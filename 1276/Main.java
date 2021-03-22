package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int sum = 0;
		LinkedList<Pair> list = new LinkedList<Pair>();
		for(int i = 0; i < N; i++) {
			int altitude = scan.nextInt();
			int x1 = scan.nextInt();
			int x2 = scan.nextInt();
			list.add(new Pair(altitude, x1, x2));
		}
		
		for(int i = 0; i < list.size(); i++) {
			Pair now = list.get(i);
			int max = 0;
			for(int j = 0; j < list.size(); j++) {
				Pair temp = list.get(j);
				if (now == temp) {
					continue ;
				}
				if (temp.x1 <= now.x1 && temp.x2 > now.x1) {
					if (temp.altitude >= max && temp.altitude <= now.altitude)
						max = temp.altitude;
				}
			}
			sum += (now.altitude - max);
		}

		for(int i = 0; i < list.size(); i++) {
			Pair now = list.get(i);
			int max = 0;
			for(int j = 0; j < list.size(); j++) {
				Pair temp = list.get(j);
				if (now == temp) {
					continue ;
				}
				if (temp.x1 < now.x2 && temp.x2 >= now.x2) {
					if (temp.altitude >= max && temp.altitude <= now.altitude)
						max = temp.altitude;
				}
			}
			sum += (now.altitude - max);
		}
		System.out.println(sum);
		scan.close();
	}
}

class Pair{
	int altitude;
	int x1;
	int x2;
	
	public Pair(int alt, int x1, int x2) {
		this.altitude = alt;
		this.x1 = x1;
		this.x2 = x2;
	}
}
