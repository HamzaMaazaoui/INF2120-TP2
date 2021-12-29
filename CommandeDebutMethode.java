/**
 * Genere la commande debut methode
 */
public class CommandeDebutMethode extends Commande {
    private String nom;
    private String type;

    /**
     * constructeur
     * @param type
     * @param nom
     */
    public CommandeDebutMethode(String type, String nom) {
        this.type = type;
        this.nom = nom;
    }

    /**
     * Retourne le nom
     * @return {@code nom}
     */
    public String getNom() {

        return nom;
    }
    /**
     * Retourne le type
     * @return {@code type}
     */
    public String getType() {
        return type;
    }

    public void interpret(ContexteInterpretation contexte) {
        contexte.methodeDebut(this);
    }

    /**
     * Retourne une chaine de caractere
     * @return la chaine a afficher
     */
    @Override
    public String toString() {
        return "CommandeDebutMethode{" +
                "name='" + nom + '\'' +
                '}';
    }
}
