import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeSwing extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    private int moveCount;

    public TicTacToeSwing() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        moveCount = 0;
        initializeGame();
    }

    private void initializeGame() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                panel.add(buttons[i][j]);
            }
        }

        add(panel);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("-")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                moveCount++;

                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (moveCount == 9) {
                    JOptionPane.showMessageDialog(null, "The game is a draw!");
                    resetGame();
                } else {
                    changePlayer();
                }
            }
        }
    }

    private boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().charAt(0) == currentPlayer && buttons[i][1].getText().charAt(0) == currentPlayer && buttons[i][2].getText().charAt(0) == currentPlayer) ||
                (buttons[0][i].getText().charAt(0) == currentPlayer && buttons[1][i].getText().charAt(0) == currentPlayer && buttons[2][i].getText().charAt(0) == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        return (buttons[0][0].getText().charAt(0) == currentPlayer && buttons[1][1].getText().charAt(0) == currentPlayer && buttons[2][2].getText().charAt(0) == currentPlayer) ||
               (buttons[0][2].getText().charAt(0) == currentPlayer && buttons[1][1].getText().charAt(0) == currentPlayer && buttons[2][0].getText().charAt(0) == currentPlayer);
    }

    private void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void resetGame() {
        moveCount = 0;
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("-");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToeSwing();
        });
    }
}
