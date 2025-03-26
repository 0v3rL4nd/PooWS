package poo.progettini;

@SuppressWarnings("serial")
public class EspressioneMalformataException extends RuntimeException {
	public EspressioneMalformataException() {
		super();
	}
	
	public EspressioneMalformataException(String s) {
		super(s);
	}
}
