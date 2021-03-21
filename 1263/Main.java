import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		JobList jobs = new JobList();
		Scanner scan = new Scanner(System.in);
		int maxTime = 1000000;
		int cnt = scan.nextInt();
		for(int i = 0; i < cnt; i++) {
			int ti = scan.nextInt();
			int si = scan.nextInt();
			jobs.insertJob(new Job(ti, si));
		}
		jobs.sortJobs();
		scan.close();
		while (jobs.hasNext()) {
			Job now = jobs.next();
			int si = now.getSi();
			if (si < maxTime)
				maxTime = si;
			if (maxTime - now.getTi() < 0) {
				maxTime = -1;
				break ;
			}
			else {
				maxTime -= now.getTi();
			}
		}
		System.out.println(maxTime);
	}
}

class JobList implements Iterator<Job>{
	private LinkedList<Job> jobs;
	private int index = 0;
	public JobList() {this.jobs = null;}
	public JobList(LinkedList<Job> jobs) {this.jobs = jobs;}
	public LinkedList<Job> getJobs(){ return this.jobs;}
	public void insertJob(Job job) {
		if (jobs == null) {
			this.jobs = new LinkedList<Job>();
		}
		this.jobs.add(job);
	}
	public void sortJobs() {
		Collections.sort(this.jobs);
	}
	@Override
	public boolean hasNext() {
		if (this.index == jobs.size())
			return false;
		else
			return true;
	}
	@Override
	public Job next() {
		return jobs.get(index++);
	}
}

class Job implements Comparable<Job>{
	private int ti;
	private int si;
	public Job(int ti, int si) {this.ti = ti; this.si = si; }
	public int getTi() { return this.ti;}
	public int getSi() {return this.si; }
	
	@Override
	public int compareTo(Job o) {
		if (this.si < o.si) {
			return 1;
		}
		else if (this.si == o.si)
			return 0;
		else
			return -1;
	}
}
