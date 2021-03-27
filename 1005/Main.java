import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numOfTest = scan.nextInt();
		for (int i = 0; i < numOfTest; i++) {
			Build build = new Build();
			build.setN(scan.nextInt());
			int k = scan.nextInt();
			for (int j = 0; j < build.getN(); j++)
				build.setConsumeTime(j, j, scan.nextInt());
			for (int j = 0; j < k; j++) {
				int x = scan.nextInt();
				int y = scan.nextInt();
				build.setConsumeTime(x - 1, y - 1, build.getConsumeTime(x - 1));
			}
			int target = scan.nextInt() - 1;
			build.setTarget(target);
			build.solve();
		}
		scan.close();
	}
}

class Build{
	private int consume[][] = new int[1000][1000];
	private int dp[] = new int[1000];
	private int n;
	private int target;
	public Build() {
		for (int i = 0; i < 1000; i++)
			dp[i] = -1;
	}
	public void setN(int n) {this.n = n;}
	public int getN() {return this.n;}
	public void setTarget(int target) {this.target = target;}
	public void setConsumeTime(int x, int y, int time) {
		consume[y][x] = time;
	}
	public int getConsumeTime(int x) {
		return consume[x][x];
	}
	public void reviseDP() {
		for (int i = 0; i < this.n; i++) {
			boolean nodependency = true;
			for (int j = 0; j < this.n; j++) {
				if (i != j && consume[i][j] > 0){
					nodependency = false;
					break ;
				}
			}
			if (nodependency == true) {
				dp[i] = consume[i][i];
			}
		}
	}
	public void solve() {
		reviseDP();
		dfs(this.target);
		System.out.println(dp[target]);
	}
	public void dfs(int target) {
		if (dp[target] != -1)
			return ;
		for (int i = 0; i < this.n; i++) {
			if (i == target || consume[target][i] == 0)
				continue ;
			else if (dp[i] == -1)
				dfs(i);
			dp[target] = Math.max(dp[target], dp[i]);
		}
		dp[target] += consume[target][target];
	}
}
