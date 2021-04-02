import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Hand hand = new Hand();
		hand.getArgs();
		hand.solve();
	}
}

class Hand{
	private int n, can;
	private long numbers = 0L;
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		// 다친 손가락의 번호
		this.n = scan.nextInt();
		// 다친 손가락으로 카운트할 수 있는 개수
		this.can = scan.nextInt();
		scan.close();
	}
	
	public void solve() {
		int div;
		if (n != 1 && n != 5)
			div = this.can / 2;
		else
			div = this.can;
		this.numbers = Long.valueOf(n) - 1L;
		long addNum = (8L * div);
		this.numbers += addNum;
		this.can %= 2;
		long slot;
		if (this.can > 0) {
			if (n == 2)
				slot = 6L;
			else if (n == 3)
				slot = 4L;
			// n == 4
			else if (n == 4)
				slot = 2L;
			else
				slot = 0;
		}
		else
			slot = 0;
		System.out.println(this.numbers + Long.valueOf(slot) * this.can);
	}
}
