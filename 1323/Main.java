import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Number number = new Number();
		number.getArgs();
		number.solve();
	}
}

class Number{
	private int n, k;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.k = scan.nextInt();
		scan.close();
	}
	
	public void solve() {
		int cnt = 0;
		long div = 0;
		long next = (long)n;
		long mult = 0;
		for (int i = n; i > 0; i /= 10) {
			mult++;
		}
		if (k == 1) {
			System.out.println(1);
		}
		else {
			n = n % k;
			mult = (long)Math.pow(10L, mult) % k;
			
			// div가 cycle을 형성하는지 체크하기 위한 map
			Map<String, Integer> prev = new HashMap<String, Integer>();
			do {
				if (next == 0L) {
					cnt = -1;
					break ;
				}
				div = (div + next) % (long)k;
				// 만약 cycle이 생성된 경우에는 생성 불가
				if (prev.get(Long.toString(div)) != null) {
					cnt = -1;
					break ;
				}
				
				// cycle이 없으면 map에 key를 추가
				else
					prev.put(Long.toString(div), 1);
				cnt++;
				next = (next * mult) % (long)k;
			} while (div != 0L);
			System.out.println(cnt);
		}
	}
}
