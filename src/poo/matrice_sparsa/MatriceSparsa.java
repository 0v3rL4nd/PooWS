package poo.matrice_sparsa;

public interface MatriceSparsa {
	int nr_righe();
	int nr_colonne();
	double get( int i, int j );
	void set( int i, int j, double v );
	
	MatriceSparsa factory(int r, int c);

	default MatriceSparsa add( MatriceSparsa m ) {
		if( nr_righe()!=m.nr_righe() || nr_colonne()!=m.nr_colonne() )
			throw new IllegalArgumentException();
		MatriceSparsa s=factory(nr_righe(),nr_colonne());
		for( int i=0; i<nr_righe(); ++i )
			for( int j=0; j<nr_colonne(); ++j ) {
				double e=get(i,j)+m.get(i,j);
				if( e!=0 ) s.set(i,j,e);
			}
		return s;
	}//add
	default MatriceSparsa mul( MatriceSparsa m ) {
		if( nr_colonne()!=m.nr_righe() )
			throw new IllegalArgumentException();
		MatriceSparsa p=factory( nr_righe(),m.nr_colonne() );
		for( int i=0; i<nr_righe(); ++i )
			for( int j=0; j<m.nr_colonne(); ++j ) {
				double ps=0;
				for( int k=0; k<nr_colonne(); ++k )
					ps=ps+get(i,k)*m.get(k,j);
				if( ps!=0 ) p.set(i,j,ps);
			}
		return p;
	}//mul
	default MatriceSparsa mul( double s ) {
		MatriceSparsa m=factory(nr_righe(),nr_colonne());
		for( int i=0; i<nr_righe(); ++i )
			for( int j=0; j<nr_colonne(); ++j ) {
				double e=get(i,j)*s;
				if( e!=0 ) m.set(i,j,e);
			}
		return m;
	}//mul

	default MatriceSparsa copia() {
		MatriceSparsa c=factory( nr_righe(), nr_colonne() );
		for( int i=0; i<nr_righe(); ++i )
			for( int j=0; j<nr_colonne(); ++j )
				if( get(i,j)!=0 ) c.set(i,j,get(i,j));
		return c;
	}//copia
	default double determinanteG() {
		if( nr_righe()!=nr_colonne() )
			throw new IllegalArgumentException();
		int numScambi=0;
		MatriceSparsa a=copia();
		for( int j=0; j<a.nr_righe(); ++j ) {
			if( a.get(j,j)==0 ) {//a meno dei problemi numerici
				//pivoting
				int p=j+1;
				for( ; p<nr_righe(); ++p )
					if( a.get(p,j)!=0 ) break;
				if( p==a.nr_righe() ) return 0;
				numScambi++;
				for( int k=j; k<nr_colonne(); ++k ) {
					double tmp=a.get(j,k); 
					a.set(j,k,a.get(p,k));
					a.set(p,k,tmp);
				}
			}
			//azzera porzione di colonna j da j+1 ad nr_righe
			for( int i=j+1; i<a.nr_righe(); ++i ) {
				if( a.get(i,j)!=0 ) {
					double coeff=a.get(i,j)/a.get(j,j);
					for( int k=j; k<a.nr_colonne(); ++k )
						a.set(i,k,a.get(i,k)-a.get(j,k)*coeff );
				}
			}
		}
		
		double d=1;
		for( int j=0; j<nr_righe(); ++j )
			d=d*a.get(j,j);
		if( numScambi%2!=0 ) d=-d;
		return d;
	}//determinanteG
	
	//minore e determinanteL lasciati come esercizio
	
}//MatriceSparsa
