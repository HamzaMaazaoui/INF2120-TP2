/**
 * Genere la commande attribut
 */
public class CommandeAttribut extends Commande {
    private String type;
    private String nom;

    /**
     * Le constructeur
     *
     * @param type
     * @param nom
     */
    public CommandeAttribut(String type, String nom) {
        this.type = type;
        this.nom = nom;
    }

    /**
     * Retourne le type
     * @return {@code type}
     */
    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public void interpret(ContexteInterpretation contexte) {
        contexte.attribut(this);
    }

    /**
     * Retourne la chaine de caractere
     * @return la chaine a afficher
     */
    @Override
    public String toString() {
        return "CommandeAttribut{" +
                "type='" + type + '\'' +
                ", name='" + nom + '\'' +
                '}';
    }
}
