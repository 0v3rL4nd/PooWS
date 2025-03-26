package poo.backtracking;

public class Pair {
    private int i, j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "<" + i + ";" + j + ">";
    }

    @Override
    public int hashCode() {
        final int prime = 83;
        int h = i;
        h = h * prime + j;
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) return false;
        if (obj == this) return true;
        Pair c = (Pair) obj;
        return c.i == this.getI() && c.j == this.getJ();
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    public static void main(String[] args) {
        Pair p = new Pair(0,0);
        Pair q = new Pair(0,1);
        System.out.println(p.equals(q));
    }
}


