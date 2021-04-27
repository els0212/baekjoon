import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.getArgs();
		sol.solve();
	}
}

class Solution{
	private String nStr;
	private int n, initLen;
	private int[] arr;
	private int[] mod;
	private boolean isAnswer = false;
	
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		nStr = scan.next();
		initLen = nStr.length();
		arr = new int[initLen];
		for (int i = 0; i < initLen; i++)
			arr[i] = nStr.charAt(i) - '0';
		scan.close();
	}
	
	public int[] append(int[] input, int num) {
		int[] last = input.clone();
		for (int j = 0; j < initLen; j++) {
			if (arr[j] != 0) {
				last[j] = (last[j] * (10 % arr[j]) + num % arr[j]) % arr[j];
			}
		}
		return last;
	}

	public boolean check(int[] input) {
		int sum = 0;
		for (int j = 0; j < initLen; j++) {
				sum += input[j];
		}
		if (sum == 0)
			return true;
		else
			return false;
	}

	public boolean dfs(int[] prev, int cnt) {
		if (cnt == 0) {
			if (check(prev) == true) {
				return true;
			}
			else
				return false;
		}
		for (int i = 0; i <= 9; i++) {
			int[] temp = append(prev, i);
			nStr += (char)(i + '0');
			if (dfs(temp, cnt - 1) == true) {
				return true;
			}
			nStr = nStr.substring(0, nStr.length() - 1);
		}
		return false;
	}
	public void solve() {
		n = Integer.parseInt(nStr);
		mod = new int[nStr.length()];
		boolean isAnswer = false;
		int cnt = 1;
		for (int i = 0; i < initLen; i++) {
            if (arr[i] != 0)
			    mod[i] = n % arr[i];
		}
		isAnswer = check(mod);
		while (isAnswer == false) {
			isAnswer = dfs(mod, cnt++);
		}
		System.out.println(nStr);
	}
}
