package poo.tracce.BigInt;

public interface BigInt extends Comparable<BigInt>, Iterable<Integer> {
    String value();                // ritorna il valore del BigInt come stringa
    int length();                 // ritorna il numero di cifre
    BigInt factory(int x);        // costruisce un BigInt da un int (eccezione se negativo)
    BigInt incr();                // this + 1
    BigInt decr();                // this - 1 (eccezione se this == 0)
    BigInt add(BigInt a);         // somma this + a
    BigInt sub(BigInt s);         // sottrazione this - s; atteso this >= s
    BigInt mul(BigInt m);         // moltiplicazione this * m
    BigInt div(BigInt d);         // divisione intera this / d; atteso d > 0 e this >= d
    BigInt rem(BigInt d);         // resto della divisione intera this % d
    BigInt pow(int exponent);     // this ^ exponent
}