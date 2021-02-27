import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);
		int len = scan.nextInt();
		int[] dp = new int[1000];
		scan.close();
		dp[0] = 1;
		for(int i = 1; i < len; i++)
		{
			// Even number
			if (i % 2 == 1)
				dp[i] = (dp[i - 1] * 2 + 1) % 10007;
			// Odd number
			else
				dp[i] = (dp[i - 1] * 2 - 1) % 10007;
		}
		System.out.println(dp[len - 1]);
	}
}
