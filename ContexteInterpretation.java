/**
 * décrit les commandes a generer
 */
public interface ContexteInterpretation {
    void classeDebut(CommandeDebutClasse cmd);

    void classeFin(CommandeFinClasse cmd);

    void methodeDebut(CommandeDebutMethode cmd);

    void attribut(CommandeAttribut cmd);

    void abstrait(CommandeAbstrait cmd);

    void parametre(CommandeParametre cmd);

    void methodeFin(CommandeFinMethode cmd);
}
