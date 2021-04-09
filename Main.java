import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Gear gear = new Gear();
		gear.getArgs();
		gear.solve();
	}
}

class Gear{
	private String up, down;
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		up = scan.next();
		down = scan.next();
		scan.close();
	}
	
	private static int getJ(int i, int upLen) {
		// i가 up의 index 보다 크거나 같은 경우 index는 0부터 시작
		if (i >= upLen - 1) {
			return 0;
		}
		// i가 up의 길이보다 짧은 경우에는 j > 0인 지점부터 시작
		else {// if (i < up.length() - 1) {
			return upLen - i - 1;
		}	
	}
	
	private static int getK(int i, int upLen) {
		if (i >= upLen - 1)
			return i - upLen + 1;
		else
			return 0;
	}
	
	private boolean checkLine(int j, int k) {
		for (; j < up.length() && k < down.length(); j++, k++) {
			if (up.charAt(j) == '2' && down.charAt(k) == '2') {
				return false;
			}
		}
		return true;
	}
	
	public int check(String up, String down) {
		int min = up.length() + down.length();
		for (int i = 0; i <= up.length() + down.length() - 2; i++) {
			// i가 down의 길이보다 작거나 같은 경우
			if (i <= down.length() - 1) {
				// j는 up의 index, k는 down의 index
				int j = getJ(i, up.length());
				int k = getK(i, up.length());
				// 기어가 정상적으로 맞물리는 경우
				if (checkLine(j, k) == true) {
					int tempMin = i >= up.length() - 1 ? down.length() : up.length() + down.length() - i - 1;
					if (min > tempMin)
						min = tempMin;
				}
			}
			// i가 down의 길이보다 긴 경우
			else {
				// j는 up의 index, k는 down의 index
				int k = getK(i, up.length());
				// 기어가 정상적으로 맞물리는 경우
				if (checkLine(0, k)) {
					if (min > i + 1) {
						min = i + 1;
					}
				}
			}
		}
		return min;
	}

	public void solve() {
		if (up.length() > down.length()) {
			String temp = this.up;
			this.up = this.down;
			this.down = temp;
		}
		int min = check(up, down);
		System.out.println(min);
	}
}

