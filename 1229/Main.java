import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Bigbang bigbang = new Bigbang();
		bigbang.getArgs();
		bigbang.solve();
	}
}

class Bigbang{
	private int n;
	private int bbNumbers[] = new int[1000];
	private int dp[];

	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		scan.close();
		dp = new int[n + 1];
		for (int i = 1; i < 1000; i++) {
			bbNumbers[i] = 2 * i * i - i;
			if (bbNumbers[i] <= n)
				dp[bbNumbers[i]] = 1;
		}

	}

	public void solve() {
		if (n < 6) {
			System.out.println(n);
		}
		else {
			for (int i = 1; i < 6; i++)
				dp[i] = i;
			int bbIndex = 3;
			for (int i = 7; i <= n; i++) {
				if (bbNumbers[bbIndex] <= i) {
					bbIndex++;
				}
				for (int j = bbIndex - 1; j >= 2; j--) {
					int prev = bbNumbers[j];
					int prev2 = bbNumbers[j];
					int temp = Math.min(dp[prev] + dp[i - prev], dp[prev2] + dp[i - prev2]);
					if (dp[i] == 0) {
						dp[i] = temp;
					}
					else {
						dp[i] = Math.min(dp[i], temp);
					}
				}
			}
			System.out.println(dp[n]);
		}
	}
}
