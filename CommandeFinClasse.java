/**
 * Genere la commande fin classe
 */
public class CommandeFinClasse extends Commande {

    public void interpret(ContexteInterpretation contexte) {
        contexte.classeFin(this);
    }
}
