/**
 * Genere la commande parametre
 */
public class CommandeParametre extends Commande {
    private String type;
    private String nom;

    /**
     * constructeur
     * @param type
     * @param nom
     */
    public CommandeParametre(String type, String nom) {
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

    /**
     * Retourne le nom
     * @return {@code nom}
     */
    public String getNom() {
        return nom;
    }


    public void interpret(ContexteInterpretation contexte) {
        contexte.parametre(this);
    }

    @Override
    public String toString() {
        return "CommandeParametre{" +
                "type='" + type + '\'' +
                ", name='" + nom + '\'' +
                '}';
    }
}
