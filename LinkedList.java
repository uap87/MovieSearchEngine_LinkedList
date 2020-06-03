package project3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a generic LinkedList. It consists of one constructor,
 * two private classes, and a series of methods. LinkedList objects are
 * initially empty.
 * 
 * @author Udit Patel
 * @version 10/19/2018
 */
public class LinkedList<E> implements Collection<E> {

	private Node<E> head = null;
	private Node<E> tail = null;

	/**
	 * Constructs a new LinkedList object
	 */
	public LinkedList() {
	}

	/**
	 * This class represents a generic Node. It consists of one constructor. Each
	 * Node has data inside of it and a reference to the next Node.
	 * 
	 * @author Udit Patel
	 * @version 10/19/2018
	 */
	private static class Node<E> {
		private E data;
		Node<E> next;

		/**
		 * Constructs a new Node object with element assigned to data and next Node
		 * assigned to next
		 * 
		 * @param element element that is stored in the Node object
		 * @param next    next Node that will be reference from this Node
		 */
		Node(E element, Node<E> next) {
			this.data = element;
			this.next = next;
		}
	}

	/**
	 * This class represents an Iterator It consists of one constructor, and three
	 * methods. Iterators start at the head of a LinkedList. They are able to test
	 * whether there is a next Node and also return it if called.
	 * 
	 * @author Udit Patel
	 * @version 10/19/2018
	 */
	private class Itr implements Iterator<E> {
		private Node<E> current;

		/**
		 * Constructs a new Iterator object with head assigned to the current Node
		 */
		public Itr() {
			this.current = head;
		}

		/**
		 * Determines if the Iterator has a next element
		 * 
		 * @return true if there is a next element; false if not
		 */
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * Moves the Iterator to the next element while returning the current element
		 * 
		 * @return the current element before this method is called
		 */
		public E next() {
			// check to make sure there is a next element
			if (!hasNext()) {
				throw new NoSuchElementException("No element exists");
			}
			// save the current element
			E temp = current.data;

			// go to next element
			current = current.next;

			// return the current element that was saved
			return temp;
		}

		/**
		 * The remove operation is not supported by this implementation of
		 * {@code Iterator}.
		 *
		 * @throws UnsupportedOperationException if this method is invoked.
		 * @see java.util.Iterator
		 */
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 * 
	 * @param o element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	public int indexOf(Object o) {
		// iterate through this list and return the index at which the element o is
		// found
		for (int i = 0; i < size(); i++) {
			if ((o == null ? get(i) == null : o.equals(get(i)))) {
				return i;
			}
		}

		// if element does not exist in this list, return -1
		return -1;
	}

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index of the element to return; should not be negative or greater than
	 *              or equal to the size of list
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if index parameter is invalid
	 */
	public E get(int index) throws IndexOutOfBoundsException {
		// if index is negative or greater than or equal to the size of this list, throw
		// IndexOutOfBoundsException
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index requested is not valid");
		}

		// iterate through this list until the index is reached
		Node<E> temp = head;
		for (int k = 0; k < index; k++) {
			temp = temp.next;
		}

		// return the element at the index of this list
		return temp.data;
	}

	/**
	 * Returns a string representation of this collection.
	 * 
	 * @return a string representation of this collection
	 */
	@Override
	public String toString() {
		// create new StringBuilder
		StringBuilder stringOfList = new StringBuilder();

		// add all of the element in this list with a comma and a space except for the
		// last element
		Node<E> current = head;
		stringOfList.append("[");
		for (int i = 0; i < size() - 1; i++) {
			stringOfList.append(String.valueOf(current.data) + ", ");
			current = current.next;
		}

		// add the last element without a comma and space
		stringOfList.append(String.valueOf(current.data));
		stringOfList.append("]");

		// return the string
		return stringOfList.toString();
	}

	/**
	 * Sorts the list in the natural order
	 */
	@SuppressWarnings("unchecked")
	public void sort() {
		// create an array from this list
		Object[] array = toArray();

		// sort the array created
		Arrays.sort(array);

		// clear this list
		this.clear();

		// iterate through the sorted array and add the elements back into this list
		for (Object o : array) {
			this.add((E) o);
		}
	}

	/**
	 * Returns the number of elements in this collection
	 * 
	 * @return the number of elements in this collection
	 */
	@Override
	public int size() {
		// iterate through and add 1 to the size int for each element present in this
		// list
		int size = 0;
		Node<E> current = head;
		while (current != null) {
			size++;
			current = current.next;
		}

		// return the total size after the iterations
		return size;
	}

	/**
	 * Returns true if this collection contains no elements, false if not
	 * 
	 * @return true if this collection contains no elements, false if not
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns true if this collection contains the specified element, false if not
	 * 
	 * @param o element to search for
	 * @return true if this collection contains the specified element, false if not
	 */
	@Override
	public boolean contains(Object o) {
		// this list contains the element o if there is an index found for the element o
		return indexOf(o) != -1;
	}

