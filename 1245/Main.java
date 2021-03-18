package baekjoon;

import java.util.Scanner;

public class Main{
	static int map[][] = new int[100][70]; 
	static boolean visited[][] = new boolean[100][70];
	static int dx[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, 
			{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
	static int M, N;
	public static void main(String[] args) {
		int peakCount = 0;
		
		Scanner scan = new Scanner(System.in);	
		N = scan.nextInt();
		M = scan.nextInt();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = scan.nextInt();
			}
		}
		scan.close();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if (visited[i][j] == false) {
					boolean isPeak = true;
					visited[i][j] = true;
					isPeak = recursive(new Axis(j, i), isPeak);
					if (isPeak == true)
						peakCount++;
				}
			}
		}
		System.out.println(peakCount);
	}
	
	public static boolean recursive(Axis axis, boolean isPeak) {
						int x = axis.getX();
						int y = axis.getY();
						for(int k = 0; k < dx.length; k++) {
							int nx = x + dx[k][0];
							int ny = y + dx[k][1];
							if (nx < 0 || ny < 0 || nx >= M || ny >= N)
								continue;
							if (map[ny][nx] > map[y][x]) {
								isPeak = false;
							}
							if (visited[ny][nx] == false && map[ny][nx] == map[y][x]) {
								visited[ny][nx] = true;
								isPeak = recursive(new Axis(nx, ny), isPeak);
							}
						}
		return isPeak;
	}
}

class Axis{
	int x;
	int y;
	public Axis(int x, int y) {this.x = x; this.y = y;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
}

