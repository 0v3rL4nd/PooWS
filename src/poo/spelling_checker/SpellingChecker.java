package poo.spelling_checker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class SpellingChecker {
	static void caricaDizionario( Set<String> diz, String nomeFile ) throws IOException{
		BufferedReader br=new BufferedReader( new FileReader(nomeFile) );
		String par=null;
		try {
			for(;;) {
				par=br.readLine();
				if( par==null ) break;
				diz.add(par.toLowerCase());
			}
		}finally { br.close(); }
	}//caricaDizionario
	static void caricaDocumento( Set<String> diz, Set<String> doc, String nomeFile ) throws IOException{
		BufferedReader br=new BufferedReader( new FileReader(nomeFile) );
		String linea=null;
		try {
			for(;;) {
				linea=br.readLine();
				if( linea==null ) break;
				String[] words=linea.split("\\W+");
				for( String w: words ) {
					if( w.length()!=0 && !diz.contains(w.toLowerCase()) ) doc.add(w);
				}
			}
		}finally { br.close(); }		
	}//caricaDocumento
	static void salvaProposte( Map<String,String> propose, String nomeFile ) throws IOException{	
		PrintWriter pw=new PrintWriter( new FileWriter(nomeFile) );
		try {
			for( String a: propose.keySet() ) {
				String b=propose.get(a);
				pw.println(a+" "+b);
			}
		}finally { pw.close(); }
	}//salvaPropose
	
	public static void main( String...args ) throws IOException {
		System.out.println("Spelling Checker");
		String fileDizionario="c:\\poo-file\\dizionario.txt";
		String fileDocumento="c:\\poo-file\\documento.txt";
		String fileProposte="c:\\poo-file\\proposte.txt";
		Set<String> dizionario=new HashSet<>(), documento=new HashSet<>();
		Map<String,String> proposte=new HashMap<>();
		caricaDizionario(dizionario,fileDizionario);
		caricaDocumento(dizionario,documento,fileDocumento);
		for( String a: documento ) {
			int dmin=Integer.MAX_VALUE;
			String b=null;
			for( String target: dizionario ) {
				int d=Similarita.lev(a,target);
				if( d<dmin ) { dmin=d; b=target; }
			}
			proposte.put(a,b);
			System.out.println(a+" "+b);
		}
		salvaProposte( proposte,fileProposte );
	}//main
	
}//SpellingChecker
