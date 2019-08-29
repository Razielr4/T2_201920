package model.data_structures;

import java.util.Iterator;

public class Stack<T> implements Iterable<T>, IStack<T> {
	
	private Node<T> primerNodo;
	private int tamano;
	
	public Stack(T elemento){
		primerNodo = new Node<T>(elemento);
		tamano = 1;
	}
	
	public Stack() {
		primerNodo = null;
		tamano = 0;
	}

	public Iterator<T> iterator() {	
		return new StackIterador<T>(primerNodo);
	}
	
	private class StackIterador<E> implements Iterator<E>{
		
		Node<E> actual; 
		
		public boolean hasNext() {
			
			return actual != null;
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
		
		public StackIterador(Node<E> first) {
            actual = first;
        }
		
	}

	public void push(T item) {
		if(isEmpty()){
			primerNodo = new Node<T>(item);
			tamano ++;
		}else{
		Node<T> nPrimero = new Node<T>(item);
		nPrimero.cambiarSiguiente(primerNodo);
		primerNodo = nPrimero;
		tamano++;
	}
	}

	public T pop() {
		T respuesta = primerNodo.darElemento();
		primerNodo = primerNodo.darSiguiente();
		tamano --;
		return respuesta;
	}

	public boolean isEmpty() {
		return primerNodo == null;
	}

	public int size() {
		return tamano;
	}

}
