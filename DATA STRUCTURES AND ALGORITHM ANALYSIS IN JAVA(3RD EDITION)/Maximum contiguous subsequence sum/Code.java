import com.sun.javafx.geom.Matrix3f;

public class Code {

	/*
	 * Cubic maximum contiguous subsequence sum algorithm.
	 */
	public static int maxSubSum1(int[] a) {
		int maxSum = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				int thisSum = 0;
				for (int k = i; k <= j; k++) {
					thisSum += a[k];
				}
				if (thisSum > maxSum) {
					maxSum = thisSum;
				}
			}
		}
		return maxSum;
	}

	/*
	 * Quadratic maximum contiguous subsequence sum algorithm.
	 */
	public static int maxSubSum2(int[] a) {
		int maxSum = 0;
		for (int i = 0; i < a.length; i++) {
			int thisSum = 0;
			for (int j = i; j < a.length; j++) {
				thisSum += a[j];
				if (thisSum > maxSum) {
					maxSum = thisSum;
				}
			}
		}
		return maxSum;
	}

	/*
	 * Recursive maximum contiguous subsequence sum algorithm. 
	 * Finds maximum sum in subarray spanning a[left...right]. 
	 * Does not attempt to maintain actual best sequence.
	 */
	private static int maxSumRec(int[] a, int left, int right) {
		// Base case
		if (left == right) {
			if (a[left] > 0) {
				return a[left];
			} else {
				return 0;
			}
		}
		int center = (left + right) / 2;
		int maxLeftSum = maxSumRec(a, left, center);
		int maxRightSum = maxSumRec(a, center + 1, right);

		int maxLeftBorderSum = 0, leftBorderSum = 0;
		for (int i = center; i >= left; i--) {
			leftBorderSum += a[i];
			if (leftBorderSum > maxLeftBorderSum) {
				maxLeftBorderSum = leftBorderSum;
			}
		}

		int maxRightBorderSum = 0, rightBorderSum = 0;
		for (int i = center + 1; i <= right; i++) {
			rightBorderSum += a[i];
			if (rightBorderSum > maxRightBorderSum) {
				maxRightBorderSum = rightBorderSum;
			}
		}

		return max3(maxLeftSum, maxRightSum,
				maxLeftBorderSum + maxRightBorderSum);
	}

	private static int max3(int max, int b, int c) {
		if (b > max) {
			max = b;
		}
		if (c > max) {
			max = c;
		}
		return max;
	}

	/*
	 * Driver for divide-and-conquer maximum contiguous subsequence sum algorithm.
	 */
	public static int maxSubSum3(int[] a) {
		return maxSumRec(a, 0, a.length - 1);
	}

	/*
	 * Linear-time maximum contiguous subsequence sum algorithm.
	 */
	public static int maxSubSum4(int[] a) {
		int maxSum = 0, thisSum = 0;
		for (int i = 0; i < a.length; i++) {
			thisSum += a[i];

			if (thisSum > maxSum) {
				maxSum = thisSum;
			} else if (thisSum < 0) {
				thisSum = 0;
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[] a = {4, -3, 5, -2, -1, 2, 6, -2};
		int[] b = {4, -3, 5, -2, -1, 2, 6, -2};
		int[] c = {4, -3, 5, -2, -1, 2, 6, -2};
		int[] d = {4, -3, 5, -2, -1, 2, 6, -2};
		System.out.println(maxSubSum1(a));
		System.out.println(maxSubSum2(b));
		System.out.println(maxSubSum3(c));
		System.out.println(maxSubSum4(d));
	}
}
