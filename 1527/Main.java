import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Gummin gummin = new Gummin();
		gummin.getArgs();
		gummin.solve();
	}
}

class Gummin{
	private long a, b;
	private int count = 0;
	private long que[] = new long[2048];
	private int queNow = 0, queLast = 0;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.a = scan.nextInt();
		this.b = scan.nextInt();
		scan.close();
	}
	
	public void solve() {
		que[queLast++] = 4;
		que[queLast++] = 7;
		while (queNow < queLast) {
			long now = que[queNow++];
			if (now > this.b)
				break ;
			if (now >= this.a)
				this.count++;
			long nextFirst = now * 10L + 4L;
			long nextSecond = now * 10L + 7L;
			if (nextFirst <= this.b)
				que[queLast++] = nextFirst;
			if (nextSecond <= this.b)
				que[queLast++] = nextSecond;
		}
		System.out.println(this.count);
	}
}
