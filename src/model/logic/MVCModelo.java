package model.logic;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import model.data_structures.Queue;
import model.data_structures.Stack;

/**
 * Definicion del modelo del mundo
 */
public class MVCModelo {
	
	private Queue<String[]> queue;
	private Stack<String[]> stack;
	
	public MVCModelo(){
		queue = new Queue<String[]>();
		stack = new Stack<String[]>();
	}
	
	public int numViajes(){
		if(queue.size() == stack.size()){
			return queue.size();
		}
		return -1;
	}
	
	public Queue<String[]> carga() throws IOException{
		CSVReader reader = null;
		Queue<String[]> respuesta = new Queue<String[]>();
		
		try{
			String [] nextLine;
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"));
			
			nextLine = reader.readNext();
			respuesta.enqueue(nextLine);
			stack.push(nextLine);
			queue.enqueue(nextLine);
			
			while ((nextLine = reader.readNext()) != null) {
				stack.push(nextLine);
				queue.enqueue(nextLine);
			}
			
			nextLine = stack.pop();
			respuesta.enqueue(nextLine);
			stack.push(nextLine);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return respuesta;
	}
	
	public Queue<String[]> cluster(int hora){
		
		Queue<String[]> respuesta = new Queue<String[]>();
		Queue<String[]> tempQueue = new Queue<String[]>();
		String[] elem = null;
		int tempHora =0;
		int actual = 0;
		
		for(int i=0;i<queue.size();i++){
			elem = queue.dequeue();
			actual = Integer.parseInt(elem[2]);
			
			if(hora <= actual){
				if(tempHora <= actual){
					tempQueue.enqueue(elem);
				}else{
					if(respuesta.size() < tempQueue.size()){
						respuesta = tempQueue;
					}
					tempQueue = new Queue<String[]>(elem);
				}
			}else{
				if(respuesta.size() < tempQueue.size()){
					respuesta = tempQueue;
				}
				tempQueue = new Queue<String[]>();
			}
			
			tempHora = actual;
		}
		
		return respuesta;
	}
	
	public Queue<String[]> reportarUltimos(int num, String hora){
		Queue<String[]> respuesta = new Queue<String[]>();
		int npi = 0;
		String[] tempN = null;
		while(npi < num ){
			tempN = stack.pop();
			if(hora.equals(tempN[2])){
				npi ++;
				respuesta.enqueue(tempN);
			}
		}
		
		return respuesta; 
	}
}
