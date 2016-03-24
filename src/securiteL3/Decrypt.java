package securiteL3;

public class Decrypt {

	private Decrypt() {
	}

	private static void firstArg(String first, String mot, StringBuilder texte, String methode) {
		long startTime;
		long endTime;
		switch (first) {
		case "c":
			Cesar c = new Cesar(texte);
			c.setMethode(Integer.parseInt(methode));
			if (mot != null)
				c.setMotTest(mot);
			startTime = System.currentTimeMillis();
			System.out.println(c.decrypter());
			endTime = System.currentTimeMillis();
			System.err.println("Temps de decryptage de cesar : " + (endTime - startTime) + " ms.");
			break;
		case "p":
			Permutation p = new Permutation(texte, new StringBuilder());
			startTime = System.currentTimeMillis();
			System.out.println(p.decrypt());
			endTime = System.currentTimeMillis();
			System.err.println("Temps de decryptage de permutation : " + (endTime - startTime) + " ms.");
			break;
		case "v":
			if (methode.equals("1") && mot != null) {
				Vigenere v = new Vigenere(texte, new StringBuilder(mot));
				startTime = System.currentTimeMillis();
				System.out.println(v.decrypter(Integer.parseInt(mot)));
				endTime = System.currentTimeMillis();
				System.err.println(
						"Temps de decryptage de vigenere AVEC la longeur de cle: " + (endTime - startTime) + " ms.");
			}
			else if (methode.equals("2")) {
				StringBuilder texte2 = Util.suppAZ(texte);
				Vigenere2 v2 = new Vigenere2(texte);
				startTime = System.currentTimeMillis();
				System.out.println((v2.trouveLengthCle(texte2, 11).cle));
				endTime = System.currentTimeMillis();
				System.err.println("Temps de decryptage de vigenere SANS la longueur de la cle: "
						+ (endTime - startTime) + " ms.");
			} else {
				System.out.println("Methode non reconnue");
			}
			break;
		default:
			System.err.println("Systeme de decryptage inconnu");
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
		String method = null;
		if (arg.length >= 3)
			method = arg[2];
		StringBuilder texte = Util.lire(arg[1]);
		if (texte == null) {
			System.err.println("Le fichier est vide");
			return;
		}

		Util.chargerLexique(Constants.LEXIQUE);
		if (arg.length == 4) {
			String mot = arg[3];
			firstArg(arg[0], mot, Util.lowerCase(texte), method);
		} else
			firstArg(arg[0], null, Util.lowerCase(Util.removeAccentLower(texte)), method);
	}
}
