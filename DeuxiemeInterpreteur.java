import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

/**
 * Deuxième Interpréteur : générateur de code.
 */
public class DeuxiemeInterpreteur implements ContexteInterpretation {

    private Stack<File> pileFichier = new Stack<>();
    private Stack<String> pileNom = new Stack<>();
    private boolean estAbstrait = false;
    private boolean estPremierParametre = false;

    /**
     * genere la commande classe debut
     *
     * @param cmd
     */
    public void classeDebut(CommandeDebutClasse cmd) {
        try {
            this.pileFichier.push(creerFichier(cmd.getNom()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        pileNom.push(cmd.getNom());
        String r = "public ";
        if (estAbstrait) r += "abstract ";
        r += "class " + cmd.getNom() + " ";
        if (pileNom.size() > 1) {
            r += "extends ";
            r += pileNom.get(pileNom.size() - 2);
        }
        r += " {\n";
        //write to the file
        try {
            ecrireFichier(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.estAbstrait = false;
    }

    /**
     * genere la commande classe fin
     *
     * @param cmd
     */
    public void classeFin(CommandeFinClasse cmd) {
        String r = "}\n";
        try {
            ecrireFichier(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pileNom.pop();
        this.pileFichier.pop();
    }

    /**
     * genere la commande methode debut
     *
     * @param cmd
     */
    public void methodeDebut(CommandeDebutMethode cmd) {
        String r = "\tpublic ";
        if (estAbstrait)
            r += "abstract ";
        r += cmd.getType() + " " + cmd.getNom() + "( ";
        this.estPremierParametre = true;
        try {
            ecrireFichier(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * genere la commande attribut
     *
     * @param cmd
     */
    public void attribut(CommandeAttribut cmd) {
        String r = "\tprivate " + cmd.getType() + " " + cmd.getNom() + ";\n\n";
        r += "\tpublic " + cmd.getType() + " get" +
                cmd.getNom().substring(0, 1).toUpperCase() + cmd.getNom().substring(1) + "() {\n" +
                "\t\treturn " + cmd.getNom() + ";\n" +
                "    }\n";
        r += "\tpublic void set" + cmd.getNom().substring(0, 1).toUpperCase() + cmd.getNom().substring(1) +
                "(" + cmd.getType() + " " + cmd.getNom() + ") {\n" + "\t\tthis." + cmd.getNom() + " = " + cmd.getNom() + ";\n" +
                "    }\n";
        try {
            ecrireFichier(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abstrait(CommandeAbstrait cmd) {
        this.estAbstrait = true;
    }

    /**
     * genere la commande parametre
     *
     * @param cmd
     */
    public void parametre(CommandeParametre cmd) {
        String r = "";
        if (!estPremierParametre)
            r += ", ";
        r += cmd.getType() + " " + cmd.getNom();
        estPremierParametre = false;
        try {
            ecrireFichier(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * genere la commande methode fin
     *
     * @param cmd
     */
    public void methodeFin(CommandeFinMethode cmd) {
        String r = ")";
        if (estAbstrait)
            r += ";";
        else
            r += "{\n    }";
        r += "\n\n";
        estAbstrait = false;
        try {
            ecrireFichier(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ecrit dans une fichier java
     *
     * @param r Le texte a ecrire dans le fichier
     * @throws IOException si  le {@code s} n'est pas valide
     */
    private void ecrireFichier(String r) throws IOException {
        FileWriter ecrire = new FileWriter(this.pileFichier.peek(), true);
        ecrire.write(r);
        ecrire.close();
    }

    /**
     * Cree un fichier java
     *
     * @param nom le nom du fichier
     * @throws IOException si le {@nom} n'est pas valide
     */
    private File creerFichier(String nom) throws IOException, URISyntaxException {
        //String path = "tests/generated/java";
         File f = new File("java\\"+"/"
                 + nom.substring(0, 1).toUpperCase() + nom.substring(1) + ".java");
       f.getParentFile().mkdir();
         return f;
    }

    /**
     * Supprime les fichiers generees dans le repertoire tests/java
     */
    public static void supprimerRepJava() {
        String path = "java";
        File javaDossier = new File(path);
        File[] fichiers = javaDossier.listFiles();
        if(fichiers!=null){
            for (File fichier : fichiers) {

                if (!fichier.delete()) {

                    System.out.println("Erreur de supprimer " + fichier);
                }
            }
        }

    }
}
