import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Bus bus = new Bus();
		bus.getArgs();
		bus.solve();
	}
}

class Bus{
	private int n;
	private long t, minTime = -1;
	private long buses[][] = new long[100000][3];
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.t = scan.nextLong();
		for (int i = 0; i < this.n; i++) {
			long startTime = scan.nextLong();
			long TimeSlot = scan.nextLong();
			long totalNumOfBus = scan.nextLong();
			buses[i][0] = startTime;
			buses[i][1] = TimeSlot;
			buses[i][2] = totalNumOfBus;
		}
		scan.close();
	}
	
	public void getMinValue(long candidate) {
		if (this.minTime == -1)
			this.minTime = candidate;
		else
			this.minTime = Math.min(candidate, this.minTime);	
	}
	public void solve() {
		for (int i = 0; i < this.n; i++) {
			long last = this.t - buses[i][0];
			if (last <= 0L) {
				getMinValue(last * -1L);
			}
			else {
				long div = last / buses[i][1];
				if (last % buses[i][1] > 0)
					div++;
				if (div < buses[i][2]) {
					long nextTime = buses[i][0] + buses[i][1] * div;
					getMinValue(nextTime - this.t);
				}
			}
		}
		System.out.println(this.minTime);
	}
}
