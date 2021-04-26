import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.getArgs();
		sol.solve();
	}
}

class Solution{
	private int n;
	private int[] numbers;
	private boolean[] visited;
	private int[] ans;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = scan.nextInt();
		}
		ans = new int[2 * n];
		for (int i = 0; i < 2 * n; i++)
			ans[i] = -1;
		scan.close();
	}
	public boolean isAllVisited() {
		for (int i = 0; i < n; i++) {
			if (visited[i] == false)
				return false;
		}
		return true;
	}

	public boolean dfs(int num) {
		int ansPtr = 0;
		while (ans[ansPtr] >= 0)
			ansPtr++;
		if (ansPtr == 2 * n)
			return false;
		else {
			if (ansPtr + num + 1 >= 2 * n || ans[ansPtr] != -1 || ans[ansPtr + num + 1] != -1)
				return false;
			else {
				ans[ansPtr] = num;
				ans[ansPtr + num + 1] = num;
			}
		}
		if (isAllVisited() == true) {
			return true;
		}
		for (int i = 0; i < n; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				if (dfs(numbers[i]) == true)
					return true;
				visited[i] = false;
			}
		}
		ans[ansPtr] = -1;
		ans[ansPtr + num + 1] = -1;
		return false;
	}

	public void solve() {
		Arrays.sort(numbers);
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			visited[i] = true;
			if (dfs(numbers[i]) == true)
				break;
			visited[i] = false;
		}
		if (isAllVisited() == true) {
			for (int num : ans) {
				System.out.print(num + " ");
			}
		} else {
			System.out.println(-1);
		}
	}
}
