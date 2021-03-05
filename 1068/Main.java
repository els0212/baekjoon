import java.util.ArrayList;
import java.util.Scanner;
public class Main {
	static int N, del;
	static int[] nodes = new int[50];
	static int[] leafs = new int[50];
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		for(int i = 0; i < N; i++)
			nodes[i] = scan.nextInt();
		del = scan.nextInt();
		ArrayList<Integer> stack = new ArrayList<Integer>();
		stack.add(del);
		while (stack.isEmpty() == false)
		{
			del = stack.get(stack.size() - 1);
			stack.remove(stack.size() - 1);
			nodes[del] = -2;
			for(int i = 0; i < N; i++)
				if (nodes[i] == del)
					stack.add(i);
		}
		int cnt = 0;
		
		for(int i = 0; i < N; i++)
		{
			if (nodes[i] >= 0)
				leafs[nodes[i]]++;
		}
		for(int i = 0; i < N; i++)
		{
			if (nodes[i] != -2 && leafs[i] == 0)
				cnt++;
		}
		System.out.println(cnt);
		scan.close();
	}
}
