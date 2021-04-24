import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		SubSquare sq = new SubSquare();
		sq.getArgs();
		sq.solve();
	}
}

class SubSquare{
	private int n, m;
	private char[][] str;
	private int[][] cnt;
	private long[] alpha = new long[26];
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.m = scan.nextInt();
		str = new char[2 * n][2 * m];
		cnt = new int[2 * n][2 * m];
		for (int i = 0; i < n; i++) {
			String s = scan.next();
			for (int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				str[i][j] = c;
				str[i][m + j] = c;
				str[n + i][j] = c;
				str[n + i][m + j] = c;
			}
		}
		scan.close();
	}
	public void solve() {
		int height = 2 * n;
		int width = 2 * m;
		// 세로
		for (int i = 1; i <= height; i++) {
			// 가로
			for (int j = 1; j <= width; j++) {
				// 현재 위치를 오른쪽 위 모서리로 하는 직사각형의 넓이
				int subArea = (height - i + 1) * (width - j + 1);	
				cnt[i - 1][j - 1] =  subArea * (i * j); 
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int idx = str[i][j] - 'A';
				long add = Long.valueOf(cnt[i][j]);
				alpha[idx] += add;
			}
		}
		for (int i = 0; i < alpha.length; i++) {
			System.out.println(alpha[i]);
		}
	}
}
