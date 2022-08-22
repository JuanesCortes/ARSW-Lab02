package edu.eci.arsw.primefinder;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		PrimeFinderThread pft=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread pft2=new PrimeFinderThread(10000001, 20000000);
		PrimeFinderThread pft3=new PrimeFinderThread(20000001, 30000000);

		

		
		try {
			pft.iniciar();;
			pft2.iniciar();
			pft3.iniciar();
			Thread.sleep(5000);
			pft.detener();
			pft2.detener();
			pft3.detener();
			
			System.out.println("ENTER PARA CONTINUAR");
			Scanner lectura = new Scanner(System.in);
			String dato = lectura.nextLine();
			
			pft.reanudar();
			pft2.reanudar();
			pft3.reanudar();
			pft.hilo.join();
			pft2.hilo.join();
			pft3.hilo.join();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
