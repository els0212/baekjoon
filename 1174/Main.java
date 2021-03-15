import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long dp[] = new long[1025];
		long N = scan.nextLong();
		Queue<Long> queue = new LinkedList<>();
		int index = 0;
		
		// 자리수 (최대 10자리까지)
		for(int i = 1; i <= 10; i++) {
			if (i == 1) {
				for(int j = 0; j < 10; j++) {
					queue.add(Long.valueOf(j));
					dp[index++] = Long.valueOf(j);
				}
			}
			else {
				int queueMax = queue.size();
				for(int j = 0; j < queueMax; j++) {
					long digit = queue.poll();
					for(int k = 0; k < digit % 10; k++) {
						dp[index++] = digit * 10 + Long.valueOf(k);
						queue.add(dp[index - 1]);
					}
				}
			}
		}
		if (N > 1023)
			System.out.println(-1);
		else
			System.out.println(dp[(int) (N - 1)]);
		scan.close();
	}
}
