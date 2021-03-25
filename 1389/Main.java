import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Relation rel = new Relation();
		rel.getArgs();
		rel.solve();
	}
}

class Relation{
	private int numOfUser;
	private int numOfRelations;
	private boolean friends[][] = new boolean[100][100];
	private int bacon[][] = new int [100][100];
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.numOfUser = scan.nextInt();
		this.numOfRelations = scan.nextInt();
		for (int i = 0; i < this.numOfRelations; i++) {
			int from = scan.nextInt();
			int to = scan.nextInt();
			friends[from - 1][to - 1] = true; 
			friends[to - 1][from - 1] = true; 
			bacon[from - 1][to - 1] = 1; 
			bacon[to - 1][from - 1] = 1; 	
		}
	}
	
	public void calcBaconValue() {
		// 모든 user에 대해서
		for (int i = 0; i < this.numOfUser; i++) {
			// i-th user의 친구들을 살펴봄
			for (int j = 0; j < this.numOfUser; j++) {
				// 만약 i와 j가 친구인 경우에
				if (bacon[i][j] > 0) {
					// i와 k가 친구인 경우를 확인하여 bacon value를 update한다.
					for (int k = 0; k < this.numOfUser; k++) {
						 if (k == j) {
								continue ;
						 }
						 if (bacon[i][k] > 0) {
							 // 만약 i와 k가 친구인데 j와 k는 친구가 아닌 경우에는 i를 통해서만 접근 가능
							if (bacon[j][k] == 0) {
								bacon[j][k] = bacon[i][k] + bacon[j][i];
								bacon[k][j] = bacon[j][k];
							}
							// 이미 j와 k가 친구인 경우에는 이전 bacon[j][k]와 비교
							else if (bacon[j][k] > bacon[i][k] + bacon[j][i]) {
								bacon[j][k] = bacon[i][k] + bacon[j][i];
							}
						 }
					}
				}
			}
		}
	}
	
	public void printMinValue() {
		int min = -1;
		int user = -1;
		for (int i = 0; i < this.numOfUser; i++) {
			int sum = 0;
			for (int j = 0; j < this.numOfUser; j++) {
				sum += bacon[i][j];
			}
			if (min == -1 || min > sum) {
				user = i;
				min = sum;
			}
		}
		System.out.println(user + 1);
	}
	public void solve() {
		calcBaconValue();
		printMinValue();
	}
}
