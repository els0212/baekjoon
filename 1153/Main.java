import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Prime prime = new Prime();
		prime.getArgs();
		prime.solve();
	}
}

class Prime {
	private int ansArr[] = new int[4];
	private int n;

	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		scan.close();
	}

	public void solve() {
		boolean primes[] = new boolean[1000000];
		for (int i = 2; i < 1000000; i++) {
			primes[i] = true;
		}
		for (int i = 2; i < 1000000; i++) {
			if (primes[i] == true) {
				for (int j = i * 2; j < 1000000; j += i) {
					primes[j] = false;
				}
			}
		}
		ansArr[0] = 2;
		ansArr[1] = (this.n % 2 == 0) ? 2 : 3;
		this.n -= (ansArr[0] + ansArr[1]);
		boolean isPossible = false;
		if (n > 3) {
			for (int i = 2; i < n; i++) {
				if (primes[i] == true && primes[n - i] == true) {
					ansArr[2] = i;
					ansArr[3] = n - i;
					isPossible = true;
					break ;
				}
			}
		}
		if (isPossible == true) {
			for (int a : ansArr) {
				System.out.print(String.format("%d ", a));
			}
		}
		else {
			System.out.println(-1);
		}
	}
}
