import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

/**
 * Troisième interpréteur : générateur de représentation UML
 */
public class TroisiemeInterpreteur implements ContexteInterpretation {

    private String content = "";
    private String nomFichier;

    /**
     * contient les variables d’instance
     */
    class Etat {
        public boolean premierAttribut = true;
        public boolean premiereMethode = true;
        public boolean premiereClasse = true;
    }

    private int nbrClasse = 0;
    private Stack<Etat> pileEtat = new Stack<>();
    private boolean estAbstrait = false;
    private boolean estPremierParametre = false;

    /**
     * Constructeur
     *
     * @param nomFichier
     */
    public TroisiemeInterpreteur(String nomFichier) {
        this.nomFichier = nomFichier;
        try {
            creerFichier(nomFichier);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * genere la commande classe debut
     *
     * @param cmd
     */
    public void classeDebut(CommandeDebutClasse cmd) {
        String uml = "";
        if (this.nbrClasse == 0)
            uml += DescriptionLatex.PAGE_DEBUT;
        else {
            if (this.pileEtat.peek().premiereClasse) {
                uml += DescriptionLatex.CLASSE_FIN;
                uml += DescriptionLatex.LISTE_CLASSE_DEBUT;
                this.pileEtat.peek().premiereClasse = false;
            }
            uml += DescriptionLatex.CLASSE_INTERNE_PREFIX;

        }
        this.pileEtat.push(new Etat());
        uml += DescriptionLatex.CLASSE_DEBUT;
        if (this.estAbstrait)
            uml += DescriptionLatex.ABSTRAIT_DEBUT;
        uml += cmd.getNom().substring(0, 1).toUpperCase()
                + cmd.getNom().substring(1);
        if (this.estAbstrait) {
            uml += DescriptionLatex.ABSTRAIT_FIN;
            this.estAbstrait = false;
        }
        ++this.nbrClasse;
        try {
            ecrireFichier(uml);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * genere la commande classe fin
     *
     * @param cmd
     */
    public void classeFin(CommandeFinClasse cmd) {
        String uml = "";
        --this.nbrClasse;
        if (this.pileEtat.peek().premiereClasse)
            uml += DescriptionLatex.CLASSE_FIN;
        else
            uml += DescriptionLatex.LISTE_CLASSE_FIN;
        if (this.nbrClasse == 0)
            uml += DescriptionLatex.PAGE_FIN;
        else
            uml += DescriptionLatex.CLASSE_INTERNE_SUFFIX;
        this.pileEtat.pop();
        try {
            ecrireFichier(uml);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * genere la commande methode debut
     *
     * @param cmd
     */
    public void methodeDebut(CommandeDebutMethode cmd) {
        String uml = "";
        if (this.pileEtat.peek().premiereMethode) {
            uml += DescriptionLatex.LISTE_METHODE_DEBUT;
            this.pileEtat.peek().premiereMethode = false;
        } else
            uml += DescriptionLatex.LISTE_METHODE_SEP;
        if (this.estAbstrait)
            uml += DescriptionLatex.ABSTRAIT_DEBUT;
        if (!cmd.getType().equals("void"))
            uml += cmd.getType() + " ";
        uml += cmd.getNom() + DescriptionLatex.PARAMETRE_DEBUT;
        this.estPremierParametre = true;
        try {
            ecrireFichier(uml);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * genere la commande attribut
     *
     * @param cmd
     */
    public void attribut(CommandeAttribut cmd) {
        String uml = "";
        if (this.pileEtat.peek().premierAttribut) {
            uml += DescriptionLatex.LISTE_ATTRIBUT_DEBUT;
            this.pileEtat.peek().premierAttribut = false;
        } else {
            uml += DescriptionLatex.LISTE_ATTRIBUT_SEP;
        }
        uml += cmd.getNom() + ":" + cmd.getType();
        try {
            ecrireFichier(uml);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * genere la commande abstrait
     *
     * @param cmd
     */
    public void abstrait(CommandeAbstrait cmd) {
        this.estAbstrait = true;
    }

    /**
     * genere la commande parametre
     *
     * @param cmd
     */
    public void parametre(CommandeParametre cmd) {
        String uml = "";
        if (this.estPremierParametre)
            this.estPremierParametre = false;
        else
            uml += DescriptionLatex.PARAMETRE_SEP;
        uml += cmd.getType();
        try {
            ecrireFichier(uml);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * genere la commande methode fin
     *
     * @param cmd
     */
    public void methodeFin(CommandeFinMethode cmd) {
        String uml = "";
        uml += DescriptionLatex.PARAMETRE_FIN;
        if (this.estAbstrait) {
            this.estAbstrait = false;
            uml += DescriptionLatex.ABSTRAIT_FIN;
        }
        try {
            ecrireFichier(uml);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * ecrit dans fichier latex.
     *
     * @param texte le texte a ecrire.
     */
    private void ecrireFichier(String texte) throws IOException, URISyntaxException {
        File fichier = new File("uml/" + this.nomFichier);
        EcrireLatex ecrireLatex = new EcrireLatex(fichier);
        this.content += texte + "\n";
        if (this.nbrClasse == 0) {
            ecrireLatex.ecrire(this.content);
        }

    }

    /**
     * Cree un fichier latex
     *
     * @param nom le nom du fichier
     * @throws IOException si le {@nom} n'est pas valide
     */
    private File creerFichier(String nom) throws IOException, URISyntaxException {
      //  String path = "tests/generated/uml";

        File f =new File ("uml\\"+nom);
        f.getParentFile().mkdir();
        return f;    }

    /**
     * Supprime les fichiers dans le repertoire tests/uml
     */
    public static void supprimerRepUml() {
        String path = "uml";
        File umlDossier = new File(path);
        File[] fichiers = umlDossier.listFiles();
        if (fichiers!=null){
            for (File fichier : fichiers) {
                if (!fichier.delete()) {
                    System.out.println("Erreur de supprimer " + fichier);
                }
            }
        }


    }
}
