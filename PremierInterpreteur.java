/**
 * Premier interpréteur : cet interpréteur va vérifier l’ordre des commandes dans la suite.
 */
public class PremierInterpreteur implements ContexteInterpretation {



    /**
     * Le mode
     */
    protected Mode mode = Mode.FClasse;
    /**
     * boolean
     */
    protected boolean estAbstrait = false;
    /**
     * Nombre d'ouverture
     */
    protected int nbrOuverture = 0;

    public void abstrait(CommandeAbstrait cmd) {
        if (estAbstrait) {
            System.err.println(Textes.MSG_ERREUR_ABSTRAIT);
            System.exit(1);
        }
        estAbstrait = true;
    }

    /**
     * Verifie la commande classe debut
     *
     * @param cmd
     */
    public void classeDebut(CommandeDebutClasse cmd) {
        if (mode == Mode.DMethode || mode == Mode.DParametre) {
            System.err.println(Textes.MSG_ERREUR_CLASSE);
            System.exit(1);

        }
        ++this.nbrOuverture;
        this.mode = Mode.DClasse;
        this.estAbstrait = false;
    }

    /**
     * Verifie la commande classe fin
     *
     * @param cmd
     */
    public void classeFin(CommandeFinClasse cmd) {
        if (mode == Mode.DMethode || mode == Mode.DParametre || this.estAbstrait || this.nbrOuverture <=0) {
            System.err.println(Textes.MSG_ERREUR_FIN_CLASSE);
            System.exit(1);
        }

        --this.nbrOuverture;
        this.mode = Mode.FClasse;
    }

    /**
     * Verifie la commande attribut
     *
     * @param cmd
     */
    public void attribut(CommandeAttribut cmd) {
        if ((this.mode != Mode.DClasse && this.mode != Mode.DAttribut) || this.estAbstrait) {
            System.err.println(Textes.MSG_ERREUR_ATTRIBUT);
            System.exit(1);
        }
        this.mode = Mode.DAttribut;
    }

    /**
     * Verifie la commande methode Debut
     *
     * @param cmd
     */
    public void methodeDebut(CommandeDebutMethode cmd) {
        if (this.mode != Mode.DClasse && this.mode != Mode.DAttribut && this.mode != Mode.FMethode) {
            System.err.println(Textes.MSG_ERREUR_METHODE);
            System.exit(1);

        }
        this.mode = Mode.DMethode;
        this.estAbstrait = false;
    }


    /**
     * Verifie la commande parametre
     *
     * @param cmd
     */
    public void parametre(CommandeParametre cmd) {
        if (this.mode != Mode.DParametre && this.mode != Mode.DMethode) {
            System.err.println(Textes.MSG_ERREUR_PARAMETRE);
            System.exit(1);
        }
        this.mode = Mode.DParametre;
    }

    /**
     * Verifie la methode fin
     *
     * @param cmd
     */
    public void methodeFin(CommandeFinMethode cmd) {
        if ((this.mode != Mode.DParametre && this.mode != Mode.DMethode) || this.estAbstrait ) {
            System.err.println(Textes.MSG_ERREUR_FIN_METHODE);
            System.exit(1);
        }

        this.mode = Mode.FMethode;
    }

    /**
     * Retourne le mode
     *
     * @return {@code Fclasse}
     */
    public Mode getMode() {

        return mode;
    }

    /**
     * Retourne la valeur estAbstrait
     *
     * @return {@code false}
     */
    public boolean isEstAbstrait() {

        return estAbstrait;
    }

    /**
     * Retourne le nombre d'ouverture
     *
     * @return {@code 0 }
     */
    public int getNbrOuverture() {

        return nbrOuverture;
    }
}
