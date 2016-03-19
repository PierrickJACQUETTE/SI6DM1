package securiteL3;

public class Dechiffre {

	private Dechiffre() {}
	
	private static void firstArg(String first, StringBuilder texte, String decalage) {
		switch (first) {
		case "c":
			Cesar c = new Cesar(texte);
			System.out.println(c.dechiffrer(decalage, texte));
			break;
		case "p":
			Permutation p = new Permutation(texte, new StringBuilder(decalage));
			System.out.println(p.dechiffrer("ferfref", new StringBuilder("zef")));
			break;
		case "v":
			Vigenere v = new Vigenere(texte, new StringBuilder(decalage));
			System.out.println(v.dechiffrer("ferfref", new StringBuilder("zef")));
			break;
		default:

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
		if (arg.length == 4) {
			String mot = arg[3];
			firstArg(arg[0], Util.lowerCase(texte), decalage);
		} else 
			firstArg(arg[0], Util.lowerCase(Util.removeAccentLower(texte)), decalage);
	}
}
