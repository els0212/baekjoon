package baekjoon;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		War war = new War();
		war.readMapSize();
		war.readMap();
		war.solve();
		war.printAnswer();
	}
}

class War{
	int N;
	int M;
	int blue = 0;
	int white = 0;
	Scanner scan = new Scanner(System.in);
	char map[][] = new char[100][100];
	boolean visited[][] = new boolean[100][100];
	
	public War() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	public void printAnswer() {
		System.out.println(this.white + " " + this.blue);
	}
	public void readMapSize() {
		this.N = scan.nextInt();
		this.M = scan.nextInt();	
	}
	
	public void readMap() {
		for (int i = 0; i < M; i++) {
			String line = scan.next();
			for (int j = 0; j < N; j++) {
				this.map[i][j] = line.charAt(j);
			}
		}
		scan.close();
	}
	
	public void solve() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == false) {
					visited[i][j] = true;
					int cnt = dfs(i, j);
					if (map[i][j] == 'W')
						this.white += (int)(Math.pow(cnt, 2));
					else {
						this.blue += (int)(Math.pow(cnt, 2));
					}
				}
			}
		}
	}
	public int dfs(int i, int j) {
		int ret = 1;
		int dx[][] = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		for (int k = 0; k < dx.length; k++) {
			int nx = dx[k][0] + j;
			int ny = dx[k][1] + i;
			if (nx < 0 || nx >= this.N || ny < 0 || ny >= this.M)
				continue ;
			if (visited[ny][nx] == false && map[ny][nx] == map[i][j]) {
				visited[ny][nx] = true;
				ret += dfs(ny, nx);
			}
		}
		return ret;
	}
}
