import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Building building = new Building();
		building.getArgs();
		building.solve();
	}
}

class Building{
	private int n;
	private int buildings[] = new int[50];
	private int views[] = new int[50];
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			buildings[i] = scan.nextInt();
		}
		scan.close();
	}
	
	public void solve() {
		for (int i = 0; i < n; i++) {
			int cnt = 0;
			double limitGradient = 1000000000;
			// i의 왼쪽인 경우 view 개수 체크
			for (int j = i - 1; j >= 0; j--) {
				double nowGradient = (double)(buildings[j] - buildings[i])/(j - i); 
				if (limitGradient > nowGradient) {
					limitGradient = nowGradient;
					cnt++;
				}
			}
			limitGradient = -1000000000;
			for (int j = i + 1; j < n; j++) {
				double nowGradient = (double)(buildings[j] - buildings[i])/(j - i); 
				if (limitGradient < nowGradient) {
					limitGradient = nowGradient;
					cnt++;
				}
			}
			views[i] = cnt;
		}
		int maxValue = 0;
		for (int i = 0; i < n; i++) {
			if (views[i] > maxValue)
				maxValue = views[i];
		}
		System.out.println(maxValue);
	}
}
