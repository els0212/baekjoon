import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		MaxMultiply maxmultiply = new MaxMultiply();
		maxmultiply.getArgs();
		maxmultiply.solve();
	}
}

class MaxMultiply{
	private long s, k, max = 1;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.s = scan.nextLong();
		this.k = scan.nextLong();
		scan.close();
	}
	
	public void solve() {
		long div = this.s / this.k;
		long last = this.s % this.k;
		for (int i = 0; i < Long.valueOf(this.k).intValue(); i++) {
			if (last > 0L)
				this.max *= (div + 1);
			else
				this.max *= div;
			last--;
		}
		System.out.println(this.max);
	}
}
