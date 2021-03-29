import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Rectangle rectangle = new Rectangle();
		rectangle.getArgs();
		rectangle.solve();
	}
}

class Rectangle{
	private int n, m;
	private long map[][] = new long[100][100];
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.m = scan.nextInt();
		for (int i = 0; i < this.n; i++) {
			String str = scan.next();
			for (int j = 0; j < this.m; j++)
				map[i][j] = str.charAt(j) - '0';
		}
		scan.close();	
	}
	public void solve() {
		long maxValue = 0;
		
		//1. 직사각형을 동일한 height의 3개 blocks로 나누는 방법
		if (this.m >= 3) {
			long firstMaxCandidate = divThreeVerticals();
			
			if (firstMaxCandidate >= maxValue)
				maxValue = firstMaxCandidate;
		}
		//2. 직사각형을 동일한 width의 3개 blocks로 나누는 방법
		if (this.n >= 3) {
			long secondMaxCandidate = divThreeHorizontals();
			
			if (secondMaxCandidate >= maxValue)
				maxValue = secondMaxCandidate;
		}
		// 3. 직사각형을 왼쪽에 수직으로 서있는 block과 오른쪽에 horizontal block 2개로 나누는 방법 (|=)
		// 4. 직사각형을 왼쪽에 horizontal block 2개와 오른쪽에 수직으로 서있는 block으로 나누는 방법 (=|)
		if (this.n >= 2 && this.m >= 2) {
			long thirdMaxCandidate = divLeftVerticalRightTwoHorizontals();
			long fourthMaxCandidate = divRightVerticalLeftTwoHorizontals();
			long fifthMaxCandidate = divUpperHozitontalLowerTwoVerticals();
			long sixthMaxCandidate = divUpperTwoVerticalsLowerHorizontal();
			if (thirdMaxCandidate >= maxValue)
				maxValue = thirdMaxCandidate;
			if (fourthMaxCandidate >= maxValue)
				maxValue = fourthMaxCandidate;
			if (fifthMaxCandidate >= maxValue)
				maxValue = fifthMaxCandidate;
			if (sixthMaxCandidate >= maxValue)
				maxValue = sixthMaxCandidate;
		}
		System.out.println(maxValue);
	}
	private long getSum(int w_st, int w_ed, int h_st, int h_ed){
		long ret = 0;
		for (int i = w_st; i < w_ed; i++) {
			for (int j = h_st; j < h_ed; j++) {
				ret += map[j][i];
			}
		}
		return ret;
	}
	private long divThreeVerticals() {
		long firstSum, secondSum, thirdSum;
		long maxValue = 0;
		for (int first = 1; first <this.m - 1; first++) {
			for (int second = first + 1; second < this.m; second++) {
				firstSum = getSum(0, first, 0, this.n);
				secondSum = getSum(first, second, 0, this.n);
				thirdSum = getSum(second, this.m, 0, this.n);
				maxValue = Math.max(maxValue, firstSum * secondSum * thirdSum);			
			}
		}
		return maxValue;
	}
	private long divThreeHorizontals() {
		long firstSum, secondSum, thirdSum;
		long maxValue = 0;
		
		for (int first = 1; first < this.n - 1; first++) {
			for (int second = first + 1; second < this.n; second++) {
				firstSum = getSum(0, this.m, 0, first);
				secondSum = getSum(0, this.m, first, second);
				thirdSum = getSum(0, this.m, second, this.n);
				maxValue = Math.max(maxValue, firstSum * secondSum * thirdSum);
			}
		}
		return maxValue;
	}
	private long divLeftVerticalRightTwoHorizontals() {
		long maxValue = 0;
		long verticalSum, horizonUpperSum, horizonLowerSum;
		
		for (int vertical = 1; vertical < this.m; vertical++){
			verticalSum = getSum(0, vertical, 0, this.n);
			for (int i = 1; i < this.n; i++) {
				horizonUpperSum = getSum(vertical, this.m, 0, i);
				horizonLowerSum = getSum(vertical, this.m, i, this.n);
				maxValue = Math.max(maxValue, verticalSum * horizonUpperSum * horizonLowerSum);
			}
		}
		return maxValue;
	}
	private long divRightVerticalLeftTwoHorizontals() {
		long maxValue = 0;
		long verticalSum, horizonUpperSum, horizonLowerSum;
		
		for (int vertical = this.m - 1; vertical > 0; vertical--) {
			verticalSum = getSum(vertical, this.m, 0, this.n);
			for (int i = 1; i < this.n; i++) {
				horizonUpperSum = getSum(0, vertical, 0, i);
				horizonLowerSum = getSum(0, vertical, i, this.n);
				maxValue = Math.max(maxValue, verticalSum * horizonUpperSum * horizonLowerSum);
			}
		}
		return maxValue;
	}
	private long divUpperHozitontalLowerTwoVerticals() {
		long maxValue = 0;
		long horizontalSum, verticalLeftSum, verticalRightSum;
		
		for (int horizontal = 1; horizontal < this.n; horizontal++) {
			horizontalSum = getSum(0, this.m, 0, horizontal);
			for (int vertical = 1; vertical < this.m; vertical++) {
				verticalLeftSum = getSum(0, vertical, horizontal, this.n);
				verticalRightSum = getSum(vertical, this.m, horizontal, this.n);
				maxValue = Math.max(maxValue, horizontalSum * verticalLeftSum * verticalRightSum);
			}
		}
		return maxValue;
	}
	private long divUpperTwoVerticalsLowerHorizontal() {
		long maxValue = 0;
		long horizontalSum, verticalLeftSum, verticalRightSum;
		
		for (int horizontal = this.n - 1; horizontal > 0; horizontal--) {
			horizontalSum = getSum(0, this.m, horizontal, this.n);
			for (int vertical = 1; vertical < this.m; vertical++) {
				verticalLeftSum = getSum(0, vertical, 0, horizontal);
				verticalRightSum = getSum(vertical, this.m, 0, horizontal);
				maxValue = Math.max(maxValue, horizontalSum * verticalLeftSum * verticalRightSum);
			}
		}
		return maxValue;
	}
}
