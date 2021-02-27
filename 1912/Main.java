import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);
		int len = scan.nextInt();
		int[] arr = new int[100001];
		int[] dp = new int[100001];
		int ans;
		for(int i = 1; i <= len; i++)
			arr[i] = scan.nextInt();
		ans = arr[1];
		dp[1] = arr[1];
		scan.close();
		for(int i = 2; i <= len; i++)
		{
			dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(ans);
	}
}
