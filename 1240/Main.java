import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.getArgs();
		tree.solve();
	}
}

class Tree{
	private int m, n;
	private int[][] edges = new int[1001][1001];
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.m = scan.nextInt();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				edges[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < n - 1; i++) {
			int src = scan.nextInt();
			int dst = scan.nextInt();
			int value = scan.nextInt();
			edges[src][dst] = value;
			edges[dst][src] = value;
		}
		for (int i = 0; i < m; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			if (x > y) {
				int temp = x;
				x = y;
				y = temp;
			}
			System.out.println(dijkstra(x, y));
		}
		scan.close();
	}
	// dijkstra
	public int dijkstra(int x, int y) {
		int[] dist = new int[n + 1];
		boolean[] visited = new boolean[n + 1];
		
		for (int i = 1; i <= n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		for (int i = 1; i <= n; i++) {
			if (edges[x][i] != Integer.MAX_VALUE) {
				dist[i] = edges[x][i];
			}
		}
		dist[x] = 0;
		visited[x] = true;
		for (int i = 1; i <= n; i++) {
			int minValue = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int j = 1; j <= n; j++) {
				if (visited[j] == false && dist[j] < minValue) {
					minValue = dist[j];
					minIndex = j;
				}
			}
			if (minIndex == -1)
				break ;
			visited[minIndex] = true;
			for (int j = 1; j <= n; j++) {
				if (edges[minIndex][j] != Integer.MAX_VALUE) {
					int nextDist = dist[minIndex] + edges[minIndex][j];
					if (nextDist < dist[j]) {
						dist[j] = nextDist;
					}
				}
			}
		}
		return dist[y];
	}
}
class Node implements Comparable<Node>{
	private int x;
	private int y;
	private int value;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getValue() {return this.value;}
	
	@Override
	public int compareTo(Node e) {
		if (this.value > e.value)
			return 1;
		else if (this.value == e.value)
			return 0;
		return -1;
	}
}
