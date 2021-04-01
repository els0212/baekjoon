import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Guitar guitar = new Guitar();
		guitar.getArgs();
		guitar.solve();
	}
}

class Guitar{
	private int n, m, ans = 11;
	long yn[] = new long[10];
	int count[] = new int[10];
	int max = -1;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.m = scan.nextInt();
		for (int i = 0; i < this.n; i++) {
			String name = scan.next();
			String str = scan.next();
			int cnt = 0;
			long bits = 0;
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == 'Y') {
					bits |= (1L<<j);
					cnt++;
				}
				else {
					bits |= (0L<<j);
				}
			}
			count[i] = cnt;
			yn[i] = bits;
		}
		
		scan.close();
	}
	
	public void solve() {
		for (int i = 0; i < this.n; i++) {
			boolean visited[] = new boolean[10];
			long sum = yn[i];
			visited[i] = true;
			dfs(i, sum, visited);
			visited[i] = false;
		}
		if (this.max == 0)
			this.ans = -1;
		System.out.println(this.ans);
	}
	
	public boolean dfs(int now, long sum, boolean visited[]){
		int cnt = Long.bitCount(sum);
		if (cnt >= this.max) {
			int numOfGuitars = countVisited(visited);
			if (cnt == this.max) {
				this.ans = Math.min(numOfGuitars, this.ans);
			}
			else
				this.ans = numOfGuitars;
			this.max = cnt;
		}
		for (int j = 0; j < this.n; j++){
			if (visited[j] == false) {
				visited[j] = true;
				dfs(j, sum | yn[j], visited);
				visited[j] = false;
			}
		}
		return false;
	}
	public static int countVisited(boolean visited[]) {
		int cnt = 0;
		for (int i = 0; i < visited.length; i++)
			if (visited[i] == true)
				cnt++;
		return cnt;
	}
}
