import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		RemoteController remoteController = new RemoteController();
		remoteController.getArgs();
		remoteController.solve();
	}
}

class RemoteController{
	private int n, m, closest = 100, digits;
	private boolean malfunction[] = new boolean[10];
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.m = scan.nextInt();
		for (int i = 0; i < m; i++) {
			int num = scan.nextInt();
			malfunction[num] = true;
		}
		int tempN = n;
		while ((tempN / 10) > 0) {
			tempN /= 10;
			digits++;
		}
		digits++;
		scan.close();
	}
	public void recursive(int number, int sum, int loopCnt) {
		if (loopCnt != 0) {
			int nowDiff = Math.abs(closest - this.n);
			int candDiff = Math.abs(sum - this.n);
			if (candDiff < nowDiff || (candDiff == nowDiff && sum < closest)) {
				
				closest = sum;
			}
			if (loopCnt == digits + 1)
				return ;
		}
		int mult = (int)Math.pow(10, loopCnt);
		for (int i = 0; i < 10; i++) {
			if (malfunction[i] == false) {
				recursive(number / 10, sum + i * mult, loopCnt + 1);
			}
		}
	}
	
	public void solve() {
		if (this.n == 100) {
			System.out.println(0);
		}
		else if (this.m == 10) {
			int cnt = this.n > 100 ? this.n - 100: 100 - this.n;
			System.out.println(cnt);
		}
		else {	
			recursive(this.n, 0, 0);
			
			int cnt = (this.closest > this.n) ? this.closest - this.n : this.n - this.closest;
			while (this.closest / 10 != 0) {
				cnt++;
				this.closest /= 10;
			}
			if (++cnt > Math.abs(this.n - 100)) {
				cnt = Math.abs(this.n - 100);
			}
			System.out.println(cnt);
			
		}
	}
}
