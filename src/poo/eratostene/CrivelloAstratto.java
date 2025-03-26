package poo.eratostene;

public abstract class CrivelloAstratto implements Crivello{
	public String toString() {
		StringBuilder sb=new StringBuilder(1000);
		int c=0;
		for( int p: this ) {
			sb.append( String.format("%8d", p) );
			c++;
			if( c%8==0 ) sb.append("\n");
		}
		return sb.toString();
	}
}
