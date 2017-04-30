public class SilverLinkedList<T> implements Iterable<T> {

	private int size;
	private int modCount = 0;
	private Node<T> beginMarker;
	private Node<T> endMarker;

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public SilverLinkedList() {
		clear();
	}

	public void clear() {
		beginMarker = new Node<T>(null, null, null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;

		size = 0;
		modCount++;
	}

	public boolean add(T value) {

		return true;
	}

	public void add(int index, T value) {
		addBefore(getNode(index, 0, size), value);
	}

	public T get(int index) {
		return getNode(index).data;
	}

	public T set(int index, T value) {
		Node<T> p = getNode(index);
		T oldValue = p.data;
		p.data = value;
		return oldValue;
	}

	public T remove(int index) {
		return remove(getNode(index));
	}

	private void addBefore(Node<T> prev, T value) {
		Node<T> newNode = new Node<>(value, prev.prev, prev);
		newNode.prev.next = newNode;
		prev.prev = newNode;
		size++;
		modCount++;
	}

	private T remove(Node<T> prevNode) {
		prevNode.next.prev = prevNode.prev;
		prevNode.prev.next = prevNode.next;
		size--;
		modCount++;

		return prevNode.data;
	}

	private Node<T> getNode(int index) {
		return getNode(index, 0, size - 1);
	}

	private Node<T> getNode(int index, int lower, int upper) {
		Node<T> prevNode;
		if (index < lower || index > upper) {
			throw new IndexOutOfBoundsException();
		}

		if (index < size / 2) {
			prevNode = beginMarker.next;
			for (int i = 0; i < index; i++) {
				prevNode = prevNode.next;
			}
		} else {
			prevNode = endMarker;
			for (int i = size; i > index; i--) {
				prevNode = prevNode.prev;
			}
		}

		return prevNode;
	}

	private static class Node<T> {
		public T data;
		public Node<T> prev;
		public Node<T> next;

		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	@Override
	public java.util.Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements java.util.Iterator<T> {
		private Node<T> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;

		public boolean hasNext() {
			return current != endMarker;
		}

		public T next() {
			if (modCount != expectedModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}

			T nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (modCount != expectedModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			if (!okToRemove) {
				throw new IllegalStateException();
			}

			SilverLinkedList.this.remove(current.prev);
			expectedModCount++;
			okToRemove = false;
		}
	}
}
