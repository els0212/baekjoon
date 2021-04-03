import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Monkey monkey = new Monkey();
		monkey.getArgs();
		monkey.solve();
	}
}

class Monkey{
	private int x, y;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.x = scan.nextInt();
		this.y = scan.nextInt();
		scan.close();
	}
	public void solve() {
		int d = this.y - this.x;
		int root = (int)Math.sqrt(d);
		int answer;
		if (x == y)
			answer = 0;
		else {
			int lower = (int)Math.pow(root,  2);
			int higher = (int)Math.pow(root + 1,  2);
			if (d == lower) {
				answer = getDays(root);
			}
			else if (d <= lower + root)
				answer = getDays(root) + 1;
			else
				answer = getDays(root + 1);
		}
		System.out.println(answer);
	}
	
	public int getDays(int n) {
		return (2 * n - 1);
	}
}
