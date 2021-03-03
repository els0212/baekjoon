import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int n, k, tempN;
		n = scan.nextInt();
		k = scan.nextInt();
		int cnt = Main.count(n);
		tempN = n;
		while (cnt > k)
		{
			tempN++;
			cnt = Main.count(tempN);
		}
		System.out.println(tempN - n);
		scan.close();
	}
	static int count(int n)
	{
		int cnt = 0;
		while (n > 0)
		{
			if (n % 2 == 1)
				cnt++;
			n /= 2;
		}
		return cnt;
	}
}
