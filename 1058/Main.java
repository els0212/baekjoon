import java.util.Scanner;
public class Main {
	static int N;
	static char[][] arr;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		arr = new char[N][N];
		dp = new int[N][N];
		
		//친구 관계 입력 받음
		for(int i = 0; i < N; i++){
			String line = scan.next();
			for(int j = 0; j < line.length(); j++){
				char c = line.charAt(j);
				arr[i][j] = c;
				dp[i][j] = (arr[i][j] == 'Y') ? 1 : 0;
			}
		}
		// 친구의 친구는 나의 친구
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if (arr[i][j] == 'Y'){
					for(int k = 0; k < N; k++)
						if (k != i && k != j && arr[j][k] == 'Y')
							dp[i][k] = 1;
				}
			}
		}
		// 2-친구가 가장 큰 놈을 찾음
		int cnt, max = 0;
		for(int i = 0; i < N; i++){
			cnt = 0;
			for(int j = 0; j < N; j++){
				if (dp[i][j] == 1)
					cnt++;
			}
			if (cnt > max)
				max = cnt;
		}
		System.out.println(max);
		scan.close();
	}
}
