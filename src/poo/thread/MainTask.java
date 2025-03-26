package poo.thread;

public class MainTask {
	public static void main( String[] args ) {
		Manager m=new ManagerMJ();
		ProcessoTask at=new ProcessoTask( Manager.Proc.A, m, 100, 500 );
		ProcessoTask bt=new ProcessoTask( Manager.Proc.B, m, 1500, 2500 );
		Thread a=new Thread(at);
		Thread b=new Thread(bt);
		a.start(); b.start();
	}//main
}//MainTask
