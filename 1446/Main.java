import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Shortcut shortcut = new Shortcut();
		shortcut.getArgs();
		shortcut.solve();
	}
}

class Shortcut{
	private int n;
	private int highway;
	private LinkedList<Short> shorts = new LinkedList<Short>();
	private int dp[] = new int[10001];
	
	public void getArgs() {
		
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.highway = scan.nextInt();
		for (int i = 0; i < this.n; i++) {
			// start
			int st = scan.nextInt();
			// end
			int ed = scan.nextInt();
			// length
			int len = scan.nextInt();
			shorts.add(new Short(st, ed , len));
		}
		scan.close();
		shorts.sort(null);
	}
	
	// 시작 위치가 동일한 모든 shortway를 list로 return
	public LinkedList<Short> getShortway(int st) {
		LinkedList<Short> shortway = new LinkedList<Short>();
		for (int i = 0; i < this.n; i++) {
			Short now = shorts.get(i);
			if (now.getStart() == st) {
				shortway.add(now);
			}
		}
		return shortway;
	}
	
	public void solve() {
		// 고속도로의 길이로 초기화
		for (int i = 0; i <= 10000; i++)
			dp[i] = i;
		
		for (int i = 0; i <= highway; i++) {
			LinkedList<Short> sameSTartShorts = getShortway(i);
			if (i > 0
					&& (dp[i] > dp[i - 1] + 1)) {
				dp[i] = dp[i - 1] + 1;
			}
			// shortway가 있는 경우 더 짧은 경로로 replace
			for (Short shortway : sameSTartShorts) {
				int ed = shortway.getEnd();
				int len = shortway.getLen();
				dp[ed] = Math.min(dp[ed], dp[i] + len);
			}
		}
		System.out.println(dp[highway]);
	}
}

class Short implements Comparable<Short>{
	private int start;
	private int end;
	private int len;
	
	public Short(int st, int ed, int len) {
		this.start = st;
		this.end = ed;
		this.len = len;
	}
	
	public int getStart() { return this.start;}
	public int getLen() {return this.len;}
	public int getEnd() {return this.end;}
	
	@Override
	public int compareTo(Short comp) {
		if (this.start < comp.getStart())
			return -1;
		else if (this.start == comp.getStart())
			return 0;
		return 1;
	}
}
