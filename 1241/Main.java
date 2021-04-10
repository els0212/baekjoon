import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Head head = new Head();
		head.getArgs();
		head.solve();
	}
}

class Head{
	private int n;
	// 입력받은 숫자를 저장하는 배열
	private int numbers[] = new int[100000];
	// 각 숫자의 개수를 저장하는 배열
	private int toktok[] = new int[1000001];
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		for (int i = 0; i < this.n; i++)
			numbers[i] = scan.nextInt();
		scan.close();
	}
	
	public void solve() {
		// toktok 배열에 각 숫자의 개수를 저장함
		for (int i = 0; i < this.n; i++) {
			toktok[numbers[i]]++;
		}
		for (int i = 0; i < this.n; i++) {
			int tempTokTok = 0;
			// 약수들을 구해서 개수를 더해줌
			for (int j = 1; j <= (int)Math.sqrt(numbers[i]); j++) {
				if (numbers[i] % j == 0) {
					int div = numbers[i] / j;
					tempTokTok += toktok[j];
					tempTokTok += toktok[div];
					// 1 * 1이나 2 * 2 와 같은 경우에는 1번만 더하도록 해줌
					if (j == div)
						tempTokTok -= toktok[j];
					// 만약 1 * n과 같이 본인의 count가 포함된 경우에는 1을 감소
					if (div == numbers[i])
						tempTokTok--;
				}
			}
			System.out.println(tempTokTok);
		}
	}
}
