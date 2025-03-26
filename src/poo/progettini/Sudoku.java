package poo.progettini;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//import poo.backtracking.Backtracking;
//
//
//public class Sudoku extends Backtracking<Cella, Integer> {
//	private int[][] tabella;
//	private int nSol;
//	private Cella finale;
//	//I miei punti di scelta sono Celle identificano una cella (i,j) in una matrice 9x9
//	//Quindi in totale 81.
//
//
//	public void imposta(int i, int j, int v) {
//		if (i<0 || i>9 || j<0 || j>9) throw new IllegalArgumentException();
//		if(assegnabile(new Cella(i,j),v))
//			assegna(new Cella(i,j),v);
//		else
//			System.out.println("Non assegnabile");
//	}
//
//	public Sudoku() {
//		tabella = new int[9][9];//di default � inizializzata a 0
//		nSol = 0;
//	}
//
//	public Sudoku(int[][] m) {
//		tabella = m;
//		nSol = 0;
//		List<Cella> ps = puntiDiScelta();
//		if(ps.size()<1) throw new IllegalArgumentException("Il sudoku non ha celle vuote");
//	}
//	@Override
//	protected boolean assegnabile(Cella p, Integer s) {
//		if (s<1 || s>9) throw new IllegalArgumentException();//non entra mai qui
//		if (tabella[p.i()][p.j()]!=0) return false;
//
//		//devo controllare inoltre che nella stessa riga, colonna e sottomatrice non
//		//sia gi� stata assegnata la scelta s
//
//		//check riga
//		for(int i=0;i<9;i++)
//			if(tabella[i][p.j] == s) return false;
//
//		//check a sinistra
//		for(int j=0;j<9;j++)
//			if(tabella[p.i][j] == s) return false;
//
//		//check nella sotto-matrice
//		for(int i=0;i<3;i++)
//			for(int j=0;j<3;j++)
//				if(tabella[(p.i/3)*3+i][(p.j/3)*3+j] == s) return false;
//
//		return true;
//	}
//	@Override
//	protected void assegna(Cella ps, Integer s) {
//		tabella[ps.i][ps.j] = s;
//	}
//	@Override
//	protected void deassegna(Cella ps, Integer s) {
//		tabella[ps.i][ps.j] = 0;
//
//	}
//	@Override
//	protected void scriviSoluzione(Cella p) {
//		System.out.println("Soluzione n�"+(++nSol));
//		for(int[] r:tabella)
//			System.out.println(Arrays.toString(r));
//
//	}
//	@Override
//	protected boolean esisteSoluzione(Cella p) {
//		return p.equals(finale);
//	}
//	@Override
//	protected List<Cella> puntiDiScelta() {
//		List<Cella> res = new ArrayList<>(81);
//
//		for(int i=0;i<9;i++)
//			for(int j=0;j<9;j++)
//				if(tabella[i][j]==0)
//					res.add(new Cella(i,j));
//		return res;
//	}
//	@Override
//	protected Collection<Integer> scelte(Cella p) {
//		Collection<Integer> res = new ArrayList<>(9);
//		for(int i=1;i<=9;i++)
//			res.add(i);
//		return res;
//	}
//	@Override
//	protected void risolvi() {
//		List<Cella> pscelta = puntiDiScelta();
//		finale = pscelta.get(pscelta.size()-1);
//		tentativo(pscelta,pscelta.get(0));
//	}
//
//}
