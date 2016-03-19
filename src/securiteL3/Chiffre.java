package securiteL3;

public class Chiffre {

	private Chiffre() {
	}

	private static void firstArg(String first, String decalage, StringBuilder texte) {
		switch (first) {
		case "c":
			Cesar c = new Cesar(texte, decalage);
			System.out.println(c.chiffrer());
			break;
		case "p":
			Permutation p = new Permutation(texte, new StringBuilder(decalage));
			System.out.println(p.chiffrer());
			break;
		case "v":
			Vigenere v = new Vigenere(texte, new StringBuilder(decalage));
			System.out.println(v.chiffrer());
			break;
		default:
		}
	}

	public static void main(String[] arg) {
		if (arg.length < 3) {
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
		firstArg(arg[0], decalage, Util.lowerCase(Util.removeAccentLower(texte)));
	}
}
