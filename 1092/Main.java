import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Crane crane = new Crane();
		crane.getArgs();
		crane.solve();
	}
}

class Crane{
	private int n, m, ans = -1;
	private ArrayList<Integer> limit = new ArrayList<Integer>();
	private ArrayList<Integer> weight = new ArrayList<Integer>();
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		for (int i = 0; i < this.n; i++) {
			limit.add(scan.nextInt());
		}
		this.m = scan.nextInt();
		for (int i = 0; i < this.m; i++) {
			weight.add(scan.nextInt());	
		}
		scan.close();
	}
	
	public int countSmall(int limit, int target) {
		int cnt = 0;
		for (int i = 0; i < weight.size(); i++) {
			if (weight.get(i) <= limit)
				cnt++;
			if (cnt == target)
				break ;
		}
		return cnt;
	}
	public void recursive(int m, int n) {
		if (n == 0)
			return ;
		int mod = m % n;
		int slot = m / n;
		int cntSmall = countSmall(limit.get(0), slot + 1);
		if (slot > cntSmall)
			slot = cntSmall;
		// slot <= cntSmall
		else {
			if (mod > 0 && cntSmall >= slot + 1)
				slot++;
			if (slot > this.ans)
				this.ans = slot;
		}
		for (int i = 0; i < slot; i++)
			weight.remove(0);
		limit.remove(0);
		recursive(m - slot, n - 1);
	}
	
	public void solve() {
		int craneMax = -1;
		for (int i = 0; i < this.n; i++) {
			int nowLimit = limit.get(i);
			if (nowLimit > craneMax)
				craneMax = nowLimit;
		}
		int boxMax = -1;
		for (int i = 0; i < this.m; i++) {
			int nowWeight = weight.get(i);
			if (nowWeight > boxMax)
				boxMax = nowWeight;
		}
		if (craneMax >= boxMax) {
			weight.sort(null);
			limit.sort(null);
			int firstWeight = weight.get(0);
			int delIndex = -1;
			for (int i = 0; i < limit.size(); i++) {
				if (limit.get(i) < firstWeight)
					delIndex = i;
			}
			
			for (int i = 0; i <= delIndex; i++)
				limit.remove(0);
			this.n = limit.size();
			recursive(m, n);
		}
		System.out.println(ans);
	}
}
