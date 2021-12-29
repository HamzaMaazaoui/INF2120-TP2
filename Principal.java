/**
 * Écrivez vos nom ici :
 *
 * @HAMZA MAAZAOUI
 * @MAAH08069405
 * @WISSAL EDDASSI
 * @EDDW02619802
 */


import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du fichier de description : ");
        String fileName = scanner.nextLine();

        List<Token> tokens = Token.LireFichier(fileName);

        ContexteInterpretation interpreteur = new PremierInterpreteur();
        List<Commande> commandes = CommandeToken.CommandeToken(tokens);

        //appeler le premier interpreteur
        for (Commande commande : commandes) {
            commande.interpret(interpreteur);
        }

        DeuxiemeInterpreteur.supprimerRepJava();
        TroisiemeInterpreteur.supprimerRepUml();
        interpreteur = new DeuxiemeInterpreteur();
        //Appeler le dexuieme interpreteur pour generer java
        for (Commande commande : commandes) {
            commande.interpret(interpreteur);
        }

        interpreteur = new TroisiemeInterpreteur("uml.tex");
        //Appeler le troisieme interpreteur pour generer Uml
        for (Commande commande : commandes) {
            commande.interpret(interpreteur);
        }




    }

}
