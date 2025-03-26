package poo.progettini;

import java.util.Scanner;

public class TestEspressione {
	static Scanner sc;
	//MAIN DI TEST
	public static void main(String[] args) {//main di prova
		System.out.println("Programma Valutatore espressioni aritmetiche intere con le seguenti propriet�:");
		System.out.println("Operatori ammessi con le rispettive priorit� : P(^)>P(*,/,%)>P(+,-)");
		System.out.println("Si possono usare le parentesi ( e ) per alterare le priorit�");
		System.out.println("A parit� di priorit�, si assume l�associativit� a sinistra");
		System.out.println("-------------------------");
		
		sc = new Scanner(System.in);
		
		loop:while(true) {
			comandi();
			System.out.println(">");
			char comando = sc.nextLine().toLowerCase().charAt(0);
			switch(comando) {
				case 'v':
					System.out.println("Inserire espressione:\n>");
					printEspressione(sc.nextLine());
					break;
				case 'e':operazioniEsempio();break;
				case 'q':break loop;
				default : System.out.println("non valido");
				}
		}
		sc.close();
	}
	


	private static void comandi() {
		System.out.println();
		System.out.println("Comandi ammessi: (solo prima lettera)");
		System.out.println("V(aluta espressione");
		System.out.println("E(sempi di prova");
		System.out.println("Q(uit");
		System.out.println();
	}



	private static void printEspressione(String str) {
		try {
			System.out.print(str+" = ");
			System.out.println(ValutatoreEspressioni.valutaEspressione(str));
		}catch(EspressioneMalformataException e) {
			System.out.println("\n"+e);
		}finally {
			System.out.print("\n\n");
		}
	}	
	
	private static void operazioniEsempio() {
		System.out.println("TEST ESPRESSIONE REGOLARE");
		printEspressione("3+5*2^4+(1-3)^2");

		System.out.println("TEST CON MALFORMAZIONE (operando mancante)");
		printEspressione("3+5*2^4++(1-3)^2");

		System.out.println("TEST CON MALFORMAZIONE (operando mancante)");
		printEspressione("3+5*2^4+(1-3)^2*");
		
		System.out.println("TEST ESPRESSIONE REGOLARE");
		printEspressione("9-7^2+(6-2^2)*10");

		System.out.println("TEST ESPRESSIONE REGOLARE (pi� complicata)");
		printEspressione("(((1-2)*(30+18*9)+(21+41-52)*17)+(19+26-22)/(21-(36-19-10)%(40-23-9))-16)");
		System.out.println("il risultato fatto da un vero calcolatore � -37 valutando / come divisione intera");
	
		
		System.out.println("-----------");
	}
}
