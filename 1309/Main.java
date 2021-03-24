import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Zoo zoo = new Zoo();
		zoo.getN();
		zoo.solve();
	}
}

class Zoo{
	private long dp[][] = new long[100000][3];
	private int N;
	
	public void getN() {
		Scanner scan = new Scanner(System.in);
		this.N = scan.nextInt();
		scan.close();		
	}
	
	public void solve() {
		for (int i = 0; i < 3; i++) {
			dp[0][i] = 1L;
		}
		for (int i = 1; i < this.N; i++) {
			// i 에 아무 것도 넣지 않을 때
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901L;
			// i의 왼쪽에 사자를 넣을 때
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901L;
			// i의 오른쪽에 사자를 넣을 때
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901L;
		}
		long sum = (dp[this.N - 1][0] + dp[this.N - 1][1] + dp[this.N - 1][2]) % 9901L;
		System.out.println(sum);
	}
}
