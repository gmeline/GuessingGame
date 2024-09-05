import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class GuessingGameGUI {
    private static int attempts = 0;
    private static int secretNumber;
    private static JLabel messageLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Devine le nombre !");
        frame.setSize(600, 250);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField textField = new JTextField(10);
        textField.setPreferredSize(new Dimension(80, 30));
        RoundedButton guessButton = new RoundedButton("Valide");
        RoundedButton replayButton = new RoundedButton("Rejouer");
        messageLabel = new JLabel("Devine le nombre entre 1 et 100:");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.setBackground(new Color(230, 240, 255));

        // Initialisation du jeu
        initializeGame();

        // ActionListener pour le bouton "Valide"
        ActionListener guessListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess(textField);
            }
        };

        // ActionListener pour le bouton "Rejouer"
        ActionListener replayListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame();
                textField.setText("");
                messageLabel.setText("Devine le nombre entre 1 et 100:");
            }
        };

        // Ajouter ActionListener aux boutons
        guessButton.addActionListener(guessListener);
        textField.addActionListener(guessListener);
        replayButton.addActionListener(replayListener);

        panel.add(messageLabel);
        panel.add(textField);
        panel.add(guessButton);
        panel.add(replayButton);

        frame.add(panel);
        frame.setSize(600, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Méthode pour initialiser ou réinitialiser le jeu
    private static void initializeGame() {
        Random rand = new Random();
        secretNumber = rand.nextInt(100) + 1;
        attempts = 0; // Réinitialiser le compteur d'essais
    }

    // Méthode pour gérer la tentative
    private static void handleGuess(JTextField textField) {
        int guess;
        try {
            guess = Integer.parseInt(textField.getText());
        } catch (NumberFormatException ex) {
            messageLabel.setText("Entrée invalide! Veuillez entrer un nombre.");
            return;
        }

        attempts++; // Incrémente le compteur d'essais

        if (guess < secretNumber) {
            messageLabel.setText("Trop bas! Essaye encore.");
        } else if (guess > secretNumber) {
            messageLabel.setText("Trop haut! Essaye encore.");
        } else {
            messageLabel.setText("Félicitation! Tu as trouvé le bon nombre en " + attempts + " essais.");
        }
    }

    // Classe pour un bouton arrondi personnalisé
    static class RoundedButton extends JButton {
        private static final int ARC_WIDTH = 20;
        private static final int ARC_HEIGHT = 20;

        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(Color.GRAY);
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        }
    }
}
