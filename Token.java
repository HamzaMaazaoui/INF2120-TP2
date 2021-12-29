import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Les mots autorisés dans l'interpréteur
 */
public class Token {


    public static final String ABSTRACT = "abstrait";
    public static final String METHODE_FIN = "methodeFin";
    public static final String CLASSE_FIN = "classeFin";
    public static final String METHODE_DEBUT = "methodeDebut";
    public static final String CLASSE_DEBUT = "classeDebut";
    public static final String ATTRIBUT = "attribut";
    public static final String PARAMETRE = "parametre";


    public static final String IDENTIFICATEUR = "Identifier";


    public static final String PARENTH_OUVRANTE = "(";
    public static final String PARENTH_FERMANTE = ")";

    //separateur
    public static final String VIRRGULE = ",";

    //special tokens
    public static final String EOF = "EOF";

    /**
     * le type
     */
    private String tokenType;
    /**
     * le litteral
     */
    private String literal;
    /**
     * Le numero de ligne du token
     */
    private int numLigne;
    /**
     * Le numero de colonne du token
     */
    private int numColonne;

    /**
     * Constructeur
     *
     * @param tokenType le type du token.
     * @param literal   le littérale.
     */
    public Token(String tokenType, String literal) {
        this.tokenType = tokenType;
        this.literal = literal;
        this.numColonne = 0;
        this.numLigne = 0;
    }

    /**
     * Constructeur
     *
     * @param tokenType  le type du token.
     * @param literal    le littérale.
     * @param numLigne   le numero de ligne.
     * @param numColonne le numero de colonne.
     */
    public Token(String tokenType, String literal, int numLigne, int numColonne) {
        this.tokenType = tokenType;
        this.literal = literal;
        this.numLigne = numLigne;
        this.numColonne = numColonne;
    }

    /**
     * Comaparer deux objets
     *
     * @param {@code o} object a comparer
     * @return {@code true} si sont egaux {@code false} sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Token token = (Token) o;
        return tokenType.equals(token.tokenType) &&
                literal.equals(token.literal);
    }

    /**
     * Retourne le type token
     *
     * @return le type
     */
    public String getTokenType() {
        return tokenType;
    }

    public String getLiteral() {
        return literal;
    }

    /**
     * Retourne une chaine de caractere
     *
     * @return la chaine contenant les details du token
     */
    @Override
    public String toString() {
        return "Token{" +
                "tokenType='" + tokenType + '\'' +
                ", literal='" + literal + '\'' +
                ", lineNumber=" + numLigne +
                ", columnNumber=" + numColonne +
                '}';
    }

    /**
     * cree les mots autorisees avec le clavier
     */
    public static Map<String, String> KEYWORDS = new HashMap<String, String>();

    static {
        KEYWORDS.put("abstrait", ABSTRACT);
        KEYWORDS.put("attribut", ATTRIBUT);
        KEYWORDS.put("classeFin", CLASSE_FIN);
        KEYWORDS.put("methodeFin", METHODE_FIN);
        KEYWORDS.put("classeDebut", CLASSE_DEBUT);
        KEYWORDS.put("methodeDebut", METHODE_DEBUT);
        KEYWORDS.put("parametre", PARAMETRE);
        KEYWORDS = Collections.unmodifiableMap(KEYWORDS);
    }

    /**
     * Lit fichier entré par le client
     *
     * @param nomFichier fichier a lire
     * @return Retourne une liste
     */
    public static List<Token> LireFichier(String nomFichier) {
        List<Token> tokens = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try {
            File fichier = new File( nomFichier);
            Scanner scanner = new Scanner(fichier);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty())
                    lines.add(line);
            }
            for (String line : lines) {
                Lexicale lexicale = new Lexicale(line);
                while (lexicale.hasNext()) {
                    tokens.add(lexicale.next());
                }
            }
        } catch (IOException e) {
            System.out.println("le fichier n'exist pas!");
        }
        return tokens;
    }
}
