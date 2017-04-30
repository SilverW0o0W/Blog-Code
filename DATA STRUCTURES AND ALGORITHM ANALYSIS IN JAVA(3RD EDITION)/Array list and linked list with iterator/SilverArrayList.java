public class SilverArrayList<T> implements Iterable<T> {

	private static final int DEFAULT_CAPACITY = 10;

	private int size;
	private T[] items;

	public SilverArrayList() {
		clear();
	}

	private void clear() {
		size = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void trimToSize() {
		ensureCapacity(size());
	}

	public T get(int index) {
		if (index < 0 || index >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return items[index];
	}

	public T set(int index, T value) {
		if (index < 0 || index >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		T old = items[index];
		items[index] = value;
		return old;
	}

	@SuppressWarnings("unchecked")
	private void ensureCapacity(int newCapacity) {
		if (newCapacity < size) {
			return;
		}
		T[] old = items;
		items = (T[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			items[i] = old[i];
		}
	}

	public boolean add(T value) {
		add(size, value);
		return true;
	}

	public void add(int index, T value) {
		if (items.length == size) {
			ensureCapacity(size * 2 + 1);
		}
		for (int i = size; i > index; i--) {
			items[i] = items[i - 1];
		}
		items[index] = value;
		size++;
	}

	public T remove(int index) {
		T removedItem = items[index];
		for (int i = index; i < size - 1; i++) {
			items[i] = items[i + 1];
		}

		size--;
		return removedItem;
	}

	public java.util.Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements java.util.Iterator<T> {
		private int current = 0;

		public boolean hasNext() {
			return current < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			return items[current++];
		}

		public void remove() {
			SilverArrayList.this.remove(--current);
		}
	}
}
