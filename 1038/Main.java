import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Desc desc = new Desc();
		desc.getArgs();
		desc.solve();
	}
}

class Desc{
	private int n;
	// 각 자리수별 개수 파악을 위한 배열 sum
	private int sum[] = new int[10];
	public void getArgs() {
		int tempSum;
		for (int i = 1; i <= 10; i++) {
			tempSum = 1;
			for (int j = i; j > 0; j--)
				tempSum *= (10 - j + 1);
			for (int j = i; j > 0; j--)
				tempSum /= j;	
			sum[i - 1] = tempSum;
		}
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		scan.close();
	}
	
	public void solve() {
		// 9876543210은 integer의 범위를 넘어가므로 long으로 선언
		ArrayList<Long> que = new ArrayList<Long>();
		// 0, 1, 2, ... ,9876543210의 총 개수는 1022개
		if (this.n < 1023) {
			for (long i = 0; i < 10; i++) {
				que.add(i);
				if (que.size() == this.n + 1L)
					break ;
			}
			long st = 1;
			while (que.size() <= this.n) {
				long now = que.get((int)st);
				st++;
				for (int i = 0; i < 10; i++) {
					long prev = que.get(i);
					if ((now % 10L) <= prev || que.size() == this.n + 1L)
						break ;
					else
						que.add(now * 10L + prev);
				}
			}
			System.out.println(que.get(que.size() - 1));
		}
		else
			System.out.println(-1);
	}
}
