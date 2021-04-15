import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Lie lie = new Lie();
		lie.getArgs();
		lie.solve();
	}
}

class Lie{
	private int n, m;
	private int numberOfknowTrue;
	private boolean trueman[] = new boolean[51];
	private int parties[][] = new int[51][51];
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.m = scan.nextInt();
		this.numberOfknowTrue = scan.nextInt();
		for (int i = 0; i < this.numberOfknowTrue; i++) {
			int numOfMan = scan.nextInt();
			trueman[numOfMan] = true;
		}
		for (int i = 1; i <= m; i++) {
			parties[i][0] = scan.nextInt();
			for (int j = 1; j <= parties[i][0]; j++) {
				int idx = scan.nextInt();
				parties[i][idx] = 1;
			}
		}
		scan.close();
	}
	
	public void knowsTrue(int idx){
		for (int i = 1; i <= 50; i++) {
			// 만약 진실을 아는 사람이 참여하고 있는 파티인 경우 거짓말 불가능
			if (parties[i][idx] == 1) {
				parties[i][0] = -1;
				for (int j = 1; j <= 50; j++) {
					if (parties[i][j] == 1 && trueman[j] == false) {
						trueman[j] = true;
						knowsTrue(j);
					}
				}
			}
		}
	}
	
	public void solve() {
		for (int i = 1; i <= 50; i++) {
			if (trueman[i] == true) {
				knowsTrue(i);
			}
		}
		int partiesCount = 0;
		for (int i = 1; i <= 50; i++) {
			if (parties[i][0] > 0) {
				partiesCount++;
			}
		}	
		System.out.println(partiesCount);
	}
}
