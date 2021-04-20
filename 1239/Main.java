import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Chart chart = new Chart();
		chart.getArgs();
		chart.solve();
	}
}

class Chart{
	private int n;
	private double numbers[];
	private int count = 0;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		numbers = new double[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = scan.nextDouble();
		}
		scan.close();
	}
	public void swap(int src, int dst) {
		double temp = numbers[src];
		numbers[src] = numbers[dst];
		numbers[dst] = temp;
	}
	public void count() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			double sum = 0;
			for (int j = i; j < n; j++) {
				sum += numbers[j];
				if (sum == 50) {
					cnt++;
					break ;
				}
			}
		}
		cnt--;
		if (cnt > count) {
			count = cnt;
		}
	}
	public void recursive(int src) {
		count();
		for (int i = src; i < n; i++) {
			for (int j = src + 1; j < n; j++) {
				swap(i, j);
				recursive(i + 1);
				swap(i, j);
			}
		}
	}
	public void solve() {
		recursive(0);
		System.out.println(count);
	}
}
