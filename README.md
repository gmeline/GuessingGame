# GuessingGame

Pour lancer le jeux il faut faire deux commandes : 
- javac GuessingGameGUI.java
- java GuessingGameGUI


import java.util.Scanner;

public class SuiviLoyerComplet {
    public static void main(String[] args) {
        // Mois de l'année
        String[] mois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", 
                         "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
        
        // Tableaux pour chaque colonne
        double[] loyersAPayer = new double[12];
        double[] virementCAF = new double[12];
        double[] virementPerso = new double[12];
        double[] difference = new double[12]; // Trop perçu ou pas assez payé

        Scanner scanner = new Scanner(System.in);

        // Saisie des données pour chaque mois
        for (int i = 0; i < 12; i++) {
            System.out.println("Mois : " + mois[i]);
            
            // Entrer le loyer à payer
            System.out.print("Entrez le loyer à payer: ");
            loyersAPayer[i] = scanner.nextDouble();
            
            // Entrer le montant viré par la CAF
            System.out.print("Entrez le montant du virement de la CAF: ");
            virementCAF[i] = scanner.nextDouble();
            
            // Entrer le montant viré par vous-même
            System.out.print("Entrez le montant de votre virement: ");
            virementPerso[i] = scanner.nextDouble();
            
            // Calcul de la différence (trop perçu ou pas assez payé)
            difference[i] = (virementCAF[i] + virementPerso[i]) - loyersAPayer[i];
            System.out.println();
        }

        // Affichage du tableau récapitulatif
        System.out.println("\nRécapitulatif des loyers:");
        System.out.printf("%-10s %-15s %-15s %-15s %-20s%n", "Mois", "Loyer à payer", "CAF virement", "Votre virement", "Différence");
        for (int i = 0; i < 12; i++) {
            System.out.printf("%-10s %-15.2f %-15.2f %-15.2f %-20.2f%n", mois[i], loyersAPayer[i], virementCAF[i], virementPerso[i], difference[i]);
        }
    }
}