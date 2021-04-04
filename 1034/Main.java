import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Table table = new Table();
		table.getArgs();
		table.solve();
	}
}

class Table{
	private int n, m, k, max = 0;
	private long table[] = new long[50];
	private int same[] = new int[50];
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		for (int i = 0; i < n; i++) {
			String s = scan.next();
			for (int j = 1; j <= m; j++) {
				if (s.charAt(j - 1) == '1')
					table[i] |= (1L<<j);
				else
					table[i] |= (0L<<j);
			}
		}
		k = scan.nextInt();
		scan.close();		
	}
	
	public int count(int lineNum) {
		int cnt = 1;
		for (int i = 0; i < n; i++) {
			if (i == lineNum)
				continue ;
			else if ((table[i] ^ table[lineNum]) == 0) {
				cnt++;
			}
		}
		return cnt;
	}

	public void solve() {
		for (int i = 0; i < n; i++) {
			same[i] = count(i);
			int numOfZeros = this.m - Long.bitCount(table[i]);
			if ( (numOfZeros % 2) == (k % 2)
					&& (numOfZeros <= k)
						&& same[i] > this.max) {
				max = same[i];
			}
		}
		System.out.println(max);
	}
}
