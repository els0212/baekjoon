import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.getArgs();
		bank.solve();
	}
}

class Bank{
	private String n, m;

	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.n = scan.next();
		this.m = scan.next();
		scan.close();
	}

	public void solve() {
		BigInteger bigN = new BigInteger(n);
		BigInteger bigM = new BigInteger(m);
		System.out.println(bigN.divide(bigM));
		System.out.println(bigN.mod(bigM));
	}
}

