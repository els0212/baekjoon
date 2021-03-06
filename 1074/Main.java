import java.util.Scanner;

public class Main {
	static int r, c, cnt;
	public static void main(String args[]) {
		int N;
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		r = scan.nextInt();
		c = scan.nextInt();
		scan.close();
		int cnt = recursive(0, 0, N);
		System.out.println(cnt);
	}
	static int recursive(int row, int col, int N)
	{
		int half, quarter;
		
		if (N <= 0)
			return 0;
		half = pow(2, N - 1);
		quarter = pow(half, 2);
		if (r < row + half && c < col + half) // 1사분면
			return recursive(row, col, N - 1);
		else if (r < row + half && c >= col + half) // 2사분면
			return (recursive(row, col + half, N - 1) + quarter); 
		else if (r >= row + half && c < col + half) // 3사분면
			return (recursive(row + half, col, N - 1) + quarter * 2); 
		else // 4사분면
			return (recursive(row + half, col + half, N - 1) + quarter * 3); 
	}
	static int pow(int n, int p)
	{
		if (p < 1)
			return 1;
		return n * pow(n, p - 1);
	}
}
