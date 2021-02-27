
import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);
		int len;
		int tempP;
		int[] t = new int[17];
		int[] p = new int[17];
		int[] dp = new int[17];
		
		len = scan.nextInt();
		for(int i =1; i <= len; i++)
		{
			t[i] = scan.nextInt();
			tempP = scan.nextInt();
			p[i] = (t[i] + i) > len + 1 ? 0 : tempP; 
		}
		scan.close();
		for(int i = len; i > 0; i--)
		{
			int dpPrev;
			if (t[i] + i <= len)
				dpPrev = dp[t[i] + i];
			else
				dpPrev = 0;
			dp[i] = dpPrev + p[i] > dp[i + 1] ? dpPrev + p[i] : dp[i + 1];
		}
		System.out.println(dp[1]);
	}
}
