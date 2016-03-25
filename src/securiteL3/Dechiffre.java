package securiteL3;

public class Dechiffre {

	private Dechiffre() {
	}

	private static void firstArg(String first, StringBuilder texte, String decalage) {
		long startTime;
		long endTime;
		switch (first) {
		case "c":
			Cesar c = new Cesar(texte);
			startTime = System.currentTimeMillis();
			System.out.print(c.dechiffrer(decalage, texte));
			endTime = System.currentTimeMillis();
			System.err.println("Temps de dechiffrage de cesar : " + (endTime - startTime) + " ms.");
			break;
		case "p":
			Permutation p = new Permutation(texte, new StringBuilder(decalage));
			startTime = System.currentTimeMillis();
			System.out.print(p.dechiffrer("ferfref", new StringBuilder("zef")));
			endTime = System.currentTimeMillis();
			System.err.println("Temps de dechiffrage de permutation : " + (endTime - startTime) + " ms.");
			break;
		case "v":
			Vigenere v = new Vigenere(texte, new StringBuilder(decalage));
			startTime = System.currentTimeMillis();
			System.out.print(v.dechiffrer("ferfref", new StringBuilder("zef")));
			endTime = System.currentTimeMillis();
			System.err.println("Temps de dechiffrage de vigenere : " + (endTime - startTime) + " ms.");
			break;
		default:
			System.err.println("Systeme de dechiffrage inconnu");
			return;
		}
	}

	public static void main(String[] arg) {
		if (arg.length < 2) {
			System.err.println(arg.length);
			System.err.println("Il manque des arguments");
			return;
		}
		if (!arg[0].equals("p") && !arg[0].equals("c") && !arg[0].equals("v")) {
			System.err.println(arg[0] + "Premier argument non reconnu");
			return;
		}
		String decalage = arg[1];
		StringBuilder texte = Util.lire(arg[2]);
		if (texte == null) {
			System.err.println("Le fichier est vide");
			return;
		}

		Util.chargerLexique(Constants.LEXIQUE);
		if (arg.length == 4) {
			firstArg(arg[0], Util.lowerCase(texte), decalage);
		} else
			firstArg(arg[0], Util.lowerCase(Util.removeAccentLower(texte)), decalage);
	}
}
