package baekjoon;

import java.util.Scanner;

public class Main {
	long width;
	long height;
	long f;
	long c;
	long x1;
	long y1;
	long x2;
	long y2;
	long verticalBoxNumbers;
	long horizonBoxNumbers;
	
	public static void main(String[] args){
		Main answer = new Main();
		answer.getArgs();
		answer.getHorizonBoxNumbers();
		answer.getVerticalBoxNumbers();
		System.out.println(answer.solve());
	}
	public long solve() {
		return ((this.height * this.width) 
				- (this.verticalBoxNumbers * this.horizonBoxNumbers));
	}
	public void getVerticalBoxNumbers(){
		long boxHeight = this.y2 - this.y1;
		this.verticalBoxNumbers = c + 1;
		this.verticalBoxNumbers *= boxHeight;
	}
	public void getHorizonBoxNumbers()
	{
		// 오른쪽 영역에 색칠되는 부분
		this.horizonBoxNumbers = (this.x2 - this.x1);
		
		// 왼쪽 영역의 경우 얼마나 겹치는지 구해야 한다.
		// 1. 왼쪽보다 오른쪽이 크거나 같은 경우 
		if (this.f <= this.width - this.f) {
			if (this.f > this.x1)
				this.horizonBoxNumbers += (Math.min(this.f, this.x2) - this.x1);
		}
		// 2. 오른쪽이 왼쪽보다 큰 경우
		else {
			if (this.width - this.f > this.x1)
				this.horizonBoxNumbers += (Math.min(this.width - this.f, this.x2) - this.x1);
		}
	}

	public void getArgs()
	{
		Scanner sc = new Scanner(System.in);
		this.width = sc.nextLong();
		this.height = sc.nextLong();
		this.f = sc.nextLong();
		this.c = sc.nextLong();
		this.x1 = sc.nextLong();
		this.y1 = sc.nextLong();
		this.x2 = sc.nextLong();
		this.y2 = sc.nextLong();
		sc.close();
	}
}

