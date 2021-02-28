import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);
		int testSize;
		int arrSize;
		int sum = 0;	
		testSize = scan.nextInt();
		for(int k = 0; k < testSize; k++) {
			int[][] arr = new int[3][100001];
			int[][] dp = new int[3][100001];
			arrSize = scan.nextInt();
			for(int i =1; i <= arrSize; i++)
				arr[1][i] = scan.nextInt();
			for(int i = 1; i <= arrSize; i++)
				arr[2][i] = scan.nextInt();
			dp[1][1] = arr[1][1];
			dp[2][1] = arr[2][1];
			for(int i = 2; i <= arrSize; i++)
			{
				dp[1][i] = Math.max(dp[1][i], dp[2][i - 1]);
				dp[2][i] = Math.max(dp[2][i], dp[1][i - 1]);
				if (i >= 3) {
					dp[1][i] = Math.max(dp[1][i], dp[2][i - 2]);
					dp[2][i] = Math.max(dp[2][i], dp[1][i - 2]);
				}
				dp[1][i] += arr[1][i];
				dp[2][i] += arr[2][i];
			}
			sum = Math.max(dp[1][arrSize], dp[2][arrSize]);
			System.out.println(sum);
		}
		scan.close();
	}
}
