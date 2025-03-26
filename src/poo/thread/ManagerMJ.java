package poo.thread;

public class ManagerMJ implements Manager {
	private int count=0; //conta accessi consecutivi di A
	private boolean risorsaOccupata=false;
	public synchronized void richiesta( Manager.Proc id ) {
		if( id==Manager.Proc.A ) {
			System.out.println("Processo A fa richiesta.");
			while( risorsaOccupata || count==2 ) {
				try { wait(); }catch( InterruptedException ie ) {}
			}//while
			count++;
			System.out.println("Processo A ottiene risorsa.");
			risorsaOccupata=true;
		}
		else { //B
			System.out.println("Processo B fa richiesta.");
			while( risorsaOccupata ) {
				try { wait(); }catch( InterruptedException ie ) {}
			}//while
			System.out.println("Processo B ottiene risorsa.");
			risorsaOccupata=true;
		}
	}//richiesta
	public synchronized void rilascio( Manager.Proc id ) {
		if( id==Manager.Proc.B ) count=0;
		System.out.println("Processo "+id+" rilascia la risorsa.");
		risorsaOccupata=false;
		notifyAll();		
	}//rilascio
}//ManagerMJ