package poo.progettini;
	
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Comparator;

public class ValutatoreEspressioni {
	//Operatori ammessi:+,-,*,/,%,^  e parentesi ()
	//Priorit� operatori : (^)>(*,/,%)>(+,-)
	//private static ComparaOperatori cmp = new ComparaOperatori();
	private static int priorita(Character c) {
		if (c=='^')
			return 3;
		if (c=='*' || c=='/' || c=='%')
			return 2;
		return 1;//+ e -
	}
	
	private static Comparator<Character> cmp = (o1,o2)->  {//LAMBDA EXPRESSION
		{
			int p1 = priorita(o1);
			int p2 = priorita(o2);
			
			return Integer.compare(p1, p2);
		}
	};
		
	private static StringBuffer sb;
	//eventualmente per segnalare in che punto della stringa abbiamo malformazioni
	
	public static int valutaEspressione(String s) {
		if(s==null) throw new IllegalArgumentException();
		s = s.trim();
		if(s=="") return 0;
		sb = new StringBuffer();

		
		return valutaEspressione(new StringTokenizer(s,"+-*/%^()",true));
		//true perch� voglio gli operandi come token
	}
	
	private static int valutaEspressione(StringTokenizer st) throws EspressioneMalformataException{
		LinkedList<Integer> stackOperandi = new LinkedList<>();
		LinkedList<Character> stackOperatori = new LinkedList<>();
		
		
		int o = 0;
		String s = st.nextToken();
		//come primo elemento posso ricevere o un numero, oppure una parentesi aperta che 
		//nel complesso sar� valutabile come un intero
		o = valutaOperando(s,st);
		//se valuta operando � andata a buon fine, allora appendo alla stringa di errore 
		//(l'eventuale errore � pi� avanti) e lo faccio direttamente dentro il metodo valutaOperando)
		stackOperandi.addLast(o);
		
		
		while(st.hasMoreTokens()) {
			s = st.nextToken();
			//a questo punto mi aspetto un operatore
			if (!s.matches("[\\+\\-*/%\\^)]"))
				throw new EspressioneMalformataException("Espressione malformata: operatore mancante in "+sb.toString().concat(" >< "+s+st.nextToken("")));
			sb.append(s);
			char opc = s.charAt(0);
			if (opc==')') { //� terminata un'espressione innestata con parentesi, 
				// quindi ultimo i calcoli e ritorno il risultato
				return calcolaOperazioniRimanenti(stackOperandi,stackOperatori);
			}
			
			if (!(stackOperatori.isEmpty() || cmp.compare(opc,stackOperatori.getLast())>0))
			//opc non � pi� prioritario rispetto alla cima dello stack operatori (CASO B)
				do 
					operazioneTopStack(stackOperandi,stackOperatori);
				while (!stackOperatori.isEmpty() && cmp.compare(opc,stackOperatori.getLast())<=0);
					//Si continua ad eseguire il passo B) se opc risulta ancora non pi�
				    //prioritario dell�operatore affiorante la cima dello stack operatori
			//in entrambi i casi, (sia caso A che B) devo aggiungere l'operatore allo stack
			stackOperatori.addLast(opc);
			
			// passo al prossimo token avendo ricevuto un operatore , mi aspetto
			// anche un operando, altrimenti l'espressione � malformata!
			if (!st.hasMoreTokens()) 
				throw new EspressioneMalformataException("Espressione malformata: operando mancante in "+sb.toString().concat(" >< //Fine stringa"));
			
			s = st.nextToken();
			o = valutaOperando(s,st);//lancia eccezioni in caso di malformazione
			stackOperandi.addLast(o);
		}//while

		return calcolaOperazioniRimanenti(stackOperandi, stackOperatori);
	}

	//Per quanto riguarda le op. rimanenti ci sono due possibilit�:    1)lo stack operatori � vuoto
	//2)nello stack operatori sono rimasti solo operatori con stessa priorit�, 
	//eccetto al pi� l'operatore in cima allo stack, che sar� per� valutato per primo, quindi
	//non ci saranno problemi di priorit�
	//nel primo caso mi basta prendere l'unico operando rimasto
	//nel secondo mi basta estrarre uno a uno dalla cima dello stack (Punto B)
	private static int calcolaOperazioniRimanenti(LinkedList<Integer> stackOperandi,
			LinkedList<Character> stackOperatori) {
		//caso 1:
		if (stackOperandi.size()==1) return stackOperandi.getLast();
		
		//caso 2:
		while(!stackOperatori.isEmpty()) 
			operazioneTopStack(stackOperandi,stackOperatori);
		
		//notiamo che, per come ho strutturato l'algoritmo, non dovrei mai entrare in questo if
		//d'errore. Questo in quanto avrei gi� lanciato l'eccezione prima, durante la lettura, 
		//evitando inutili calcoli e notificando in quale parte della stringa � presente la malformazione.
		if(!(stackOperandi.size()==1))
			throw new EspressioneMalformataException("Espressione malformata :"+sb.toString());
		return stackOperandi.removeLast();//il risultato � l'unico elemento nello stack operandi.
	}

	private static void operazioneTopStack(LinkedList<Integer> stackOperandi, LinkedList<Character> stackOperatori) {
		int o2 = stackOperandi.removeLast(); 
		int o1 = stackOperandi.removeLast();
		char opt = stackOperatori.removeLast();
		stackOperandi.addLast(operazione(o1,opt,o2));
	}
	
	private static int operazione(int o1, char opc, int o2) {
		int res = o1;
		switch(opc) {
			case'+':res+=o2;break;
			case'-':res-=o2;break;
			case'*':res*=o2;break;
			case'/':res/=o2;break;
			case'%':res%=o2;break;
			case'^':res=(int) Math.pow(res, o2);break;
			default:throw new IllegalArgumentException("Operatore non valido : "+opc);
			//per come ho gestito il codice prima, questo non dovrebbe mai succedere
		}
		return res;
	}
	
	private static int valutaOperando(String s, StringTokenizer st) {
		if(s.charAt(0)=='(') {
			sb.append("(");
			return valutaEspressione(st);//chiamata ricorsiva, interpreto il contenuto
			// delle parentesi come un'espressione, vale come un singolo intero
		}
		else if (s.matches("[0-9]+")){//deve contenere almeno una cifra tra 0 e 9
			sb.append(s);
			return Integer.parseInt(s);// caso base, ovvero che il primo numero sia un intero
		}
		else
			throw new EspressioneMalformataException("Espressione malformata: operando mancante in "+sb.toString().concat(" >< "+s+st.nextToken("")));
	}
}
