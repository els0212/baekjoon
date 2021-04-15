import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Teach teach = new Teach();
		teach.getArgs();
		teach.solve();
	}
}

class Teach{
	private int n, k, canRead = 0;
	private String str[] = new String[50];
	//private boolean alphabet = new LinkedList<Character>();
	private boolean used[] = new boolean[26];
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.k = scan.nextInt();
		used['a' - 'a'] = true;
		used['n' - 'a'] = true;
		used['t' - 'a'] = true;
		used['i' - 'a'] = true;
		used['c' - 'a'] = true;
		for (int i = 0; i < n; i++) {
			String now = scan.next().replaceAll("a|n|t|i|c", "");
			str[i] = now;
		}
		scan.close();
	}

	public void dfs(int k, int nowCharIndex) {
		if (k == 0) {
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				boolean flag = true;
				for (int j = 0; j < str[i].length(); j++) {
					if (used[str[i].charAt(j) - 'a'] == false) {
						flag = false;
						break ;
					}
				}
				if (flag == true)
					cnt++;
			}
			this.canRead = Math.max(canRead, cnt);
			return ;
		}
		for (int i = nowCharIndex; i < 26; i++) {
			if (used[i] == false) {
				used[i] = true;
				dfs(k - 1, i + 1);
				used[i] = false;
			}
		}
	}
	
	public void solve() {
		if (k >= 5)  {
			this.k -= 5;
			dfs(k, 0);
		}
		System.out.println(this.canRead);
	}
}
