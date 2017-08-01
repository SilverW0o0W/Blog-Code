import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<T> {
	/*
	 * Construct the hash table
	 */
	public SeparateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	/*
	 * Construct the hash table
	 * @param size approximate table size
	 */
	@SuppressWarnings("unchecked")
	public SeparateChainingHashTable(int size) {
		theLists = new LinkedList[nextPrime(size)];
		for (int i = 0; i < theLists.length; i++) {
			theLists[i] = new LinkedList<>();
		}
	}

	/*
	 * Insert into the hash table. If the item i already present, the do nothing.
	 * @param x the item to insert.
	 */
	public void insert(T x) {
		List<T> whichList = theLists[myhash(x)];
		if (!whichList.contains(x)) {
			whichList.add(x);

			//Rehash; see Section 5.5
			if (++currentSize > theLists.length) {
				rehash();
			}
		}
	}

	/*
	 * Remove from the hash table.
	 * @param x the item to remove.
	 */
	public void remove(T x) {
		List<T> whichList = theLists[myhash(x)];
		if (!whichList.contains(x)) {
			whichList.remove(x);
			currentSize--;
		}
	}

	/*
	 * Find an item in the hash table.
	 * @param x the item to search for.
	 * @return true if x is not found.
	 */
	public boolean contains(T x) {
		List<T> whichList = theLists[myhash(x)];
		return whichList.contains(x);
	}

	/*
	 * Make the hash table logically empty.
	 */
	public void makeEmpty() {
		for (int i = 0; i < theLists.length; i++) {
			theLists[i].clear();
		}
		currentSize = 0;
	}

	private static final int DEFAULT_TABLE_SIZE = 101;

	private List<T>[] theLists;
	private int currentSize;

	/*
	 * Rehashing for separate chaining hash table.
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		List<T>[] oldLists = theLists;
		//Create new double -sized, empty table
		theLists = new List[nextPrime(2 * theLists.length)];
		for (int j = 0; j < theLists.length; j++) {
			theLists[j] = new LinkedList<>();

			//Copy table over
			currentSize = 0;
			for (int i = 0; i < oldLists.length; i++) {
				for (T item : oldLists[i])
					insert(item);
			}
		}
	}

	private int myhash(T x) {
		int hashVal = x.hashCode();
		hashVal %= theLists.length;
		if (hashVal < 0) {
			hashVal += theLists.length;
		}
		return hashVal;
	}

	/**
	* Internal method to find a prime number at least as large as n.
	* @param n the starting number (must be positive).
	* @return a prime number larger than or equal to n.
	*/
	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2)
			;

		return n;
	}

	/**
	 * Internal method to test if a number is prime.
	 * Not an efficient algorithm.
	 * @param n the number to test.
	 * @return the result of the test.
	 */
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}
}
