package poo.thread;

interface Manager {
	enum Proc{A,B}
	void richiesta( Proc id );
	void rilascio( Proc id );
}//Manager

