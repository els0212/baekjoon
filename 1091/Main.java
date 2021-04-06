import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Card card = new Card();
		card.getArgs();
		card.solve();
	}
}

class Card{
	private int n, ans = 0;
	private int p[] = new int[48]; // Initial state
	private int finalState[] = new int[48]; // Target state
	private int s[] = new int[48];
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		
		this.n = scan.nextInt();
		for (int i = 0; i < this.n; i++) {
			p[i] = scan.nextInt();
		}
		for (int i = 0; i < this.n; i++) {
			s[i] = scan.nextInt();
		}
		scan.close();
		for (int i = 0; i < this.n; i++) {
			finalState[i] = i % 3; 
		}
	}
	
	public boolean compareArray(int src[], int dst[]) {
		for (int i = 0; i < this.n; i++) {
			if (src[i] != dst[i])
				return false;
		}
		return true;
	}
	
	public void swap() {
		
	}
	
	public boolean recursive(int state[]) {
		int tempState[] = state.clone();
		// swap
		for (int i = 0; i < this.n; i++) {
			tempState[s[i]] = state[i];
		}
		state = tempState;
		ans++;
		if (compareArray(state, p) == true)
			return false;
		//check after swap
		if (compareArray(state, finalState) == true)
			return true;
		// if state and finalState is not equal, do swap
		else {
			return recursive(state);
		}
	}
	
	public void solve() {
		if (compareArray(p, finalState) == false) {
			int state[] = p.clone();
			if (recursive(state) == false)
				this.ans = -1;
		}
		System.out.println(ans);
	}
}
