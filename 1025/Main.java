import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		//System.out.println(Math.sqrt(576));
		ArithmeticProgression ap = new ArithmeticProgression();
		ap.getArgs();
		ap.solve();
	}
}
class Axis{
	private int n;
	private int m;
	public Axis(int n, int m) {this.n = n; this.m = m;}
	public int getN() {return this.n;}
	public int getM() {return this.m;}
	public int getDiffN(int n) {return this.n - n;}
	public int getDiffM(int m) {return this.m - m;}
}

class ArithmeticProgression{
	private int n, m;
	private int arr[][] = new int[9][9];
	private int maxSquare = -1;
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.m = scan.nextInt();
		if (this.n != 0 && this.m != 0) {
			for (int i = 0; i < this.n; i++) {
				String line = scan.next();
				for (int j = 0; j < this.m; j++) {
					arr[i][j] = line.charAt(j) - '0';
				}
			}
		}
		scan.close();
	}
	
	public void solve() {
		if (this.n == 0 && this.m == 0) {
			System.out.println(-1);
			return ;
		}
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				Stack<Axis> visited = new Stack<Axis>();
				visited.add(new Axis(i, j));
				dfs(i, j, arr[i][j], visited);
			}
		}
		System.out.println(this.maxSquare);
	}
	
	public static boolean isSquare(int n) {
		int sq = (int)Math.sqrt(n);
		if ((int)Math.pow(sq, 2) == n)
			return true;
		return false;
	}
	
	public static boolean isArithmeticProgression(Stack<Axis> visited) {
		if (visited.size() <= 2)
			return true;
		Axis now = visited.pop();
		Axis prev = visited.pop();
		int diff_x = now.getDiffM(prev.getM());
		int diff_y = now.getDiffN(prev.getN());
		while (visited.isEmpty() == false) {
			now = prev;
			prev = visited.pop();
			int temp_diff_x = now.getDiffM(prev.getM());
			int temp_diff_y = now.getDiffN(prev.getN());
			if (diff_x != temp_diff_x || diff_y != temp_diff_y)
				return false;
		}
		return true;
	}
	
	public void dfs(int i, int j, int sum, Stack<Axis> visited) {
		// i, j가 등차수열인지 체크
		if ((isArithmeticProgression((Stack<Axis>) visited.clone()) == false) || (visited.size() > 9))
			return ;
			//sum이 제곱수인 경우
		if (isSquare(sum) == true) {
			// 등차수열이 맞는 경우에는 가장 큰 제곱수인지 확인
			if (sum >= this.maxSquare) {
				this.maxSquare = sum;
			}
		}
		for (int k = 0; k < this.n; k++) {
			for (int m = 0; m < this.m; m++) {
				visited.add(new Axis(k, m));
				int temp_sum = sum * 10 + arr[k][m];
				dfs(k, m, temp_sum, visited);
				visited.pop();
			}
		}
	}
}
