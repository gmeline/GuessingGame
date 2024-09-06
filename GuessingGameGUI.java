import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;


public class GuessingGameGUI {
    private static int attempts = 0;//compteur pour suivre le nombre d'essais du joueur
    private static int secretNumber;//Nombre que le joueur dois deviner
    private static JLabel messageLabel;//Afficher le message au joueur
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Devine le nombre !");//Création de la fenêtre de jeux

        JPanel panel = new JPanel();//Crée un panneau pour contenir les autres conposants
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField textField = new JTextField(10);//Champs de texte poru que le joueur entre son nombre
        textField.setPreferredSize(new Dimension(80, 30));
        RoundedButton guessButton = new RoundedButton("Valide");//Bouton pour valider
        RoundedButton replayButton = new RoundedButton("Rejouer");//Bouton pour rejouer
        messageLabel = new JLabel("Devine le nombre entre 1 et 100:"); //Message qui affiche la règle du jeux
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.setBackground(new Color(230, 240, 255));//Définit la couleur de fond du jeux

        // Initialisation/Réinitialisation du jeu
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
        frame.setSize(600, 250);//Taille de la fenêtre de jeux
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Quand la fenêtre se ferme le programme se ferme aussi 
        frame.setVisible(true);//Rend la fenêtre visible
    }

    // Méthode pour initialiser ou réinitialiser le jeu
    private static void initializeGame() {
        Random rand = new Random();//Générateur du nombre aléatoire
        secretNumber = rand.nextInt(100) + 1;//Génère le nombre aléatoire entre 1 et 100
        attempts = 0; // Réinitialiser le compteur d'essais
    }

    // Méthode pour gérer la tentative
    private static void handleGuess(JTextField textField) {
        int guess;//Variable qui permet de stocker le guess du joueur
        try {
            guess = Integer.parseInt(textField.getText());//Vérifie que le joueur entre bien un nombre
        } catch (NumberFormatException ex) {
            messageLabel.setText("Entrée invalide! Veuillez entrer un nombre.");//Si le joueur n'entre pas un nombre alors ce message apparaît
            return;
        }

        attempts++; // Incrémente le compteur d'essais

        if (guess < secretNumber) {//Compare le guess du joueur avec le nombre générer par l'ordinateur
            messageLabel.setText("Trop bas! Essaye encore.");
        } else if (guess > secretNumber) {
            messageLabel.setText("Trop haut! Essaye encore.");
        } else {
            messageLabel.setText("Félicitation! Tu as trouvé le bon nombre en " + attempts + " essais.");
        }
    }

    // Classe pour un bouton arrondi personnalisé
    static class RoundedButton extends JButton {//Création d'un bouton avec les bords arrondis sans roundedbutton alors ce serai un bouton rectangulaire
        private static final int ARC_WIDTH = 20;
        private static final int ARC_HEIGHT = 20;

        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);//On ne met pas de remplissage
            setFocusPainted(false);//On ne met pas de bordure
            setBorderPainted(false);//On ne met par d'indication de focus
        }

        
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
            super.paintComponent(g);
        }

        
        protected void paintBorder(Graphics g) {
            g.setColor(Color.GRAY);
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        }
    }
}
