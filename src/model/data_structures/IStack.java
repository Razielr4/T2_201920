package model.data_structures;

public interface IStack<T> {

	public void push(T item);
	public T pop();
	public boolean isEmpty();
	public int size();
}
