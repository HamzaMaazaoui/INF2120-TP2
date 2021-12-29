/**
 * Genere la commande fin methode
 */
public class CommandeFinMethode extends Commande {

    public void interpret(ContexteInterpretation contexte) {
        contexte.methodeFin(this);
    }
}
