import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Prime prime = new Prime();
		prime.getArgs();
		prime.solve();
	}
}

class Prime{
	long a, b;
	long count = 0;
	boolean arr[] = new boolean[10000001];
	public void getArgs() {
		for (int i = 2; i <= 10000000; i++)
			arr[i] = true;
		Scanner scan = new Scanner(System.in);
		this.a = scan.nextLong();
		this.b = scan.nextLong();
		scan.close();
	}
	
	public void solve() {
		ertosthenes();
		long sqrtB = (long)Math.sqrt(this.b);
		long tempA, tempB;
		for (int i = 0; i <= sqrtB; i++) {
			if (arr[i] == true) {
				tempA = this.a;
				tempB = this.b;
				long now = (long)i * (long)i;
				while (now < tempA) {
					now *= (long)i;
				}
				
				if (now > tempB)
					continue ;
				while (now >= tempA && now <= tempB) {
					this.count++;
					
					// overflow 발생 여부 체크
					if (now != 0 && now > Long.MAX_VALUE / (long)i)
						break ;
					now *= (long)i;
				}
			}
		}
		System.out.println(this.count);
	}
	private void ertosthenes() {
		for (int i = 2; i <= 10000000; i++) {
			if (arr[i] == true) {
				for (int j = i + i; j <= 10000000; j += i){
					arr[j] = false;
				}
			}
		}
	}
}

