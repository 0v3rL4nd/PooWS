package poo.thread;

public class ManagerMJ2 implements Manager {
	private int count=0; //conta accessi consecutivi di A
	private boolean risorsaOccupata=false;
	private Object lock=new Object(); //sostituisce this per il lucchetto
	public void richiesta( Manager.Proc id ) {
		synchronized( lock ) {
			if( id==Manager.Proc.A ) {
				System.out.println("Processo A fa richiesta.");
				while( risorsaOccupata || count==2 ) {
					try { lock.wait(); }catch( InterruptedException ie ) {}
				}//while
				count++;
				System.out.println("Processo A ottiene risorsa.");
				risorsaOccupata=true;
			}
			else { //B
				System.out.println("Processo B fa richiesta.");
				while( risorsaOccupata ) {
					try { lock.wait(); }catch( InterruptedException ie ) {}
				}//while
				System.out.println("Processo B ottiene risorsa.");
				risorsaOccupata=true;
			}
		}//chiusura blocco synchronized
	}//richiesta
	public void rilascio( Manager.Proc id ) {
		synchronized( lock ) {
			if( id==Manager.Proc.B ) count=0;
			System.out.println("Processo "+id+" rilascia la risorsa.");
			risorsaOccupata=false;
			lock.notifyAll();	
		}
	}//rilascio
}//ManagerMJ2