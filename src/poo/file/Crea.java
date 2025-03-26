package poo.file;
import java.io.*;
import java.util.*;

public class Crea{
	public static void main( String []args )throws IOException {
		//per semplicita’ non si usa la bufferizzazione
		Scanner sc=new Scanner( System.in );
		System.out.print("Nome file da creare: ");
		String nomeFile=sc.nextLine(); //nome fisico del file o da file system
		
		DataOutputStream dos=new DataOutputStream(
			new FileOutputStream(nomeFile) ); //apre il file in scruttura
		System.out.println("Fornisci una serie di interi sino al primo 0");
		
		int x=0;
		for(;;){
			System.out.print("int>");
			x=sc.nextInt();
			if( x==0 ) break;
			dos.writeInt( x );
		}
		dos.close(); //chiude file
		//visualizza contenuto di f3.dat
		DataInputStream dis=new DataInputStream(
			new FileInputStream(nomeFile) ); //apre il file in lettura
		System.out.println();
		System.out.println("Contenuto del file");
		for(;;){
			try{
				x=dis.readInt();
			}catch(	EOFException e ){ break; }
			System.out.println( x );
		}//for
		dis.close();
	}//main
}//Crea
