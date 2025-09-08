package poo.tracce.Sudoku;

import javax.swing.*;

public class SudokuMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new SudokuGui().setVisible(true);
        });
    }
}