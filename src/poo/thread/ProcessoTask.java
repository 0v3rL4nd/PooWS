package poo.thread;

public class ProcessoTask implements Runnable{
	private Manager.Proc id;
	private final int MAX, MIN; //msec
	private Manager m;
	
	public ProcessoTask( Manager.Proc id, Manager m, final int MAX, final int MIN ) { 
		this.id=id; this.m=m; this.MAX=MAX; this.MIN=MIN;
	}
	
	private void pausa() {
		try {
			Thread.sleep( (int)(Math.random()*(MAX-MIN)+MIN));
		}catch( InterruptedException ie ) {}
	}//pausa
	
	public void run() {//corpo del processo
		while( true ) {
			pausa();
			m.richiesta(id);
			pausa();
			m.rilascio(id);
		}
	}//run  
}//ProcessoTask
