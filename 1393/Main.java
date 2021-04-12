import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Train train = new Train();
		train.getArgs();
		train.solve();
	}
}

class Train{
	private int station[] = new int[2];
	private int start[] = new int[2];
	private int dx[] = new int[2];
	private double smallest = 101;
	private int smallestAxis[] = new int[2];
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		station[0] = scan.nextInt();
		station[1] = scan.nextInt();
		start[0] = scan.nextInt();
		start[1] = scan.nextInt();
		dx[0] = scan.nextInt();
		dx[1] = scan.nextInt();
		scan.close();
	}
	// station x, station_y와의 거리
	public double getDist(int now_x, int now_y) {
		return Math.sqrt(Math.pow(now_x - station[0], 2) + Math.pow(now_y - station[1], 2));
	}
	
	// 최대 공약수 return
	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		if (a < b) {
			if (a == 0) {
				return 1;
			}
			else
				return gcd(a, b % a);
		}
		else
			return gcd(b, a % b);
	}
	
	public void solve() {
	
		if (dx[0] == 0 && dx[1] == 0) {
			smallestAxis[0] = start[0];
			smallestAxis[1] = start[1];
		}
		else {
			int forward_x = start[0];
			int forward_y = start[1];
			// dx와 dy를 최대공약수로 나눈 값을 dx, dy로 사용
			int common = gcd(dx[0], dx[1]);
			dx[0] /= common;
			dx[1] /= common;
			
			while (forward_x <= 100 && forward_y <= 100
					&& forward_x >= -100 && forward_y >= -100) {
				double forwardDist = getDist(forward_x, forward_y);
				if (forwardDist < this.smallest) {
					this.smallest = forwardDist;
					smallestAxis[0] = forward_x;
					smallestAxis[1] = forward_y;					
				}
				forward_x += dx[0];
				forward_y += dx[1];
			}
		}
		System.out.println(String.format("%d %d", smallestAxis[0], smallestAxis[1]));
	}
}
