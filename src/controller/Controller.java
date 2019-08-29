package controller;

import java.io.IOException;
import java.util.Scanner;

import model.data_structures.Queue;
import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					try { Queue<String[]> temp = modelo.carga();
						  	System.out.println("El total de viajes es: "+ modelo.numViajes());
							
						  	String[] tempDatos = temp.dequeue();
							System.out.println("Para el primer viaje:\n");
							System.out.println("	-La zona origen: "+ tempDatos[0]);
							System.out.println("	-La zona destino: "+ tempDatos[1]);
							System.out.println("	-La hora: "+ tempDatos[2]);
							System.out.println("	-El tiempo promedio: "+ tempDatos[3]);
							
							tempDatos = temp.dequeue();
							System.out.println("\nPara el ultimo viaje:\n");
							System.out.println("	-La zona origen: "+ tempDatos[0]);
							System.out.println("	-La zona destino: "+ tempDatos[1]);
							System.out.println("	-La hora: "+ tempDatos[2]);
							System.out.println("	-El tiempo promedio: "+ tempDatos[3]);
						
							do{
								System.out.println("\n1. Buscar el grupo de viajes consecutivos (cluster) más grande "
										+ "que se encuentran ordenados ascendentemente por la hora a partir de una hora");
								System.out.println("2. Reportar los últimos viajes para una hora dada");
								System.out.println("6. Exit");
								
								option = lector.nextInt();
								
								if(option==1 || option ==2 || option == 6 ){
									System.out.println("--------- \n Opcion Invalida !! \n---------");
								}
							}while(option != 6);
							
							if(option == 1){
								do{
									System.out.println("--------- \n Ingrese el numero de la hora que desea como partida: ");
									option= lector.nextInt();
									if(option>=0 && option< 24 ){
									
									temp = modelo.cluster(option);
											
									System.out.println("El numero de viajes en el cluster es:"+ temp.size());
									System.out.println("Para cada viaje se informara en orden: la hora, la zona origen, la zona destino y el tiempo promedio.\n");
									
									int npi = temp.size();
									for(int i=1;i< npi+1;i++){
										tempDatos = temp.dequeue();
										System.out.println(i+ ") " + tempDatos[2]+ " | "+ tempDatos[0]+" | "+ tempDatos[1]+" | "+ tempDatos[3]);
									}	
								}else{
										System.out.println("--------- \n Opcion Invalida !! \n---------");
									}
								}while(option<0 && option>=24);
								
						
								
							}else if(option == 2){
								
								int hora = 0;
								do{
									hora =lector.nextInt();
									System.out.println("Ingrese el numero de la hora que desea como partida: ");
									
									if((option<0 && option>=24)){
										System.out.println("--------- \n Opcion Invalida !! \n---------");
									}
								}while(option<0 && option>=24);
								
								System.out.println("\nIngrese el numero de elementos que desea ver: ");
								int num = lector.nextInt();
								
								String s = hora+"";
								temp = modelo.reportarUltimos(num, s);
								
								System.out.println("Para cada viaje se informara en orden: la hora, la zona origen, la zona destino y el tiempo promedio.\n");
								
								int npi = temp.size();
								for(int i=1;i< npi+1;i++){
									tempDatos = temp.dequeue();
									System.out.println(i+ ") " + tempDatos[2]+ " | "+ tempDatos[0]+" | "+ tempDatos[1]+" | "+ tempDatos[3]);
								}
								
							}else{
								break;
							}
							
					}catch (IOException e) {e.printStackTrace();}
				break;	
					
				case 6: 
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
