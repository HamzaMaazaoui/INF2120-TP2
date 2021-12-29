/**
 * Stocke une paire de valeurs
 * @param <E> la cle
 * @param <T> la valeur
 */
public class Pair<E, T> {
    E cle;
    T valeur;

    /**
     * Constructeur
     * @param cle  la cle
     * @param valeur la valeur
     */
    public Pair(E cle, T valeur) {
        this.cle = cle;
        this.valeur = valeur;
    }

    /**
     * Retourne la cle de la pair
     * @return {@code cle } retourne la cle
     */
    public E getCle() {

        return cle;
    }

    /**
     * Modifie la cle de la pair
     * @param {@code cle} retroune la nouvelle cle
     */
    public void setCle(E cle) {
        this.cle = cle;
    }

    /**
     * Retourne la valeur de la paire
     * @return {@code valeur} retourne la valeur
     */
    public T getValeur() {

        return valeur;
    }

    /**
     * Modifie la valeur
     * @param {@code valeur} retourne la nouvelle valeur
     */
    public void setValeur(T valeur) {

        this.valeur = valeur;
    }
}