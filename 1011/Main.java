import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int testSize;
		testSize = scan.nextInt();
		for (int i = 0; i < testSize; i++) 
		{
			int st = scan.nextInt();
			int ed = scan.nextInt();
			int absSize = ed - st;
			int root = (int)Math.sqrt((double)(ed - st));
			int cnt;
			int rootPow = Main.pow(root);
			if ( rootPow == absSize)
				cnt = 2 * root - 1;
			else if (rootPow < absSize && absSize <= rootPow + root)
				cnt = 2 * root;
			else
				cnt = 2 * root + 1;
			System.out.println(cnt);
		}
		scan.close();
	}
	static int pow(int n)
	{
		return n * n;
	}
}
