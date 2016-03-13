package securiteL3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;

public interface Transcrire {

	public StringBuilder chiffrer();

	public StringBuilder dechiffrer();

	public default boolean isWordInListeMots(ArrayList<String> listeMots, String motATrouver) {
		int firstCase = 0;
		int lastCase = listeMots.size() - 1;
		boolean found = false;
		int middleCase = 0;

		while (found == false && lastCase >= firstCase) {
			middleCase = (lastCase + firstCase) / 2;
			System.out.println("FIRST : " + firstCase);
			System.out.println("LAST : " + lastCase);
			System.out.println("middle : " + middleCase);
			if (listeMots.get(middleCase).equals(motATrouver))
				return true;
			else {
				System.out.println("middle case : " + listeMots.get(middleCase));
				System.out.println("mot a trouver : " + motATrouver);
				System.out.println("diff : " + listeMots.get(middleCase).compareTo(motATrouver));
				if (listeMots.get(middleCase).compareTo(motATrouver) > 0)
					lastCase = middleCase - 1;
				else if (listeMots.get(middleCase).compareTo(motATrouver) < 0)
					firstCase = middleCase + 1;
			}
		}

		return false;
	}

	public default ArrayList<String> remplirListe(String filename) {
		BufferedReader reader = null;
		ArrayList<String> list = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			list = new ArrayList<String>();
			String line;
			while ((line = reader.readLine()) != null) {
				//System.out.println("line avec accent :" +line);
				String stringNoAccent = Normalizer.normalize(line, Normalizer.Form.NFD)
						.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
				list.add(stringNoAccent);
				//System.out.println("ligne sans accent : " + stringNoAccent);
			}
			list.trimToSize();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/*
	 * public default boolean isInFile(String nameFile, String mot) { File f =
	 * new File(nameFile); int c; FileInputStream ips; BufferedReader br = null;
	 * InputStreamReader ipsr = null; try { ips = new FileInputStream(f); ipsr =
	 * new InputStreamReader(ips); br = new BufferedReader(ipsr); String texte =
	 * ""; while ((c = br.read()) != -1) { if (c != '\n') { texte += (char) c; }
	 * else { if (texte.equals(mot)) return true; else texte = ""; } }
	 * br.close(); } catch (IOException exception) { exception.getStackTrace();
	 * System.out.println("Erreur lors de la lecture : " +
	 * exception.getMessage()); }
	 * 
	 * return false; }
	 */

	public default String randomStr(String s, String mot) {
		String[] tab = s.split(" ");
		if (tab.length == 1 && tab[0].equals(mot))
			return null;
		else if (!tab[0].equals(mot))
			return tab[0];
		else {
			for (int i = 1; i < tab.length; i++) {
				if (!tab[i].equals(mot))
					return tab[i];
			}

			return null;
		}
	}
}