	/**
	 * Returns an iterator over the elements in this collection
	 * 
	 * @return an iterator over the elements in this collection
	 */
	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	/**
	 * Returns an array containing all of the elements in this collection
	 * 
	 * @return an array containing all of the elements in this collection.
	 */
	@Override
	public Object[] toArray() {
		// create an array of Object types with the size of this list
		Object[] arr = new Object[size()];

		// iterate through this list and assign the values to array arr
		for (int i = 0; i < size(); i++) {
			arr[i] = get(i);
		}

		// return the new array a with the elements from this list
		return arr;
	}

	/**
	 * Returns an array containing all of the elements in this collection; the
	 * runtime type of the returned array is that of the specified array
	 * 
	 * @param a specified array that provides the type of array that needs to be
	 *          returned
	 * @return an array of specified type containing all of the elements in this
	 *         collection
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		// if the specified array is too small, create a new instance of
		// an array of T type objects with the size of this list and assign it to array
		// a
		if (a.length < size()) {
			a = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
		}

		// iterate through this list and assign the values to array a
		Node<E> current = head;
		for (int i = 0; i < size(); i++) {
			a[i] = (T) current.data;
			current = current.next;
		}

		// return the new array a with the elements from this list
		return a;
	}

	/**
	 * Adds the element and returns true; if element was not able to be added,
	 * returns false
	 * 
	 * @param e element to be added
	 * @return true if element was added, false if not
	 */
	@Override
	public boolean add(E e) {
		// if element to be added is null, return false, as null elements are not
		// allowed in the list
		if (e == null) {
			return false;
		}

		// create a Node using the e parameter and null as the reference to next
		Node<E> newest = new Node<>(e, null);

		// if this list is empty, assign the new Node created as the head and tail and
		// return true
		if (isEmpty()) {
			head = newest;
			tail = newest;
			return true;
		}

		// if this list is not empty, assign the current tail's reference to next to the
		// new Node created and
		// assign the new Node created to tail and return true
		else {
			tail.next = newest;
			tail = newest;
			return true;
		}
	}

	/**
	 * Removes a single instance of the specified element from this collection, if
	 * it is present and returns true If list is empty or the element is not
	 * present, it will return false
	 * 
	 * @param o element to be removed
	 * @return true if element was removed, false if not
	 */
	@Override
	public boolean remove(Object o) {
		// if this list is empty, return false
		if (head == null) {
			return false;
		}

		// if head contains the element to be removed, shift head to next element and
		// return true
		if (head.data.equals(o)) {
			head = head.next;
			return true;
		}

		Node<E> current = head;
		Node<E> previous = null;

		// iterate through this list until the element is found or the end of this list
		// is reached
		while (current != null && !current.data.equals(o)) {
			previous = current;
			current = current.next;
		}

		// if end of list was reached without finding the element, return false
		if (current == null) {
			return false;
		}

		// delete the element and return true
		previous.next = current.next;
		return true;
	}

	/**
	 * Returns true if this collection contains all of the elements in the specified
	 * collection
	 * 
	 * @param c collection that is being compared to this list
	 * @return true if this collection contains all of the elements in the specified
	 *         collection, false if not
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (!contains(o))
				return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// if this list and obj are identical, return true
		if (this == obj) {
			return true;
		}

		// if obj is null, return false
		if (obj == null) {
			return false;
		}

		// if this list and obj are not of the same class, return false
		if (!(this.getClass() == obj.getClass())) {
			return false;
		}

		// cast obj into a LinkedList object
		LinkedList<?> temp = (LinkedList<?>) obj;

		// if this list and temp list are not the same size, return false
		if (temp.size() != size()) {
			return false;
		}

		// if any of the elements in this list are not equal to the corresponding
		// element in the temp list, return false
		for (int i = 0; i < size(); i++) {
			if (!(((temp.get(i).equals(get(i)))))) {
				return false;
			}
		}

		// if the temp list passes all of the tests above, return true
		return true;
	}

	/**
	 * The addAll operation is not supported by this implementation of
	 * {@code Collection}.
	 *
	 * @throws UnsupportedOperationException if this method is invoked.
	 * @see java.util.Collection
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * The removeAll operation is not supported by this implementation of
	 * {@code Collection}.
	 *
	 * @throws UnsupportedOperationException if this method is invoked.
	 * @see java.util.Collection
	 */
	@Override
	public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * The retainAll operation is not supported by this implementation of
	 * {@code Collection}.
	 *
	 * @throws UnsupportedOperationException if this method is invoked.
	 * @see java.util.Collection
	 */
	@Override
	public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes all of the elements from this collection
	 */
	@Override
	public void clear() {
		head = null;
	}
}
