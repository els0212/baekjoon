import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		SpanningTree tree = new SpanningTree();
		tree.getArgs();
		tree.solve();
	}
}

class SpanningTree{
	private int v, e;
	private int sum = 0;
	private LinkedList<Edge> edges = new LinkedList<Edge>();
	private int parent[];
	private int setCount[];
	public void getArgs() {
		Scanner scan = new Scanner(System.in);
		this.v = scan.nextInt();
		this.e = scan.nextInt();
		for (int i = 0; i < this.e; i++) {
			int st = scan.nextInt();
			int ed = scan.nextInt();
			int value = scan.nextInt();
			edges.add(new Edge(st, ed, value));
		}
		parent = new int[this.v + 1];
		setCount = new int[this.v + 1];
		scan.close();
		
	}
	public int getParent(int child) {
		if (parent[child] == child)
			return child;
		else {
			parent[child] = getParent(parent[child]);
		}
		return parent[child];
	}
	public void merge(int src, int dst) {
		int srcParent = parent[src];
		int dstParent = parent[dst];
		if (setCount[srcParent] >= setCount[dstParent]) {
			parent[dst] = srcParent;
			setCount[srcParent]++;
			setCount[dstParent]--;
		}
		else {
			parent[src] = dstParent;
			setCount[dstParent]++;
			setCount[srcParent]--;			
		}
	}
	public void solve() {
		edges.sort(null);
		for (int i = 1; i <= v; i++) {
			parent[i] = i;
			setCount[i]++;
		}
		
		for (Edge edge: edges) {
			int src = getParent(edge.getSt());
			int dst = getParent(edge.getEd());
			if (src != dst) {
				sum += edge.getWeight();
				merge(src, dst);
			}
		}
		System.out.println(sum);
	}
}

class Edge implements Comparable<Edge>{
	private int st;
	private int ed;
	private int weight;
	
	public Edge(int st, int ed, int weight) {
		this.st = st;
		this.ed = ed;
		this.weight = weight;
	}
	
	public int getSt() {return this.st;}
	public int getEd() {return this.ed;}
	public int getWeight() {return this.weight;}
	
	@Override
	public int compareTo(Edge edge) {
		if (this.weight > edge.weight)
			return 1;
		else if (this.weight == edge.weight)
			return 0;
		return -1;
	}
}
