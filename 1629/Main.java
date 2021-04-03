import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Multiply multiply = new Multiply();
		multiply.solve();
	}
}

class Multiply{

	public void solve() {
		long a, b, c;
		Scanner scan = new Scanner(System.in);
		a = scan.nextLong();
		b = scan.nextLong();
		c = scan.nextLong();
		scan.close();
		long ans = 0;
		ans = pow(a, b, c) % c;
		System.out.println(ans);
	}
	public long pow(long a, long b, long c) {
		if (b == 0)
			return 1;
		else if (b == 1L)
			return a;
		long n = pow(a, b / 2, c) % c;
		if (b % 2L == 1L) {
			return ((n * n) % c) * a % c;
		}
		return (n * n) % c;
	}
}
