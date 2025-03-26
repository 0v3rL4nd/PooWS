package poo.file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class InsertionSortSuFile {
	public static void main( String[] args ) throws IOException{
		System.out.println("Insortion sort su file tipato di interi.");
		Scanner sc=new Scanner( System.in );
		System.out.print("Fornisci il file sorgente: ");
		String nomeFile=sc.nextLine();
		System.out.print("Fornisci l'elemento da inserire x: ");
		int x=sc.nextInt(); sc.nextLine();
		DataInputStream dis=new DataInputStream(
			new FileInputStream( nomeFile )	);
		DataOutputStream dos=new DataOutputStream( new FileOutputStream("c:\\poo-file\\tmp"));
		boolean flag=false;
		int y=0; //fittizio
		for(;;) {
			try {
				y=dis.readInt();
				if( y>x ) {
					flag=true;
					break;
				}
				else dos.writeInt(y);
			}catch( EOFException e ) {
				break;
			}
		}
		//con certezza inseriamo x
		dos.writeInt(x);
		if( flag ) {
			dos.writeInt(y); //sistema la y pendin5g
			for(;;) {//copia elementi residui di dis
				try {
					y=dis.readInt();
					dos.writeInt(y);
				}catch(EOFException e) {
					break;
				}
			}
		}
		dis.close();
		dos.close();
		System.out.println("Contenuto file dopo insertion sort");
		DataInputStream sorg=new DataInputStream( new FileInputStream("c:\\poo-file\\tmp"));
		for(;;) {
			try {
				y=sorg.readInt();
				System.out.println(y);
			}catch(EOFException e){
				break;
			}
		}
		sorg.close();
		sc.close();
		
		//ridenominazione del file temporaneo col nome del file originario
		File f1=new File( nomeFile );
		f1.delete();
		File f2=new File( "c:\\poo-file\\tmp" );
		f2.renameTo(f1);
		
		
	}//main
}//
