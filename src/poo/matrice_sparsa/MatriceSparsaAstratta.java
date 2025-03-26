package poo.matrice_sparsa;

public abstract class MatriceSparsaAstratta implements MatriceSparsa{
	public String toString() {
		StringBuilder sb=new StringBuilder(300);
		for( int i=0; i<nr_righe(); ++i ) {
			for( int j=0; j<nr_colonne(); ++j ) {
				double e=get(i,j);
				if( e!=0 ) sb.append(String.format("<"+i+","+j+">=%1.2f", e)+" " );
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}//MatriceSparsaAstratta
