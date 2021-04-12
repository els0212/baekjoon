import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Robot robot = new Robot();
		robot.getArgs();
		robot.solve();
	}
}

class Robot{
	private int n;
	private double probability[] = new double[4];
	private int dx[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	private double finalProb = 1;
	private boolean visited[][] = new boolean[29][29];
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		for (int i = 0; i < probability.length; i++) {
			probability[i] = (double)scan.nextInt() / 100;
		}
		scan.close();
		
	}
	
	void dfs(int prevX, int prevY, int moveCount, double prob) {
		if (moveCount == this.n) {
			return ;
		}
		for (int i = 0; i < 4; i++) {
			int x = prevX + dx[i][0];
			int y = prevY + dx[i][1];
			double tempProb = prob * probability[i];
			if (visited[y][x] == false) {
				visited[y][x] = true;
				dfs(x, y, moveCount + 1, tempProb);
				visited[y][x] = false;
			}
			else {
			// 이미 방문한 경우 아래로는 방문할 필요가 없으므로 지금까지 계산한 확률을 빼주고 pruning
				this.finalProb -= tempProb;
			}
		}
	}
	
	public void solve() {
		// 14, 14가 원점
		visited[14][14] = true;
		for (int i = 0; i < 4; i++) {
			int x = 14 + dx[i][0];
			int y = 14 + dx[i][1];
			visited[y][x] = true;
			dfs(x, y, 1, probability[i]);
			visited[y][x] = false;
		}
		System.out.println(this.finalProb);
	}
}
