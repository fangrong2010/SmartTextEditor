package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		this.head.next = this.tail;
		this.tail.prev = this.head;
		this.size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException("null pointer");
		LLNode<E> node = new LLNode<E>(element);
		LLNode<E> secondToTail = this.tail.prev; 
		node.prev = secondToTail;
		node.next = this.tail;
		secondToTail.next = node;
		this.tail.prev = node;
		size++; // increment size
		
		return true;
//		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index < 0 || index > this.size-1)
			throw new IndexOutOfBoundsException("index out of bounds");
		LLNode<E> curr = this.head.next;
		while(index > 0){
			curr = curr.next;
			index--;
		}
		return curr.data;
//		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException("null pointer");
		if(index < 0 || index > this.size)
			throw new IndexOutOfBoundsException("index out of bounds");
		LLNode<E> node = new LLNode<E>(element);
		LLNode<E> curr = this.head;
		while(index > 0){
			curr = curr.next;
			index--;
		}
		node.next = curr.next;
		node.prev = curr;
		node.next.prev = node;
		node.prev.next = node;
		
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index < 0 || index > this.size-1)
			throw new IndexOutOfBoundsException("index out of bounds");
		LLNode<E> curr = this.head.next;
		while(index > 0){
			curr = curr.next;
			index--;
		}
		curr.prev.next = curr.next; // connect curr's prev and next
		curr.next.prev = curr.prev;
		size--; // decrement the size
		
		return curr.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException("null pointer");
		if(index < 0 || index > this.size-1)
			throw new IndexOutOfBoundsException("index out of bounds");
		LLNode<E> curr = this.head.next;
		while(index > 0){
			curr = curr.next;
			index--;
		}
		curr.data = element;
		
		return element;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
