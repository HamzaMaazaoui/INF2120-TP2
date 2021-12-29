/**
 * Genere la commande abstrait
 */
public class CommandeAbstrait extends Commande {

    public void interpret(ContexteInterpretation contexte) {
        contexte.abstrait(this);
    }
}
