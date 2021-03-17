package baekjoon;

import java.util.Scanner;

public class Main{
	public static int totalCount;
	public static int R, C, K;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int map[][] = new int[5][5];
		R = scan.nextInt();
		C = scan.nextInt();
		K = scan.nextInt();
		
		for(int i = 0; i < R; i++) {
			String str = scan.next();
			for(int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == 'T') {
					map[i][j] = -1;
				}
			}
		}
		Axis.setMap(map);
		scan.close();
		int visited[][] = new int[5][5];
		visited[R - 1][0] = 1;
		dfs(new Axis(0, R - 1, 1), visited);
		System.out.println(Main.totalCount);
	}
	public static void dfs(Axis axis, int visited[][]) {
		int x = axis.getX();
		int y =axis.getY();
		int map[][] = Axis.map;
		if (axis.getLength() == Main.K) {
			if (x == Main.C - 1 && y == 0 && map[x][y] == 0)
				Main.totalCount++;
			return;
		}
		// North
		if (y - 1 >= 0 && map[y - 1][x] == 0 && visited[y - 1][x] == 0) {
			visited[y - 1][x] = 1;
			dfs(new Axis(x, y - 1, axis.getLength() + 1), visited);
			visited[y - 1][x] = 0;
			
		}
		// South
		if (y + 1 < R && map[y + 1][x] == 0 && visited[y + 1][x] == 0) {
			visited[y + 1][x] = 1;
			dfs(new Axis(x, y + 1, axis.getLength() + 1), visited);
			visited[y + 1][x] = 0;
		}
		// West
		if (x - 1 >= 0 && map[y][x - 1] == 0 && visited[y][x - 1] == 0) {
			visited[y][x - 1] = 1;
			dfs(new Axis(x - 1, y, axis.getLength() + 1), visited);
			visited[y][x - 1] = 0;
		}
		// East
		if (x + 1 < C && map[y][x + 1] == 0 && visited[y][x + 1] == 0) {
			visited[y][x + 1] = 1;
			dfs(new Axis(x + 1, y, axis.getLength() + 1), visited);
			visited[y][x + 1] = 0;
		}	
	}
}

class Axis{
	public static int map[][] = new int[5][5];
	private int x;
	private int y;
	private int length;
	
	public Axis(int x, int y, int len) {this.x = x; this.y = y; this.length = len;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setLength(int len) {this.length = len;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getLength() {return this.length;}
	public static void setMap(int map[][]) { Axis.map = map;}
}

