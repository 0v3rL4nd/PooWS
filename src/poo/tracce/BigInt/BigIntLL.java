package poo.tracce.BigInt;

import java.util.*;

public class BigIntLL extends AbstractBigInt {

    private LinkedList<Integer> digits = new LinkedList<>(); // cifre in ordine naturale (MSB → LSB)

    // --- Costruttore da int ---
    public BigIntLL(int x) {
        if (x < 0) throw new IllegalArgumentException("Valore negativo non ammesso");
        if (x == 0) {
            digits.add(0);
        } else {
            while (x > 0) {
                digits.addFirst(x % 10);
                x /= 10;
            }
        }
    }

    // --- Costruttore da stringa ---
    public BigIntLL(String s) {
        if (!s.matches("\\d+")) throw new IllegalArgumentException("Stringa non numerica");
        // rimuove eventuali zeri iniziali ma mantiene almeno uno zero
        s = s.replaceFirst("^0+(?!$)", "");
        for (char c : s.toCharArray()) {
            digits.add(c - '0');
        }
    }

    @Override
    public String value() {
        StringBuilder sb = new StringBuilder();
        for (int d : digits) sb.append(d);
        return sb.toString();
    }

    @Override
    public int length() {
        return digits.size();
    }

    @Override
    public BigInt factory(int x) {
        return new BigIntLL(x);
    }

    @Override
    public BigInt incr() {
        return add(new BigIntLL(1));
    }

    @Override
    public BigInt decr() {
        // Controllo inline invece di usare isZero()
        if (digits.size() == 1 && digits.getFirst() == 0)
            throw new ArithmeticException("Decremento da zero non definito");
        return sub(new BigIntLL(1));
    }

    @Override
    public BigInt add(BigInt a) {
        LinkedList<Integer> res = new LinkedList<>();
        LinkedList<Integer> d2 = new LinkedList<>();
        for (char c : a.value().toCharArray()) d2.add(c - '0');

        ListIterator<Integer> it1 = digits.listIterator(digits.size());
        ListIterator<Integer> it2 = d2.listIterator(d2.size());

        int carry = 0;
        while (it1.hasPrevious() || it2.hasPrevious() || carry > 0) {
            int x = it1.hasPrevious() ? it1.previous() : 0;
            int y = it2.hasPrevious() ? it2.previous() : 0;
            int sum = x + y + carry;
            res.addFirst(sum % 10);
            carry = sum / 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int d : res) sb.append(d);
        return new BigIntLL(sb.toString());
    }

    @Override
    public BigInt sub(BigInt s) {
        if (this.compareTo(s) < 0)
            throw new ArithmeticException("Sottrazione negativa non ammessa");

        LinkedList<Integer> d1 = new LinkedList<>(this.digits);
        LinkedList<Integer> d2 = new LinkedList<>();
        for (char c : s.value().toCharArray()) d2.add(c - '0');

        ListIterator<Integer> it1 = d1.listIterator(d1.size());
        ListIterator<Integer> it2 = d2.listIterator(d2.size());

        LinkedList<Integer> res = new LinkedList<>();
        int borrow = 0;

        while (it1.hasPrevious()) {
            int x = it1.previous() - borrow;
            int y = it2.hasPrevious() ? it2.previous() : 0;
            if (x < y) {
                x += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            res.addFirst(x - y);
        }

        // Rimuove eventuali zeri iniziali
        while (res.size() > 1 && res.peekFirst() == 0)
            res.removeFirst();

        StringBuilder sb = new StringBuilder();
        for (int d : res) sb.append(d);
        return new BigIntLL(sb.toString());
    }

    @Override
    public BigInt mul(BigInt m) {
        BigInt result = new BigIntLL(0);
        LinkedList<Integer> d2 = new LinkedList<>();
        for (char c : m.value().toCharArray()) d2.add(c - '0');

        int shift = 0;
        ListIterator<Integer> it2 = d2.listIterator(d2.size());
        while (it2.hasPrevious()) {
            int digit = it2.previous();
            int carry = 0;
            LinkedList<Integer> partial = new LinkedList<>();

            for (int i = 0; i < shift; i++) partial.addLast(0);

            ListIterator<Integer> it1 = digits.listIterator(digits.size());
            while (it1.hasPrevious()) {
                int prod = it1.previous() * digit + carry;
                partial.addFirst(prod % 10);
                carry = prod / 10;
            }
            if (carry > 0) partial.addFirst(carry);

            StringBuilder sb = new StringBuilder();
            for (int d : partial) sb.append(d);
            result = result.add(new BigIntLL(sb.toString()));
            shift++;
        }
        return result;
    }

    @Override
    public BigInt div(BigInt d) {
        // Controllo inline invece di usare isZero()
        if (d.length() == 1 && d.value().equals("0"))
            throw new ArithmeticException("Divisione per zero");
        if (this.compareTo(d) < 0) return new BigIntLL(0);

        BigInt quotient = new BigIntLL(0);
        BigInt remainder = new BigIntLL(0);

        for (char c : this.value().toCharArray()) {
            remainder = remainder.mul(new BigIntLL(10));
            remainder = remainder.add(new BigIntLL(c - '0'));

            int count = 0;
            while (remainder.compareTo(d) >= 0) {
                remainder = remainder.sub(d);
                count++;
            }
            quotient = quotient.mul(new BigIntLL(10)).add(new BigIntLL(count));
        }
        return quotient;
    }

    @Override
    public BigInt rem(BigInt d) {
        return this.sub(this.div(d).mul(d));
    }

    @Override
    public BigInt pow(int exponent) {
        if (exponent < 0) throw new IllegalArgumentException("Esponente negativo non ammesso");
        BigInt base = this;
        BigInt result = new BigIntLL(1);
        int e = exponent;

        while (e > 0) {
            if ((e & 1) == 1) result = result.mul(base);
            base = base.mul(base);
            e >>= 1;
        }
        return result;
    }

    @Override
    public Iterator<Integer> iterator() {
        return digits.iterator();
    }

    @Override
    public int compareTo(BigInt other) {
        if (other == null) throw new NullPointerException("Confronto con null");

        int l1 = this.length();
        int l2 = other.length();
        if (l1 != l2) return Integer.compare(l1, l2);

        // Stessa lunghezza: confronto cifra per cifra dalla più significativa
        Iterator<Integer> itThis = this.digits.iterator();
        Iterator<Integer> itOther = other.iterator();
        while (itThis.hasNext() && itOther.hasNext()) {
            int a = itThis.next();
            int b = itOther.next();
            if (a != b) return Integer.compare(a, b);
        }
        return 0; // Uguali
    }

    // --- Main di prova ---
    public static void main(String[] args) {
        BigIntLL two = new BigIntLL(2);
        BigInt pow = two.pow(128);
        System.out.println("2^128 = " + pow);

        java.math.BigInteger b = new java.math.BigInteger("2");
        java.math.BigInteger p = b.pow(128);
        System.out.println("BigInteger: " + p);
    }
}
