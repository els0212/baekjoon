
import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);
		int len;
		long[] dp = new long[101]; // i가 커지면 int 범위를 넘어가게 됨
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		for(int i = 6; i <= 100; i++)
			dp[i] = dp[i - 5] + dp[i - 1];
		len = scan.nextInt();
		for(int i =0; i < len; i++)
		{
			int target = scan.nextInt();
			System.out.println(dp[target]);
		}
		scan.close();
	}
}
