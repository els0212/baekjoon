import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Automaton automaton = new Automaton();
		automaton.solve();
	}
}

class Automaton{
	private int state[][] = {
			{2, 1}, //start
			{3, -1}, // 1이면 fail
			{-1, 4}, // 1이면 fail
			{5, -1}, // 1이면 fail 
			{2, 1}, // end state
			{5, 6},
			{2, 7}, // end state
			{8, 7}, // end state
			{5, 4}
	};
	private String inputStr;
	private int numOfTest;
	public void solve() {
		Scanner scan = new Scanner(System.in);
		this.numOfTest = scan.nextInt();
		for (int i = 0; i < this.numOfTest; i++) {
			this.inputStr = scan.next();
			int nowState = 0;
			for (int j = 0; j < this.inputStr.length(); j++) {
				int nowToken = inputStr.charAt(j) - 0x30;
				nowState = state[nowState][nowToken];
				if (nowState == -1)
					break ;
			}
			if (nowState == 4 || nowState == 6 || nowState == 7) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
		scan.close();
	}
}
