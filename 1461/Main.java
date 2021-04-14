import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Library library = new Library();
		library.getArgs();
		library.solve();
	}
}

class Library{
	private int n, m;
	LinkedList<Integer> negative = new LinkedList<Integer>();
	LinkedList<Integer> positive = new LinkedList<Integer>();
	public void getArgs() {
		
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.m = scan.nextInt();
		for (int i = 0; i < this.n; i++) {
			int now = scan.nextInt();
			if (now < 0)
				negative.add(now);
			else
				positive.add(now);
		}
		scan.close();
		negative.sort(null);
		positive.sort(null);
	}
	
	public int getNegativeSum(LinkedList<Integer> list) {
		int sum = 0;
		int div = list.size() / m;
		
		for (int i = 0; i <= div; i++) {
			// 나머지가 없는 경우에는 pass
			if (i == div && list.size() % m == 0)
				break ;
			int tempIdx = i * m;
			int num = Math.abs(list.get(tempIdx));
			sum += (num * 2);
		}
		return sum;
	}
	
	public int getPositiveSum(LinkedList<Integer> list) {
		int sum = 0;
		int div = list.size() / m;
		
		for (int i = 0; i <= div; i++) {
			int tempIdx = list.size() - i * m - 1;
			if (tempIdx < 0)
				break ;
			int num = Math.abs(list.get(tempIdx));
			sum += (num * 2);
		}
		return sum;
	}
	
	public void solve() {
		int absMax = 0;
		if (positive.size() == 0) {
			absMax = Math.abs(negative.get(0));
		}
		else if (negative.size() == 0) {
			absMax = Math.abs(positive.get(positive.size() - 1));
		}
		else {
			absMax = Math.max(
					Math.abs(negative.get(0))
					, Math.abs(positive.get(positive.size() - 1))
					);
		}
		
		int negativeSum = getNegativeSum(this.negative);
		int positiveSum = getPositiveSum(this.positive);
		System.out.println( (negativeSum + positiveSum - absMax));
	}
}
