import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Volume vol = new Volume();
		vol.getArgs();
		vol.solve();
	}
}

class Volume{
	private int n, s, m, ans = -1;
	private int v[] = new int[100];
	private boolean dp[][] = new boolean[101][1001];
	
	public Volume() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++)
				dp[i][j] = false;
		}
	}
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.s = scan.nextInt();
		this.m = scan.nextInt();
		for (int i = 0; i < this.n; i++)
			v[i] = scan.nextInt();
		scan.close();
	}
	
	public void solve() {
		if (this.s + v[0] <= this.m)
			dp[0][this.s + v[0]] = true;
		if (this.s - v[0] >= 0)
			dp[0][this.s - v[0]] = true;
		for (int i = 1; i <= this.n; i++) {
			for (int j = 0; j <= this.m; j++) {
				if (dp[i - 1][j] == true) {
					int added = j + v[i];
					int subed = j - v[i];
					if (added <= this.m && added >= 0)
						dp[i][added] = true;
					if (subed <= this.m && subed >= 0)
						dp[i][subed] = true;				
				}
			}
		}
		for (int i = 0; i <= this.m; i++) {
			if (dp[this.n - 1][i] == true)
				this.ans = (int)Math.max(i, this.ans);
		}
		System.out.println(ans);
	}
}
