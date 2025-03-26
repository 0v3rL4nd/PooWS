package poo.thread;

public class Main {
	public static void main( String[] args ) {
		Manager m=new ManagerMJ();
		Processo a=new Processo( Manager.Proc.A, m, 100, 500 );
		Processo b=new Processo( Manager.Proc.B, m, 1500, 2500 );
		a.start(); b.start();
	}//main
}//Main
