import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Maze maze = new Maze();
		maze.getArgs();
		maze.solve();
	}
}

class Maze{
	private int m, n, minValue = -1;
	private boolean[][] maze;
	private int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.m = scan.nextInt();
		this.n = scan.nextInt();
		maze = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String s = scan.next();
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '1') {
					maze[i][j] = true;
				}
			}
		}
		scan.close();
	}
	public void dfs() {
		boolean[][] visited = new boolean[n][m];
		PriorityQueue<Room> queue = new PriorityQueue<Room>();
		visited[0][0] = true;
		queue.add(new Room(0, 0, 0));
		while (queue.isEmpty() == false) {
			Room now = queue.poll();
			if (now.getX() == m - 1 && now.getY() == n - 1) {
				this.minValue = now.getValue();
				break ;
			}
			for (int i = 0; i < dir.length; i++) {
				int dx = now.getX() + dir[i][0];
				int dy = now.getY() + dir[i][1];
				if (dx < 0 || dx >= m || dy < 0 || dy >= n || visited[dy][dx] == true) {
					continue ;
				}
				else {
					visited[dy][dx] = true;
					int nValue = now.getValue();
					if (maze[dy][dx] == true)
						nValue++;
					queue.add(new Room(dx, dy, nValue));
				}
			}
		}
	}

	public void solve() {
		dfs();
		System.out.println(minValue);
	}
}

class Room implements Comparable<Room>{
	private int x;
	private int y;
	private int value;
	
	public Room(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getValue() {return this.value;}
	@Override
	public int compareTo(Room e) {
		return this.value - e.value;
	}
}
