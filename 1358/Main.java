import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Hockey hockey = new Hockey();
		hockey.getArgs();
		hockey.solve();
	}
}

class Hockey{
	private int w, h, x, y, p;
	private double r;
	private int[][] circle = new int[2][2];
	private int[][] players = new int[100][2];
	private int validCount;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.w = scan.nextInt();
		this.h = scan.nextInt();
		this.x = scan.nextInt();
		this.y = scan.nextInt();
		this.p = scan.nextInt();
		
		initCircle();
		for (int i = 0; i < this.p; i++) {
			this.players[i][0] = scan.nextInt();
			this.players[i][1] = scan.nextInt();
		}
		this.validCount = this.p;
		scan.close();
	}
	
	private void initCircle() {
		this.r = this.h / 2;
		this.circle[0][0] = this.x;
		this.circle[0][1] = this.y + (int)(r);
		this.circle[1][0] = this.x + this.w;
		this.circle[1][1] = this.y + (int)(r);
		
	}
	private static double getDistance(int x, int y, int center_x, int center_y) {
		double dist_x = Math.pow(center_x - x, 2);
		double dist_y = Math.pow(center_y - y, 2);
		return (Math.sqrt(dist_x + dist_y));
	}
	private boolean checkOutOfCircle(int player_x, int player_y) {
		double distFromFirstCircle = getDistance(player_x, player_y, circle[0][0], circle[0][1]); 
		double distFromSecondCircle = getDistance(player_x, player_y, circle[1][0], circle[1][1]); 
		if (distFromFirstCircle > this.r && distFromSecondCircle > this.r)
			return true;
		return false;
	}
	
	private boolean checkOutOfRectangle(int player_x, int player_y) {
		if (player_x < this.x || player_x > this.x + this.w)
			return true;
		else if (player_y < this.y || player_y > this.y + this.h)
			return true;
		return false;
	}
	public void solve() {
		for (int i = 0; i < this.p; i++) {
			int player_x = this.players[i][0];
			int player_y = this.players[i][1];
			if (checkOutOfCircle(player_x, player_y) == true
					&& checkOutOfRectangle(player_x, player_y) == true)
				this.validCount--;
		}
		System.out.println(this.validCount);
	}
}
