import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Painting painting = new Painting();
		painting.getArgs();
		painting.solve();
	}
}

class Painting{
	private int n, k;
	// row 1개씩 drawing
	int map[] = new int[20002];
	private LinkedList<Rectangle> rectangles = new LinkedList<Rectangle>();
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		this.k = scan.nextInt();
		for (int i = 1; i <= this.n; i++) {
			int x1 = scan.nextInt();
			int y1 = scan.nextInt();
			int x2 = scan.nextInt();
			int y2 = scan.nextInt();
			// index, x1, x2, y1, y2
			rectangles.add(new Rectangle(i, x1, x2, y1, y2));
		}
		scan.close();
	}
	public void init() {
		for (int i = 0; i < 20002; i++)
			map[i] = 0;
	}
	public void solve() {
		int minY1 = 10000;
		int maxY2 = -10000;
		
		for (Rectangle r : rectangles) {
			if (r.getY1() < minY1) {
				minY1 = r.getY1();
			}
			if (r.getY2() > maxY2) {
				maxY2 = r.getY2();
			}
			
		}
		for (int i = minY1; i < maxY2; i++) {
			init();
			for (int k = 0; k < rectangles.size(); k++) {
				Rectangle test = rectangles.get(k);
				test.drawLine(map, i);
			}
			for (int k = 0; k < 20002; k++) {
				if (map[k] != 0) {
					rectangles.get(map[k] - 1).increaseArea();
				}
			}
		}
		rectangles.sort(null);
		while (rectangles.size() != k) {
			rectangles.remove(k);
		}
		Collections.sort(rectangles, new Comparator<Rectangle>() {
			@Override
			public int compare(Rectangle r1, Rectangle r2) {
				if (r1.getName() < r2.getName())
					return -1;
				else if (r1.getName() == r2.getName())
					return 0;
				else
					return 1;
			}
		});
		for (Rectangle r : rectangles) {
			System.out.print(String.format("%d ",r.getName()));
		}
	}
}

class Rectangle implements Comparable<Rectangle>{
	private int index, x1, x2, y1, y2, area;
	
	public Rectangle(int index, int x1, int x2, int y1, int y2) {
		this.index = index;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.area = 0;
	}
	public int getX1() {return this.x1;}
	public int getX2() {return this.x2;}
	public int getY1() {return this.y1;}
	public int getY2() {return this.y2;}
	public int getName() {return this.index;}

	public void increaseArea() {this.area++;}
	public void drawLine(int map[], int j) {
		if (j < this.y1 || j >= this.y2)
			return ;
		else {
			int end = 10000 + this.x2;
			for (int i = 10000 + this.x1; i < end; i++) {
				map[i] = this.index;
			}
		}
	}
	@Override
	public int compareTo(Rectangle o) {
		if (this.area > o.area) {
			return -1;
		}
		else if (this.area == o.area) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
