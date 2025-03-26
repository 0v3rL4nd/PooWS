package poo.backtracking;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Set;
//
//enum Colori {
//	ROSSO, VERDE, GIALLO, MARRONE
//};
//
//public class ColorazioneMappa extends Backtracking<Integer, Colori> {
//
//	// (vincoli) mai due celle confinanti ricevano lo stesso colore.
//
//	// Punti di scelta: gli indici da 0 ad n-1 delle nazioni.
//	// Scelte per ogni punto di scelta: i 4 possibili colori.
//	private int n;
//	private Set<Integer>[] confinanti; // array con le celle confinanti
//	private Set<Integer>[] stessoColore; // ogni indice rappresenta un set di celle che hanno lo stesso colore
//	private int numSol;
//
//	public ColorazioneMappa(Set<Integer>[] confinanti) {
//		this.n = confinanti.length;
//		this.confinanti = new HashSet[n];
//		this.stessoColore = new HashSet[Colori.values().length];
//		for (int i = 0; i < n; ++i)
//			this.confinanti[i] = new HashSet<>(confinanti[i]);
//		for (int i = 0; i < Colori.values().length; ++i)
//			this.stessoColore[i] = new HashSet<>();
//
//	}
//
//	private int getColore(Colori s) {
//		Colori[] c = Colori.values();
//		for (int i = 0; i < c.length; i++) {
//			if (c[i].equals(s)) {
//				return i;
//			}
//		}
//		return -1;
//	}
//
//	@Override
//	protected boolean assegnabile(Integer p, Colori s) {
//		// io ho un indice e devo verificare se tale colore sia assegnabile
//		Set<Integer> conf = this.confinanti[p];
//		int index = this.getColore(s);
//		Set<Integer> colori = this.stessoColore[index]; // set di tutte le nazioni che hanno lo stesso colore
//		for (Integer col : colori) {
//			if (conf.contains(col)) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	@Override
//	protected void assegna(Integer ps, Colori s) {
//		int i = this.getColore(s);
//		stessoColore[i].add(ps);
//
//	}
//
//	@Override
//	protected void deassegna(Integer ps, Colori s) {
//		// TODO Auto-generated method stub
//		int i = this.getColore(s);
//		stessoColore[i].remove(ps);
//	}
//
//	@Override
//	protected void scriviSoluzione(Integer integer) {
//		numSol++;
//		System.out.print("Soluzione " + numSol + ": ");
//		for (int i = 0; i < stessoColore.length; i++) {
//			Colori curr = trovaColore(i);
//			String s = stessoColore[i].toString();
//			System.out.print(curr + s+" ");
//		}
//		System.out.println(" ");
//	}
//
//	@Override
//	protected boolean esisteSoluzione(Integer integer) {
//		return integer == n - 1;
//	}
//
//	@Override
//	protected List<Integer> puntiDiScelta() {
//		List<Integer> ps = new ArrayList<>();
//		for (int i = 0; i < this.confinanti.length; i++) {
//			ps.add(i);
//		}
//		return ps;
//	}
//
//	@Override
//	protected Collection<Colori> scelte(Integer p) {
//		Collection<Colori> s = new ArrayList<>();
//		for (Colori c : Colori.values())
//			s.add(c);
//		return s;
//	}
//
//	private Colori trovaColore(int i) {
//		return Colori.values()[i];
//	}
//
//	public static void main(String[] args) throws IOException {
//		int n = 6; // esempio
//		Set<Integer> conf[] = new HashSet[n];
//		// esempio di confinanze
//		conf[0] = new HashSet<>(java.util.Arrays.asList(1, 4));
//		conf[1] = new HashSet<>(java.util.Arrays.asList(0, 4, 5, 2));
//		conf[2] = new HashSet<>(java.util.Arrays.asList(1, 5, 3));
//		conf[3] = new HashSet<>(java.util.Arrays.asList(2));
//		conf[4] = new HashSet<>(java.util.Arrays.asList(0, 1, 5));
//		conf[5] = new HashSet<>(java.util.Arrays.asList(1, 2, 4));
//		ColorazioneMappa cm = new ColorazioneMappa(conf);
//		cm.risolvi();
//	}// main
//
//}