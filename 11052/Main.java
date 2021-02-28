import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);
		int len;
		int[] arr = new int[1001];
		int[] dp = new int[1001];
		
		len = scan.nextInt();
		for(int i =1; i <= len; i++)
			arr[i] = scan.nextInt();
		scan.close();
		for(int i = 1; i <= len; i++)
		{
			for(int j = 1; j <=i; j++) {
				dp[i] = Math.max(dp[i], dp[i - j] + arr[j]);
			}
		}
		System.out.println(dp[len]);
	}
}
