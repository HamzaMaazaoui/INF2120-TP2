import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Permet d'ecrire le fichier latex
 */
public class EcrireLatex {

    private File fichier;
    private FileWriter fichierEcrire;
    private boolean estInitialise = false;

    /**
     * Ecrit dans le fichier latex
     *
     * @param fichier fichier source
     * @throws IOException s'il n'y a pas de {@code fichier}
     */
    public EcrireLatex(File fichier) throws IOException {
        this.fichier = fichier;
        this.fichierEcrire = new FileWriter(fichier, false);
    }

    /**
     * Retourne la valeur boolean
     *
     * @return {@code false}
     */
    public boolean estInitialise() {
        return this.estInitialise;
    }

    /**
     * Ecrit debut fichier Latex
     *
     * @throws IOException 'il n'y a pas de fichier
     */
    public void debutFichier() throws IOException {
        if (!this.estInitialise) {
            fichierEcrire.write(DescriptionLatex.FICHIER_DEBUT);
            this.estInitialise = true;
        }
    }

    /**
     * Ecrit fin fichier Latex
     *
     * @throws IOException s'il n'y a pas de fichier
     */
    public void finFichier() throws IOException {
        fichierEcrire.write(DescriptionLatex.FICHIER_FIN);
        this.fichierEcrire.close();
    }

    /**
     * ecrit le fichier latex
     * @param latex le texte a ecrire
     * @throws IOException s'il n'y a pas de {@code latex}
     */
    public void ecrire(String latex) throws IOException {
        debutFichier();
        fichierEcrire.write(latex);
        finFichier();
    }
}
