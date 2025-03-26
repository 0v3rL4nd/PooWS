package poo.agendina;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.StringTokenizer;

public interface Agendina extends Iterable<Nominativo>{
	@SuppressWarnings("unused")
	default int size() {
		int conta=0;
		for( Nominativo n: this ) conta++;
		return conta;
	}//size
	default void svuota() {
		Iterator<Nominativo> it=iterator();
		while( it.hasNext() ) {
			it.next(); it.remove();
		}
	}//svuota
	void aggiungi( Nominativo n );
	default void rimuovi( Nominativo n ) {
		Iterator<Nominativo> it=iterator();
		while( it.hasNext() ) {
			Nominativo x=it.next(); 
			if( x.equals(n) ) {
				it.remove();
				break;
			}
			if( x.compareTo(n)>0 ) return;
		}		
	}//rimuovi
	default Nominativo cerca( Nominativo n ) {
		for( Nominativo x: this ) {
			if( x.equals(n) ) return x; 
			if( x.compareTo(n)>0 ) return null;
		}
		return null;
	}//cerca
	default Nominativo cerca( String prefisso, String telefono ) {
		for( Nominativo x: this )
			if( x.prefisso().equals(prefisso) && x.telefono().equals(telefono) ) return x;
		return null;
	}//cerca
	default void salva( String nomeFile ) throws IOException{
		/*
		PrintWriter pw=new PrintWriter( new FileWriter(nomeFile) );
		for( Nominativo n: this ) {
			pw.println(n);
		}
		pw.close();
		
		PrintStream ps=new PrintStream( new FileOutputStream( nomeFile ), true ); //auto-flushing
		for( Nominativo n: this )
			ps.println( n );
		ps.close();
		*/
		ObjectOutputStream oos=new ObjectOutputStream( new FileOutputStream(nomeFile) );
		for( Nominativo n: this )
			oos.writeObject(n);
		oos.close();
	}//salva
	default void ripristina( String nomeFile ) throws IOException{
		File f=new File( nomeFile );
		if( !f.exists() ) throw new IOException("File inesistente.");
/*
		BufferedReader br=new BufferedReader( new FileReader(nomeFile) );
		this.svuota();
		for(;;) {
			String linea=br.readLine();
			if( linea==null ) break;  
			StringTokenizer st=new StringTokenizer(linea," -");
			try {
				String cog=st.nextToken();
				String nom=st.nextToken();
				String pre=st.nextToken();
				String tel=st.nextToken();
				this.aggiungi( new Nominativo(cog,nom,pre,tel) );
			}catch( Exception e ) {
				throw new IOException("Dati scorretti.");
			}
		}
		br.close();
*/
		ObjectInputStream ois=new ObjectInputStream( new FileInputStream(f) );
		this.svuota(); //ottimismo
		for(;;) {
			try {
				Nominativo n=(Nominativo)ois.readObject();
				this.aggiungi(n);
			}catch( ClassNotFoundException e1 ) {
				ois.close();
				throw new IOException();
			}catch( ClassCastException e2 ) {
				ois.close();
				throw new IOException();
			}catch( EOFException e3 ) {
				ois.close();
				break;
			}
		}
	}//ripristina
}//Agendina
