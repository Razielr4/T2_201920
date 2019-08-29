package model.data_structures;

public interface IQueue<T> {
	
	public void enqueue(T item);
	public T dequeue();
	public boolean isEmpty();
	public int size();
	
}
