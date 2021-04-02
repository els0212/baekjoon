import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		One one = new One();
		one.getArgs();
		one.solve();
	}
}

class One{
	private int n, ans = 1;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		scan.close();
	}
	
	public void solve() {
		if (this.n % 2 == 0 || this.n % 5 == 0)
			this.ans = -1;
		else {
			int mod = 1 % this.n;
			while (mod != 0) {
				mod = (mod * 10 + 1) % this.n;
				ans++;
			}
		}
		System.out.println(this.ans);
	}
}
