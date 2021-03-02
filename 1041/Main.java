import java.util.Arrays;
import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		long N;
		long sum = 0;
		long[] arr = new long[7];
		long[] min = new long[7];
		N = scan.nextLong();
		for(int i = 1; i <= 6; i++)
				arr[i] = scan.nextLong();
		min[1] = arr[1];
		for(int i = 1; i <= 6; i++)
		{
			if (min[1] > arr[i])
				min[1] = arr[i];
			for(int j = i + 1; j <= 6; j++)
			{
					if (i + j == 7)
						continue;
					if (min[2] == 0)
						min[2] = arr[i] + arr[j];
					else if (min[2] > arr[i] + arr[j])
						min[2] = arr[i] + arr[j];
					for(int k = j + 1; k <= 6; k++)
					{
						if (i + k == 7 || j + k == 7)
							continue;
						else if (min[3] == 0)
							min[3] = arr[i] + arr[j] + arr[k];
						else if (min[3] > arr[i] + arr[j] + arr[k])
							min[3] = arr[i] + arr[j] + arr[k];
					}
			}
		}

		if (N == 1)
		{
			Arrays.sort(arr);
			for(int i = 1; i <= 6; i++)
				arr[i] += arr[i - 1];
			sum = arr[5];
		}
		else
		{
			long midCnt = (N - 2) * 4;
			long otherCnt = N * N - midCnt - 4;
			
			long upperCorner = min[3] * 4;
			long upperMid = min[2] * midCnt;
			long upperOther = min[1] * otherCnt;
			sum += (upperCorner + upperMid + upperOther);
			long otherSum = (N - 1) * (min[2] * 4 + min[1] * midCnt);
			sum += otherSum;
		}
		System.out.println(sum);
		scan.close();
	}
}
