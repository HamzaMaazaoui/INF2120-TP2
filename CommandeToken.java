import java.util.ArrayList;
import java.util.List;

public class CommandeToken {


    public static List<Commande> CommandeToken(List<Token> tokens) {
        List<Commande> commandes = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            if (Token.KEYWORDS.containsKey(tokens.get(i).getTokenType())) {
                String identificateur;
                Pair<String, String> pair;
                switch (tokens.get(i).getTokenType()) {
                    case Token.ABSTRACT:
                        commandes.add(new CommandeAbstrait());
                        break;
                    case Token.CLASSE_DEBUT:
                        identificateur = extraireUnIdentificateur(tokens, i);
                        commandes.add(new CommandeDebutClasse(identificateur));
                        break;
                    case Token.CLASSE_FIN:
                        commandes.add(new CommandeFinClasse());
                        break;
                    case Token.METHODE_DEBUT:
                        pair = extraireDeuxIdentificateurs(tokens, i);
                        commandes.add(new CommandeDebutMethode(pair.getCle(), pair.getValeur()));
                        break;
                    case Token.METHODE_FIN:
                        commandes.add(new CommandeFinMethode());
                        break;
                    case Token.ATTRIBUT:
                        pair = extraireDeuxIdentificateurs(tokens, i);
                        commandes.add(new CommandeAttribut(pair.getCle(), pair.getValeur()));
                        break;
                    case Token.PARAMETRE:
                        pair = extraireDeuxIdentificateurs(tokens, i);
                        commandes.add(new CommandeParametre(pair.getCle(), pair.getValeur()));
                }
            }
        }
        return commandes;
    }

    /**
     * Extraire deux identificateurs
     * @param tokens la liste
     * @return {@code r[0],r[r1]} Retourne les deux identificateurs
     */
    private static Pair<String, String> extraireDeuxIdentificateurs(List<Token> tokens, int i) {
        int cpt = 0;
        int j = i + 1;
        String[] r = new String[2];
        while (cpt < 2) {
            if (tokens.get(j).getTokenType().equals(Token.IDENTIFICATEUR)) {
                r[cpt++] = tokens.get(j).getLiteral();
            }
            ++j;
        }
        return new Pair<>(r[0], r[1]);
    }
    /**
     * Extraire un identificateurs
     * @param tokens la liste
     * @return {@code resultat} Retourne  l'identificateur
     */
    private static String extraireUnIdentificateur(List<Token> tokens, int i) {
        int cnt = 0;
        int j = i + 1;
        String resultat = "";
        while (cnt < 1) {
            if (tokens.get(j).getTokenType().equals(Token.IDENTIFICATEUR)) {
                resultat = tokens.get(j).getLiteral();
                ++cnt;
            }
            ++j;
        }
        return resultat;
    }
}
