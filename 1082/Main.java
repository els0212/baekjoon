import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Room room = new Room();
		room.getArgs();
		room.solve();
	}
}

class Cost implements Comparable<Cost> {
	int key;
	int value;

	public Cost(int key, int value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(Cost o) {
		if (this.value < o.value)
			return -1;
		else if (this.value == o.value)
			return 0;
		return 1;
	}
}

class Room {
	private int n, money;
	private int costArr[] = new int[11];
	private LinkedList<Cost> costs = new LinkedList<Cost>();
	private String sum = "";

	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.nextInt();
		for (int i = 0; i < this.n; i++) {
			int value = scan.nextInt();
			costArr[i] = value;
			costs.add(new Cost(i, value));
		}
		money = scan.nextInt();
		scan.close();
		Collections.sort(costs);
	}

	public void solve() {
		if (n > 1) {
			// 첫 번째 자리에 들어갈 숫자를 찾음
			Cost msd = null;
			for (Cost cost : costs) {
				if (cost.key != 0) {
					if (msd == null) {
						msd = cost;
					} else if (msd.value > cost.value) {
						msd = cost;
					}
				}
			}
			// 만약 1 ~ n -1 사이에서 가장 낮은 가격을 가진 숫자가 예산 초과이면 failure
			if (msd.value > money) {
				sum += "0";
			} else {
				sum += Integer.toString(msd.key);
				money -= msd.value;
				// 0을 포함해서 가장 낮은 가격으로 최대 길이를 만듬
				while ((money - costs.get(0).value) >= 0) {
					sum += Integer.toString(costs.get(0).key);
					money -= costs.get(0).value;
				}
				// 첫 번째 가격부터 시작해서 살 수 있는 가장 큰 수로 교체 
				for (int i = 0; i < sum.length(); i++) {
					int changeIndex = -1;
					int now = sum.charAt(i) - '0';
					money += costArr[now];
					for (int j = 0; j < n; j++) {
						if (costs.get(j).value <= money) {
							if (changeIndex < costs.get(j).key)
								changeIndex = costs.get(j).key;
						} else
							break;
					}
					money -= (costArr[changeIndex]);
					String next = sum.substring(0, i) + Integer.toString(changeIndex);
					if (sum.length() > 1)
						next += sum.substring(i + 1);
					sum = next;
				}
			}
		} else {
			sum += "0";
		}
		System.out.println(sum);
	}
}
