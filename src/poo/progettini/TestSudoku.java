package poo.progettini;

//import java.util.Arrays;
//
//public class TestSudoku {
//
////	protected static boolean assegnabile(int[][] tabella,Cella p, Integer s) {
////		if (s<1 || s>9) throw new IllegalArgumentException();
////		//if (tabella[p.i()][p.j()]==0) return false;
////
////		//devo controllare inoltre che nella stessa riga, colonna e sottomatrice non
////		//sia gi� stata assegnata la scelta s
////
////		//check sopra
////		for(int i=0;i<p.i;i++)
////			tabella[i][p.j] = s;
////
////		//check a sinistra
////		for(int j=0;j<p.i;j++)
////			tabella[p.i][j] = s;
////
////		for(int i=0;i<3;i++)
////			for(int j=0;j<3;j++)
////				tabella[(p.i/3)*3+i][(p.j/3)*3+j] = s;
////
////		return true;
////	}
////
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
////		NRegineNEW nr = new NRegineNEW(6);
////		nr.risolvi();
////		int[][] t = {						SOLUZIONE ATTESA:
////				{0,8,0,5,7,6,2,0,0},
////				{0,0,0,4,0,2,0,0,0},
////				{0,0,0,0,3,9,5,4,8},
////				{6,3,0,9,0,0,8,5,2},
////				{0,9,0,2,0,0,3,7,0},
////				{8,0,0,0,5,0,6,9,4},
////				{2,5,7,6,0,3,4,8,9},
////				{3,0,8,7,0,0,0,2,5},
////				{0,4,0,0,0,0,0,0,6},
////				};
////				SOLUZIONE ATTESA:
////				9 8 4 5 7 6 2 1 3
////				5 1 3 4 8 2 9 6 7
////				7 2 6 1 3 9 5 4 8
////				6 3 1 9 4 7 8 5 2
////				4 9 5 2 6 8 3 7 1
////				8 7 2 3 5 1 6 9 4
////				2 5 7 6 1 3 4 8 9
////				3 6 8 7 9 4 1 2 5
////				1 4 9 8 2 5 7 3 6
////		int[][] t = {
////				{0,5,8,0,6,4,0,0,0},
////				{6,0,9,0,0,0,0,4,0},
////				{0,0,2,3,7,0,0,6,8},
////				{0,8,7,0,0,0,0,5,0},
////				{0,0,3,8,0,0,7,0,0},
////				{5,0,0,4,0,7,0,0,2},
////				{8,7,0,0,4,6,0,0,0},
////				{0,0,0,2,5,3,8,0,0},
////				{0,2,0,0,8,0,0,0,4}};
////				SOLUZIONE ATTESA:
////				7 5 8 1 6 4 9 2 3
////				6 3 9 5 2 8 1 4 7
////				4 1 2 3 7 9 5 6 8
////				1 8 7 6 3 2 4 5 9
////				2 4 3 8 9 5 7 1 6
////				5 9 6 4 1 7 3 8 2
////				8 7 1 9 4 6 2 3 5
////				9 6 4 2 5 3 8 7 1
////				3 2 5 7 8 1 6 9 4
//		int[][] t = {
//				{0,6,3,0,8,0,4,0,0},
//				{0,9,0,0,0,4,0,0,7},
//				{4,0,0,0,2,0,0,0,0},
//				{0,0,7,2,0,3,0,5,8},
//				{0,5,0,0,4,0,0,0,0},
//				{0,0,8,0,0,0,2,0,4},
//				{6,0,0,0,0,0,7,0,0},
//				{0,0,5,0,3,6,0,0,0},
//				{0,3,0,8,7,0,0,2,6}
//		};
////				SOLUZIONE ATTESA:
////				5 6 3 7 8 1 4 9 2
////				8 9 2 3 5 4 6 1 7
////				4 7 1 6 2 9 5 8 3
////				9 4 7 2 6 3 1 5 8
////				2 5 6 1 4 8 3 7 9
////				3 1 8 5 9 7 2 6 4
////				6 8 9 4 1 2 7 3 5
////				7 2 5 9 3 6 8 4 1
////				1 3 4 8 7 5 9 2 6
//
//		System.out.println("Risolvo il sudoku:");
//		for(int[] r:t)
//			System.out.println(Arrays.toString(r));
//
//		Sudoku sudoku = new Sudoku(t);
//		sudoku.risolvi();
//	}
//
//}
