package securiteL3;

public class decrypt {
	
	private decrypt() {}
	
	private static void firstArg(String first, String mot, StringBuilder texte, String methode) {
		switch (first) {
		case "c":
			Cesar c = new Cesar(texte);
			c.setMethode(Integer.parseInt(methode));
			if (mot != null)
				c.setMotTest(mot);

			System.out.println(c.decrypter());
			break;
		case "p":
			Permutation p = new Permutation(texte, new StringBuilder());
			System.out.println(p.decrypt());
			break;
		case "v":
			Vigenere v = new Vigenere(texte, new StringBuilder(methode));
			System.out.println(v.decrypter(Integer.parseInt(methode)));
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
		String method = null;
		if (arg.length == 3) {
			method = arg[2];
		}
		StringBuilder texte = Util.lire(arg[1]);
		if (texte == null) {
			System.err.println("Le fichier est vide");
			return;
		}
		if (arg.length == 4) {
			String mot = arg[3];
			firstArg(arg[0], mot, Util.lowerCase(texte), method);
		} else {
			firstArg(arg[0], null, Util.lowerCase(Util.removeAccentLower(texte)), method);
		}
	}
}
