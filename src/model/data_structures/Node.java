package model.data_structures;

public class Node<E> {
	E elemento;

	Node<E> siguiente;
	
	public Node(E elemento) {this.elemento = elemento; siguiente = null;}

	public void cambiarSiguiente(Node<E> siguiente) {this.siguiente = siguiente;}
		
	public E darElemento() {return elemento;}
	
	public void cambiarElemento(E elemento) {this.elemento = elemento;}
	
	public Node<E> darSiguiente() {return siguiente;}
}
