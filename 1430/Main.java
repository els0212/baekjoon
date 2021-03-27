import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Tower tower = new Tower();
		tower.getArgs();
		tower.solve();
	}
}

class Axis implements Comparable<Axis>{
	private int x;
	private int y;
	private int dist;
	private static Axis enemy;
	public Axis() {this.x = -1; this.y = -1; this.dist = 0;}
	public Axis(int x, int y) {this.x = x; this.y = y; this.dist = 0;}
	public void setDist(int dist) {this.dist = dist;}
	public int getDist() {return this.dist;}
	public static void setEnemy(Axis enemy) {Axis.enemy = enemy;}
	public static Axis getEnemy() {return Axis.enemy;}
	public double getDistanceTo(Axis target) {
		return Math.sqrt(Math.pow(this.x - target.x, 2) + Math.pow(this.y - target.y, 2));
	}
	
	@Override
	public int compareTo(Axis next) {
		double distFromNextToEnemy = next.getDistanceTo(enemy);
		double distFromNowToEnemy = this.getDistanceTo(enemy);
		if (distFromNowToEnemy < distFromNextToEnemy)
			return 1;
		else if (distFromNowToEnemy == distFromNextToEnemy)
			return 0;
		else
			return -1;
	}

}

class Tower{
	private double damage = 0;
	private int n, r, d;
	//private Axis enemy;
	private int near[][] = new int[100][100];
	private LinkedList<Axis> myTower = new LinkedList<Axis>();
	
	public void getArgs() {
		int x, y;
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		r = scan.nextInt();
		d = scan.nextInt();
		x = scan.nextInt();
		y = scan.nextInt();
		Axis.setEnemy(new Axis(x, y));
		for (int i = 0; i < n; i++) {
			x = scan.nextInt();
			y = scan.nextInt();
			myTower.add(new Axis(x, y));
		}
		scan.close();
	}
	
	public void setClosest() {
		
		for (int i = 0; i < this.n; i++) {
			Axis now = myTower.get(i);
			double distToEnemy = now.getDistanceTo(Axis.getEnemy());
			
			// 적을 공격할 수 있는 경우 near[i][i] = 1, 공격 불가능시 0
			if (distToEnemy <= r)
				near[i][i] = 1;
			// i번째 axis에서 <=r 거리에 있는 모든 axis를 near에 추가
			for (int j = 0; j < this.n; j++) {		
				Axis next = myTower.get(j);
				if (now == next)
					continue ;
				double dist = now.getDistanceTo(next);
				if (dist <= r)
					near[i][j] = 1;
				else
					near[i][j] = 5001;
			}
		}
		
		// 모든 axis에 대해서 loop
		for (int i = 0; i < this.n; i++) {
			// i와 인접한 j
			for (int j = 0; j < this.n; j++) {
				// i와 j가 인접하고 near[i][i]가 아닌 경우에만
				if (near[i][j] != 5001 && i != j) {
					// j와 인접한 k
					for (int k = 0; k < this.n; k++) {
						int candidate = near[i][j] + near[j][k];
						// near[j][i] || near[j][j] || near[j][k] == 5001인 경우는 skip
						if (k != i && k != j && near[j][k] != 5001) {
								// i와 k가 인접하지 않거나 i ~ k 거리가 near[i][j] + near[j][k]보다 먼 경우 update
								if (near[i][k] == 0 || near[i][k] > candidate) {
									near[i][k] = candidate;
									near[k][i] = near[i][k];
								}
						}
					}
				}
			}
		}
	}
	
	public void solve() {
		setClosest();
		for (int i = 0; i < n; i++) {
			if (near[i][i] == 1)
				this.damage += this.d;
			else {
				int min = 5001;
				for (int j = 0; j < n; j++) {
					if (i == j)
						continue ;
					else if (near[i][j] < min && near[j][j] == 1) {
						min = near[i][j];
					}
				}
				if (min == 5001)
					continue ;
				else {
					double temp_d = d;
					while (min >= 1) {
						temp_d /= 2;
						min--;
					}
					this.damage += temp_d;
				}
			}
		}
		System.out.println(String.format("%.2f", this.damage));
	}
}
