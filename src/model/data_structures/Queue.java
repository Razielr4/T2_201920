package model.data_structures;

import java.util.Iterator;

public class Queue<T> implements Iterable<T>, IQueue<T>{
	
	private Node<T> first;
	private Node<T> last;
	private int tamano;
	
	public Queue(T elemento){
		first = new Node<T>(elemento);
		last = first;
		tamano = 1;
	}

	public Queue() {
		first = null;
        last  = null;
        tamano = 0;
	}

	public Iterator<T> iterator() {
		return new QueueIterator<T>(first);
	}
	
	private class QueueIterator<E> implements Iterator<E>{
		
		Node<E> actual;
		
		public QueueIterator(Node<E> primero) {
            actual = primero;
        }

		public boolean hasNext() {
			return actual.darSiguiente() != null;
		}

		public E next() {
			if(hasNext()){
				Node<E> dar = actual;
				actual = actual.darSiguiente();
				return dar.darElemento();
			}else{
				return null;
			}
		}
		
		
	}

	public void enqueue(T item) {
		if(isEmpty()){
			first = new Node<T>(item);
			last = first;
			tamano ++;
			
		}else{
			last.cambiarSiguiente(new Node<T>(item));
			last = last.darSiguiente();
			tamano++;
			
		}
		
	}

	public T dequeue() {
		T respuesta = first.darElemento();
		first = first.darSiguiente();
		tamano --;
		return respuesta; 
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return tamano;
	}

}
