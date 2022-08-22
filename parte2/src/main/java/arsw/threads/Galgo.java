package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private Carril carril;
	RegistroLlegada regl;
	boolean corriendo;

	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
		corriendo = false;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {			
			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			synchronized(this) {
				while(!corriendo) {
					wait();
				}
			}
			synchronized(regl) {
				if (paso == carril.size()) {						
					carril.finish();
					int ubicacion=regl.getUltimaPosicionAlcanzada();
					regl.setUltimaPosicionAlcanzada(ubicacion+1);
					System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
					if (ubicacion==1){
						regl.setGanador(this.getName());
					}
					
				}
			}
			
		}
	}

	public synchronized void pausar() {
		corriendo = false;
	}
	
	public synchronized void continuar() {
		corriendo = true;
		notifyAll();
	}
	
	@Override
	public void run() {
		
		try {
			corriendo = true;
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
