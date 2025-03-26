package poo.progettini;

public class Cella{
	int i,j;
	public Cella(int i,int j) {
		if (i<0 || i>9 || j<0 || j>9) throw new IllegalArgumentException();
		this.i = i;
		this.j = j;
	}
	
	public int i() {
		return i;
	}
	
	public int j() {
		return j;
	}
}
