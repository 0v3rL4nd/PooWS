package poo.backtracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

//una cella la posso rappresentare mediante 
//<i,j> => i*n+j è il numero univoco di quella cella
//se dal numero voglio i = numero/n 
//j = numero%n
//dove n = 9

public class Sudoku extends Backtracking<Integer, Integer> {
	// 0 = cella vuota
	// vincoli: uno stesso numero non può essere ripetuto sulla stessa riga o sulla
	// stessa colonna o nello stesso sottoblocco 3*3
	private int[][] sudoku;
	private int numSol;
	// verifichiamo a quale blocco appartiene. siccome abbiamo tutti i valori dei
	// sottoblocchi, facciamo una ricerca.
	private List<Integer> sottoblocchi = new LinkedList<>();

	public Sudoku(String[] schema) {
		if (schema.length != 9) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < schema.length; i++) {
			if (schema[i].length() != 9) {
				throw new IllegalArgumentException();
			}
		}
		this.sudoku = new int[schema.length][schema.length];
		sottoblocchi.add(0);
		sottoblocchi.add(3);
		sottoblocchi.add(6);
		sottoblocchi.add(27);
		sottoblocchi.add(30);
		sottoblocchi.add(33);
		sottoblocchi.add(54);
		sottoblocchi.add(57);
		sottoblocchi.add(60);
		for (int i = 0; i < schema.length; i++) {
			for (int j = 0; j < schema[i].length(); j++) {
				// prendo l'elemento dell'array di stringhe
				char c = schema[i].charAt(j);
				if (c != '0') {
					// System.out.println(i + " " + j);
					int valoreNumerico = c - '0';
					sudoku[i][j] = valoreNumerico;
					// adesso bisogna calcolare il numero univoco della cella
					int numeroUnivoco = i * schema.length + j;
					assegnate.add(numeroUnivoco);
				} else {
					// altrimenti ho un carattere pari a 0
					sudoku[i][j] = 0;
					nonAssegnate.add(i * schema.length + j);
				}
			}
		}

	}

	private List<Integer> assegnate = new ArrayList<>();
	private List<Integer> nonAssegnate = new ArrayList<>();

	@Override
	protected boolean esisteSoluzione(Integer p) {
		// TODO Auto-generated method stub
		return nonAssegnate.size() == 0;
	}

	@Override
	protected boolean assegnabile(Integer p, Integer s) {
		// TODO Auto-generated method stub
		// verifichiamo che sulla riga e sulla colonna non ci sia il valore s
		 System.out.println("Qui con "+p+" e valore "+s);
		int i = p / sudoku.length;
		int j = p % sudoku.length;
		System.out.println("p ha come indici " + i + " " + j);
		/*for (int in = 0; in < sudoku.length; in++) {
			for (int jn = 0; jn < sudoku.length; jn++) {
				System.out.print(sudoku[in][jn]);
			}
			System.out.println();
		}*/

		for (int riga = 0; riga < sudoku.length; riga++) {
			// controllo sulla colonna
			if (sudoku[riga][j] == s) {
				System.out.println("Esiste un elemento lungo la riga "+riga+" e colonna "+j);
				return false;
			}
		}
		for (int colonna = 0; colonna < sudoku.length; colonna++) {
			// controllo sulla riga
			if (sudoku[i][colonna] == s) {
				System.out.println("Esiste un elemento uguale in posizione "+i+" "+colonna);
				return false;
			}
		}
		// controllo nel sottoblocco
		// verifico a quale sottoblocco appartiene
		Integer valoreSottoBlocco = 0;
		for (int index = 0; index < sottoblocchi.size() - 1; index++) {
			if (sottoblocchi.get(index) <= p && p <= sottoblocchi.get(index + 1)) {
				valoreSottoBlocco = sottoblocchi.get(index);
				break;
			}
		}
		int rigaSottoBlocco = valoreSottoBlocco / sudoku.length;
		int colonnaSottoBlocco = valoreSottoBlocco % sudoku.length;
		System.out.println("Appartiene al sottoblocco " + valoreSottoBlocco + " il quale ha indici " + rigaSottoBlocco
				+ " " + colonnaSottoBlocco);
		for (int r = rigaSottoBlocco; r <= rigaSottoBlocco + 2; r++) {
			for (int c = colonnaSottoBlocco; c <= colonnaSottoBlocco + 2; c++) {
				if (sudoku[r][c] == s) {
					return false;
				}
			}
		}
		return true;

	}

	@Override
	protected void assegna(Integer ps, Integer s) {
		// TODO Auto-generated method stub
		int i = ps / sudoku.length;
		int j = ps % sudoku.length;
		sudoku[i][j] = s;
		System.out.println("Quindi elimino il valore");
		nonAssegnate.remove(ps);
		System.out.println("Dopo assegnabile = "+nonAssegnate);
	}

	@Override
	protected void deassegna(Integer ps, Integer s) {
		// TODO Auto-generated method stub
		int i = ps / sudoku.length;
		int j = ps % sudoku.length;
		sudoku[i][j] = 0;
		nonAssegnate.add(ps);
		System.out.println("Dopo deassegna "+nonAssegnate);
	}

	@Override
	protected void scriviSoluzione(Integer p) {
		// TODO Auto-generated method stub
		numSol++;
		System.out.println("Numero soluzione: " + numSol);
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku.length; j++) {
				System.out.print(sudoku[i][j]);
			}
			System.out.println();
		}

	}

	@Override
	protected List<Integer> puntiDiScelta() {
		// TODO Auto-generated method stub
		return nonAssegnate;
	}

	@Override
	protected Collection<Integer> scelte(Integer p) {
		// TODO Auto-generated method stub
		Collection<Integer> scelte = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			scelte.add(i);
		}
		return scelte;
	}

	public static void main(String[] args) {
		String[] schema = new String[9];
		schema[0] = "530070000";
		schema[1] = "600195000";
		schema[2] = "098000060";
		schema[3] = "800060003";
		schema[4] = "400803001";
		schema[5] = "700020006";
		schema[6] = "060000280";
		schema[7] = "000419005";
		schema[8] = "000080079";
		Sudoku s = new Sudoku(schema);
		s.risolvi();
	}

}
