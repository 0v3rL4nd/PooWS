package poo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {

    public void Avvia(){
        try{
            Scanner sc = new Scanner(System.in);
            DatagramSocket client = new DatagramSocket();
            System.out.println("client " + client.getLocalPort());
            InviaMessaggio(""+client.getLocalPort(),8000,client);
            String received = riveviMessaggio(client);
            System.out.println(received);

            while(!received.startsWith("tris")){
                InviaMessaggio(sc.nextLine(),8000,client);
                received = riveviMessaggio(client);
                System.out.println(received);
            }
            client.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }

    private void InviaMessaggio(String messaggio, int destinatario,DatagramSocket server) throws Exception {
        byte [] buf =messaggio.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("160.97.156.89"), destinatario);
        server.send(packet);
    }

    private String riveviMessaggio(DatagramSocket server) throws Exception {
        byte [] buf=new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        server.receive(packet);
        return new String(packet.getData(),0,packet.getLength());
    }

    public static void main (String[] args){
        Client c = new Client();
        c.Avvia();
    }

}
