package poo.tracce.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


class SudokuGui extends JFrame {
    private SudokuSolver solver;
    private JTextField[][] celleGUI;
    private JButton btnSetup, btnStart, btnReset, btnPulisci;
    private JSpinner spinnerSoluzioni;
    private JLabel lblInfo;
    private boolean modalitaSetup;
    private List<int[][]> soluzioniTrovate;
    private int soluzioneCorrente;
    private JButton btnPrev, btnNext;

    public SudokuGui() {
        solver = new SudokuSolver();
        modalitaSetup = false;
        soluzioniTrovate = new ArrayList<>();
        soluzioneCorrente = 0;

        inizializzaGUI();
        aggiornaVisualizzazione();
    }

    private void inizializzaGUI() {
        setTitle("Risolutore Sudoku - Backtracking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pannello principale del sudoku
        JPanel pannelloSudoku = creaPannelloSudoku();
        add(pannelloSudoku, BorderLayout.CENTER);

        // Pannello controlli
        JPanel pannelloControlli = creaPannelloControlli();
        add(pannelloControlli, BorderLayout.SOUTH);

        // Pannello info
        lblInfo = new JLabel("Modalità normale - Clicca Setup per modificare le celle");
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInfo, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel creaPannelloSudoku() {
        JPanel pannello = new JPanel(new GridLayout(9, 9, 1, 1));
        pannello.setBackground(Color.BLACK);
        pannello.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        celleGUI = new JTextField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                celleGUI[i][j] = new JTextField();
                celleGUI[i][j].setHorizontalAlignment(JTextField.CENTER);
                celleGUI[i][j].setFont(new Font("Arial", Font.BOLD, 20));

                // Colori per distinguere i settori
                Color coloreSfondo = ((i / 3 + j / 3) % 2 == 0) ?
                        new Color(240, 240, 240) : Color.WHITE;
                celleGUI[i][j].setBackground(coloreSfondo);

                // Listener per il click del mouse
                final int riga = i;
                final int col = j;

                celleGUI[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (modalitaSetup) {
                            gestisciClickSetup(riga, col);
                        }
                    }
                });

                pannello.add(celleGUI[i][j]);
            }
        }

        return pannello;
    }

    private JPanel creaPannelloControlli() {
        JPanel pannello = new JPanel(new FlowLayout());

        btnSetup = new JButton("Setup");
        btnStart = new JButton("Start");
        btnReset = new JButton("Reset");
        btnPulisci = new JButton("Pulisci");

        // Spinner per il numero di soluzioni
        spinnerSoluzioni = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        JLabel lblSoluzioni = new JLabel("N° Soluzioni:");

        // Pulsanti navigazione soluzioni
        btnPrev = new JButton("< Prec");
        btnNext = new JButton("Succ >");
        btnPrev.setEnabled(false);
        btnNext.setEnabled(false);

        // Listeners
        btnSetup.addActionListener(e -> toggleModalitaSetup());
        btnStart.addActionListener(e -> risolviSudoku());
        btnReset.addActionListener(e -> resetSudoku());
        btnPulisci.addActionListener(e -> pulisciSudoku());
        btnPrev.addActionListener(e -> mostraSoluzionePrecedente());
        btnNext.addActionListener(e -> mostraSoluzioneSuccessiva());

        pannello.add(btnSetup);
        pannello.add(btnStart);
        pannello.add(btnPulisci);
        pannello.add(btnReset);
        pannello.add(new JSeparator(SwingConstants.VERTICAL));
        pannello.add(lblSoluzioni);
        pannello.add(spinnerSoluzioni);
        pannello.add(new JSeparator(SwingConstants.VERTICAL));
        pannello.add(btnPrev);
        pannello.add(btnNext);

        return pannello;
    }

    private void gestisciClickSetup(int riga, int col) {
        String input = JOptionPane.showInputDialog(
                this,
                "Inserisci valore per cella (" + riga + "," + col + ") (1-9, 0 per vuoto):",
                "Imposta Cella",
                JOptionPane.QUESTION_MESSAGE
        );

        if (input != null) {
            try {
                int valore = Integer.parseInt(input.trim());
                if (valore >= 0 && valore <= 9) {
                    solver.impostaCella(riga, col, valore);
                    aggiornaVisualizzazione();
                } else {
                    JOptionPane.showMessageDialog(this, "Valore non valido! Usa 0-9.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Formato non valido! Inserisci un numero.");
            }
        }
    }

    private void toggleModalitaSetup() {
        modalitaSetup = !modalitaSetup;
        if (modalitaSetup) {
            btnSetup.setText("Fine Setup");
            btnStart.setEnabled(false);
            lblInfo.setText("Modalità SETUP - Clicca sulle celle per impostare i valori");
        } else {
            btnSetup.setText("Setup");
            btnStart.setEnabled(true);
            lblInfo.setText("Modalità normale - Pronto per risolvere");
        }
    }

    private void risolviSudoku() {
        btnStart.setEnabled(false);
        btnSetup.setEnabled(false);
        lblInfo.setText("Risoluzione in corso...");

        SwingWorker<List<int[][]>, Void> worker = new SwingWorker<List<int[][]>, Void>() {
            @Override
            protected List<int[][]> doInBackground() throws Exception {
                int numSoluzioni = (Integer) spinnerSoluzioni.getValue();
                return solver.risolvi(numSoluzioni);
            }

            @Override
            protected void done() {
                try {
                    soluzioniTrovate = get();
                    soluzioneCorrente = 0;

                    if (soluzioniTrovate.isEmpty()) {
                        lblInfo.setText("Nessuna soluzione trovata!");
                        JOptionPane.showMessageDialog(SudokuGui.this,
                                "Impossibile risolvere il Sudoku con le impostazioni correnti.");
                    } else {
                        mostraSoluzione(0);
                        lblInfo.setText("Trovate " + soluzioniTrovate.size() + " soluzioni");

                        // Abilita navigazione se ci sono più soluzioni
                        if (soluzioniTrovate.size() > 1) {
                            btnNext.setEnabled(true);
                        }
                    }

                } catch (Exception ex) {
                    lblInfo.setText("Errore durante la risoluzione!");
                    ex.printStackTrace();
                }

                btnStart.setEnabled(true);
                btnSetup.setEnabled(true);
            }
        };

        worker.execute();
    }

    private void mostraSoluzione(int indice) {
        if (indice >= 0 && indice < soluzioniTrovate.size()) {
            int[][] soluzione = soluzioniTrovate.get(indice);

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    celleGUI[i][j].setText(String.valueOf(soluzione[i][j]));

                    // Colora le celle secondo lo stato
                    Stato stato = solver.getCella(i, j).getStato();
                    if (stato == Stato.IMPOSTATO) {
                        celleGUI[i][j].setForeground(Color.RED);
                    } else {
                        celleGUI[i][j].setForeground(new Color(255, 140, 0)); // Arancione
                    }
                }
            }

            // Aggiorna pulsanti navigazione
            btnPrev.setEnabled(indice > 0);
            btnNext.setEnabled(indice < soluzioniTrovate.size() - 1);

            lblInfo.setText("Soluzione " + (indice + 1) + " di " + soluzioniTrovate.size());
        }
    }

    private void mostraSoluzionePrecedente() {
        if (soluzioneCorrente > 0) {
            soluzioneCorrente--;
            mostraSoluzione(soluzioneCorrente);
        }
    }

    private void mostraSoluzioneSuccessiva() {
        if (soluzioneCorrente < soluzioniTrovate.size() - 1) {
            soluzioneCorrente++;
            mostraSoluzione(soluzioneCorrente);
        }
    }

    private void resetSudoku() {
        solver.reset();
        soluzioniTrovate.clear();
        btnPrev.setEnabled(false);
        btnNext.setEnabled(false);
        aggiornaVisualizzazione();
        lblInfo.setText("Sudoku resettato");
    }

    private void pulisciSudoku() {
        solver.pulisci();
        soluzioniTrovate.clear();
        btnPrev.setEnabled(false);
        btnNext.setEnabled(false);
        aggiornaVisualizzazione();
        lblInfo.setText("Soluzioni pulite - Mantenute le impostazioni");
    }

    private void aggiornaVisualizzazione() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cella cella = solver.getCella(i, j);

                if (cella.isVuota()) {
                    celleGUI[i][j].setText("");
                    celleGUI[i][j].setForeground(Color.BLACK);
                } else {
                    celleGUI[i][j].setText(String.valueOf(cella.getValore()));

                    if (cella.getStato() == Stato.IMPOSTATO) {
                        celleGUI[i][j].setForeground(Color.RED);
                    } else {
                        celleGUI[i][j].setForeground(Color.BLACK);
                    }
                }
            }
        }
    }
}