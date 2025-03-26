package poo.tracce.appello120221;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class RPn {
    private Map<String, Integer> map = new HashMap<>();

    public RPn( String memoria ) throws IOException {
        BufferedReader br = new BufferedReader( new FileReader( memoria ) );
        String line = "[a-zA-Z_]\\w*\\s+\\d+";
        try{
            for(;;){
                String s = br.readLine();
                if( s == null ) break;
                if( !s.matches( line ) ) throw new IOException( "Formato non corretto" );
                StringTokenizer st = new StringTokenizer( s, " " );
                String id = st.nextToken();
                int val = Integer.parseInt( st.nextToken() );
                map.put( id, val );
            }
        }
        finally {
            br.close();
        }
    }

    public int valuta( String rpn ){
        String var = "[a-zA-z_]\\w*";
        String inte = "\\d+";
        String op = "(" + var + "|" + inte + ")";
        String expr = op + "(\\s+" + op + "(\\s+(" + op + "|[\\+\\-\\*/]))+)?";

        if( !rpn.matches(expr) ) throw new IllegalArgumentException( "Formato non corretto");

        StringTokenizer st = new StringTokenizer( rpn, " " );
        Map<String, Integer> stack = new TreeMap<>();
        while( st.hasMoreTokens() ){
            String s = st.nextToken();
            if( s.matches( op ) ){
                if( !map.containsKey( s ) ) throw new IllegalArgumentException( "Variabile non definita" );
                stack.put( s, map.get( s ) );
            }
            else if( s.matches( inte ) ){
                stack.put( s, Integer.parseInt( s ) );
            }
            else{
                String op1 = st.nextToken();
                String op2 = st.nextToken();
                int val1 = stack.get( op1 );
                int val2 = stack.get( op2 );
                int val = 0;
                switch( s ){
                    case "+": val = val1 + val2; break;
                    case "-": val = val1 - val2; break;
                    case "*": val = val1 * val2; break;
                    case "/": val = val1 / val2; break;
                }
                stack.put( s, val );
            }
        }
        return stack.get( rpn );
    }

    public static void main( String[] args ) throws IOException {
        RPn rpn = new RPn( "memoria.txt" );
        System.out.println( rpn.valuta( "miao   10 + b 20 *" ) );
    }
}
