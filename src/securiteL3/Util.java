package securiteL3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Util {
	private static ArrayList<String> listeMots;

	public static ArrayList<String> getListeMots() {
		return listeMots;
	}

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

	public static boolean isWordInListeMots(ArrayList<String> listeMots, String motATrouver) {
		int firstCase = 0;
		int lastCase = listeMots.size() - 1;
		int middleCase;

		while (lastCase >= firstCase) {
			middleCase = (lastCase + firstCase) / 2;
			if (motATrouver.compareTo(listeMots.get(middleCase)) == 0) {
				return true;
			} else {
				if (motATrouver.compareTo(listeMots.get(middleCase)) > 0) {
					firstCase = middleCase + 1;
				} else if (motATrouver.compareTo(listeMots.get(middleCase)) < 0) {
					lastCase = middleCase - 1;
				}
			}
		}

		return false;
	}

	public static ArrayList<String> remplirListe(String filename) {
		BufferedReader reader = null;
		Set<String> set = null;
		ArrayList<String> list = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			set = new LinkedHashSet<String>();
			list = new ArrayList<String>();
			String line;
			while ((line = reader.readLine()) != null)
				list.add(Normalizer.normalize(line, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", ""));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		set.addAll(list);
		list = new ArrayList<String>(set);
		Collections.sort(list);

		return list;
	}

	public static void chargerLexique(String filename) {
		listeMots = remplirListe(filename);
	}
}
