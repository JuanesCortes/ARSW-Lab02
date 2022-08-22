package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread implements Runnable{

	Thread hilo;
	boolean enEjecucion;
	int a,b,i;
	
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
		this.i = a;
		hilo = new Thread(this);
		enEjecucion = false;
	}
	
	public synchronized void iniciar() {
		enEjecucion = true;
		hilo.start();
	}
	
	public synchronized void detener() {
		enEjecucion = false;
		
	}
	
	public synchronized void reanudar() {
		enEjecucion = true;
		notify();
	}
	

	public void run(){
		for (int i = a; i <= b; i++) {
			if (isPrime(i)) {
				primes.add(i);
				System.out.println(i);
			}
			synchronized (this) {
                while (!enEjecucion) {
                    try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
                
            }
		}
		
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	
	
	
}
