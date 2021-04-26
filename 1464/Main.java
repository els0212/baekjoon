import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.getArgs();
		sol.solve();
	}
}

class Solution{
	private String origin, ans;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		origin = scan.next();
		ans = "" + origin.charAt(0);
		scan.close();
	}
	public void reverse() {
		StringBuffer sb = new StringBuffer(ans);
		ans = sb.reverse().toString();
	}
	public void solve() {
		for (int i = 1; i < origin.length(); i++) {
			if (origin.charAt(i) > ans.charAt(i - 1))  {
				reverse();
				ans += origin.charAt(i);
				reverse();
			}
			else
				ans += origin.charAt(i);
		}
		reverse();
		System.out.println(ans);
	}
}
