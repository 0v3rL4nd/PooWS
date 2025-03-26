package poo.backtracking;

public record Coppia(int i, int j) {
    @Override
    public String toString() {
        return "<"+i+";"+j+">";
    }
}
