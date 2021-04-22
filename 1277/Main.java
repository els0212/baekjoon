import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		PowerStation ps = new PowerStation();
		ps.getArgs();
		ps.solve();
	}
}

class PowerStation{
	private int n, w;
	private double m;
	private Axis[] stations;
	private boolean[][] edges;
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.w = scan.nextInt();
		this.m = scan.nextDouble();
		
		stations = new Axis[n];
		edges = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			
			// x, y는 좌표
			stations[i] = new Axis(i, x, y);
		}
		for (int i = 0; i < w; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			
			// x, y는 station 번호 
			edges[x - 1][y - 1] = true;
			edges[y - 1][x - 1] = true;
		}
		scan.close();
	}
	public long greedy() {
		boolean[] visited = new boolean[n];
		double[] dist = new double[n];

		for (int i = 0; i < n; i++) {
			if (edges[0][i] == true) {
				dist[i] = 0;
			}
			else {
				dist[i] = Double.MAX_VALUE;
			}
		}
		dist[0] = 0;
		for (int j = 0; j < n; j++) {
			double minValue = Double.MAX_VALUE;
			int minIndex = -1;
			for (int i = 0; i < n; i++) {
				if (visited[i] == false && dist[i] < minValue) {
					minValue = dist[i];
					minIndex = i;
				}
			}
			if (minIndex == n - 1) {
				break ;
			}
			Axis now = stations[minIndex];
			int nowStnNum = now.getStationNumber();
			visited[nowStnNum] = true;
			for (int i = 0; i < n; i++) {
				if (i == minIndex)
					continue ;
				Axis test = stations[i];
				double testDist = now.getDistance(test);
				int testStnNum = test.getStationNumber();
				if (edges[nowStnNum][testStnNum] == true)
					dist[i] = Math.min(dist[i], dist[nowStnNum]);
				else
					dist[i] = Math.min(dist[i], dist[nowStnNum] + testDist);
			}
		}
		return (long)(dist[n - 1] * 1000L);
	}

	public void solve() {
		long ans = greedy();
		System.out.println(ans);
	}
}

class Axis{
	private int stnNumber;
	private int x;
	private int y;
	
	public Axis(int stnNumber, int x, int y) {
		this.stnNumber = stnNumber;
		this.x = x;
		this.y = y;
	}
	public double getDistance(Axis e) {
		return Math.sqrt(Math.pow(this.x - e.x, 2) + Math.pow(this.y - e.y, 2));
	}
	public int getStationNumber() {return this.stnNumber;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
}
