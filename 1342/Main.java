import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Lucky lucky = new Lucky();
		lucky.getArgs();
		lucky.solve();
	}
}

class Lucky{
	private String s;
	private Map<String, Integer> alphabet = new HashMap<String, Integer>();
	private int luckyCount = 0;
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.s = scan.next();
		scan.close();
		for (int i = 0; i < s.length(); i++) {
			String key = Character.toString(s.charAt(i));
			if (alphabet.get(key) == null) {
				alphabet.put(key, 1);
			}
			else {
				alphabet.replace(key, alphabet.get(key) + 1);
			}
		}
	}
	
	public void dfs(String str) {
		if (str.length() == s.length()) {
			this.luckyCount++;
			return ;
		}
		// alphabet에 들어있는 모든 key에 대해서 loop를 수행
		for (String key : alphabet.keySet()) {
			// loop를 돌면서 이전까지 만든 str에 영향을 미치면 안되므로 임시 string을 만듬
			String tempString = str;
			int value = alphabet.get(key);
			// 이미 key가 모두 사용된 경우에는 해당 key는 건너뜀
			if (value <= 0) {
				continue ;
			}
			else {
				// 만들어야 하는 tempString이 비어있거나
				if ((tempString.isEmpty() == true)
						// 혹은 tempString에 가장 마지막에 넣은 character와 key가 다른 경우에만
						|| (tempString.charAt(tempString.length() - 1) != key.charAt(0))) {
						// key의 value를 1 감소시킨 후 map의 value를 replace
					alphabet.replace(key, --value);
					// tempString에 key를 이어붙임
					tempString += key;
					// 현재까지 만든 tempString을 parameter로 넘겨서 다음 loop를 호출
					dfs(tempString);
					// loop에서 빠져나온 후에는 다시 key의 value를 1 증가시켜줌
					alphabet.replace(key, ++value);
				}
			}
		}		
	}
	
	public void solve() {
		dfs("");
		System.out.println(luckyCount);
	}
}
