/**
 * une classe permet de créer et initialiser une analyse lexicale
 */
public class Lexicale {

    private String src;
    private int pos;
    private byte current;
    private int inputSize;
    private int line;
    private int column;

    /**
     * Cree et initialiser une nouvelle analyse lexicale a partir de la source
     *
     * @param src source.
     */
    public Lexicale(String src) {
        this.src = src;
        this.pos = 0;
        this.current = (byte) src.charAt(pos);
        this.inputSize = src.length();
        this.line = 1;
        this.column = 1;
    }

    public Token next() {
        Token result;
        ignorerEspace();
        switch (this.current) {
            case ',':
                result = new Token(Token.VIRRGULE, ",", this.line, this.column);
                break;
            case '(':
                result = new Token(Token.PARENTH_OUVRANTE, "(", this.line, this.column);
                break;
            case ')':
                result = new Token(Token.PARENTH_FERMANTE, ")", this.line, this.column);
                break;
            case 0:
                result = new Token(Token.EOF, "EOF", this.line, this.column);
                break;
            default:
                result = litIdentificateur();
        }
        advance();
        return result;
    }

    /**
     * Lit un identificateur.
     *
     * @return un token.
     */
    private Token litIdentificateur() {
        int start = this.pos;

        while (identifierCharacter(peek()))
            advance();

        String literal = this.src.substring(start, this.pos + 1);
        if (!estValideIdentificateur(literal)) {
            System.err.println(literal + " identificateur non valide.");
            System.exit(1);
        }
        String literalType = Token.IDENTIFICATEUR;
        if (Token.KEYWORDS.containsKey(literal))
            literalType = Token.KEYWORDS.get(literal);

        return new Token(literalType, literal, this.line, this.column);
    }

    /**
     * Verifie l'identificateur est valide ou non .
     *
     * @param str L'identificateur a checker.
     * @return un boolean.
     */
    private boolean estValideIdentificateur(String str) {
        boolean estValide = true;

        if (!(Character.isLetter(str.charAt(0)) || str.charAt(0) == '_'))
            estValide = false;

        for (int i = 1; i < str.length(); i++)
            if (!identifierCharacter((byte) str.charAt(i)))
                estValide = false;

        return estValide;
    }

    /**
     * regarder l'octet suivant
     *
     * @return return l'octet;
     */
    private byte peek() {
        byte character = 0;
        if (this.pos < this.inputSize - 1) {
            character = (byte) src.charAt(this.pos + 1);
        }
        return character;
    }

    /**
     * Verifier si le caractere appartient aux identificateurs
     *
     * @param {@code c} le caractere.
     * @return {@code true} si le caractere est un identificateur
     */
    private boolean identifierCharacter(byte c) {
        return c == '_' || Character.isLetterOrDigit(c);
    }

    /**
     * Ignore les espaces blancs '\n' '\r' ' ' '\t'
     */
    private void ignorerEspace() {
        while (estUnEspaceBlanc(this.current)) {
            if (current == '\n') {
                this.line++;
                this.column = 1;
            }
            advance();
        }
    }

    /**
     * Verifier un caractere est un espace blanc.
     *
     * @param {@code c} le caractere.
     * @return {@code true} si le caractere est un espace blanc {@code false} sinon.
     */
    private boolean estUnEspaceBlanc(byte c) {
        return Character.isSpaceChar(c);
    }

    public void advance() {
        pos++;
        column++;
        if (pos < src.length())
            current = (byte) src.charAt(pos);
        else
            current = 0;//EOF
    }

    /**
     * verifie si y'a un element suivant.
     *
     * @return {@code true} si il est present {@code false} sinon.
     */
    public boolean hasNext() {
        return this.pos < this.inputSize;
    }

    /**
     * retourne une chaine de caractere
     * @return la chaine construite
     */
    @Override
    public String toString() {
        return "Lexer{" +
                "src='" + src + '\'' +
                ", current=" + current +
                ", pos=" + pos +
                '}';
    }
}
