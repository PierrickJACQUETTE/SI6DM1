package securiteL3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;

public class Dechiffre {

	public static StringBuilder lire(String nom) {
		File f = new File(nom);
		FileInputStream ips;
		BufferedReader br = null;
		InputStreamReader ipsr = null;
		StringBuilder texte = new StringBuilder();
		try {
			ips = new FileInputStream(f);
			ipsr = new InputStreamReader(ips);
			br = new BufferedReader(ipsr);
			int c;
			while ((c = br.read()) != -1) {
				if (c == '\n')
					texte.append('\n');
				else
					texte.append((char) c);
			}
		} catch (IOException exception) {
			exception.getStackTrace();
			System.out.println("Erreur lors de la lecture : " + exception.getMessage());
		}
		return texte;
	}

	private static void firstArg(String first, StringBuilder texte, String decalage) {
		switch (first) {
		case "c":
			Cesar c = new Cesar(texte);
			System.out.println(c.dechiffrer(decalage,texte));
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
	
	public static StringBuilder removeAccentLower(StringBuilder source) {
		return new StringBuilder(Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "")
				.toLowerCase());
	}
	
	public static StringBuilder lowerCase(StringBuilder pText) {
		for (int i = 0; i < pText.length(); i++) {
			int c = (int) pText.charAt(i);
			if (c >= 65 && c <= 65 + 27) {
				pText.setCharAt(i, (char) (c | 32));
			}
		}
		return pText;
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
		StringBuilder texte = lire(arg[2]);
		if (texte == null) {
			System.err.println("Le fichier est vide");
			return;
		}
		if (arg.length == 4) {
			String mot = arg[3];
			firstArg(arg[0], lowerCase(texte), decalage);
		} else {
			firstArg(arg[0], lowerCase(removeAccentLower(texte)), decalage);
		}
	}
}
