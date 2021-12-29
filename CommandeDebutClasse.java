/**
 * Genere la commande debut classe
 */
public class CommandeDebutClasse extends Commande{
  private String nom;

  /**
   * Constructeur
   * @param nom
   */
  public CommandeDebutClasse(String nom) {

    this.nom = nom;
  }

  /**
   * Retourne le nom
   * @return {@code nom}
   */
  public String getNom() {

    return nom;
  }

  public void interpret(ContexteInterpretation contexte) {

    contexte.classeDebut(this);
  }

  @Override
  public String toString() {
    return "CommandeDebutClasse{" +
        "name='" + nom + '\'' +
        '}';
  }
}
