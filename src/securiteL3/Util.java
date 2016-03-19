package securiteL3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;

public class Util {

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
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return texte;
	}

	public static StringBuilder removeAccentLower(StringBuilder source) {
		return new StringBuilder(
				Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "").toLowerCase());
	}

	public static StringBuilder lowerCase(StringBuilder pText) {
		for (int i = 0; i < pText.length(); i++) {
			int c = (int) pText.charAt(i);
			if (c >= 65 && c <= 65 + 27)
				pText.setCharAt(i, (char) (c | 32));
		}

		return pText;
	}
}
