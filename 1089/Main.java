import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Bulb bulb = new Bulb();
		bulb.getArgs();
		bulb.solve();
	}
}

class Bulb{
	private int numbers[] = new int[10];
	private int n;
	private int input[] = new int[10];
	
	public void setNumbers() {
		String numString[] = {
		"111101101101111", // 0
		"001001001001001", // 1
		"111001111100111", // 2
		"111001111001111", // 3
		"101101111001001", // 4
		"111100111001111", // 5
		"111100111101111", // 6
		"111001001001001", // 7
		"111101111101111", // 8
		"111101111001111"  // 9
		};
		for (int i = 0; i < 10; i++) {
			String now = numString[i];
			for (int j = 1; j <= now.length(); j++) {
				if (now.charAt(j - 1) == '1')
					numbers[i] |= (1 << (16 - j));
			}
		}
	}
	public void getArgs() {
		setNumbers();
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		for (int i = 4; i >= 0; i--) {
			String s = scan.next();
			int suffix = i * 3;
			for (int j = 1, k = 0; j <= 4 * n; j++) {
				int shift = suffix + 4 - j % 4;
				if (j != 0 && j % 4 == 0) {
					k++;
					continue ;
				}
				if (s.charAt(j - 1) == '#')
					input[k] |= (1<<shift);
				else
					input[k] |= (0<<shift);
			}
		}
		scan.close();
	}
	public void solve() {
		int que[][] = new int[10][11];
		for (int i = 0; i < this.n; i++) {
			// i번째 인자의 index 저장 위치
			que[i][0] = 0;
			// 실제 후보들이 들어있는 위치
			que[i][1] = -1;
		}
		for (int j = 0; j < this.n; j++) {
			int nowQue[] = que[j];
			int queIndex = 1;
			int mult = (int)Math.pow(10, this.n - j - 1);
			for (int i = 0; i < 10; i++) {
				int xorOutput = numbers[i] ^ input[j];
				int andOutput = input[j] & xorOutput;
				if (andOutput == 0) {
					nowQue[queIndex++] = (i * mult);
				}
			}
			que[j][0] = queIndex - 1;
		}
		for (int i = 0; i < this.n; i++) {
			// que[i][0]에 있는 index가 0인 경우 아무것도 들어 있지 않음을 의미함
			if (que[i][0] == 0) {
				System.out.println(-1);
				return ;
			}
		}
		double sum = 0;
		
		for (int i = 0; i < this.n; i++) {
			double tempSum = 0;
			for (int j = 1; j <= que[i][0]; j++) {
				tempSum += que[i][j];
			}
			sum += (tempSum / que[i][0]);
		}
		System.out.println(sum);
	}
}
