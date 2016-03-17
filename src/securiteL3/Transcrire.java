package securiteL3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;

public interface Transcrire {

	public StringBuilder chiffrer();

	public StringBuilder dechiffrer(String decalage, StringBuilder strp);

	public default boolean isWordInListeMots(ArrayList<String> listeMots, String motATrouver) {
		int firstCase = 1;
		int lastCase = listeMots.size();
		int middleCase;
		System.out.println("frumentacé".compareTo("frumentacees"));

		while (lastCase >= firstCase) {
			middleCase = (lastCase + firstCase) / 2;
			System.out.println("middle case : "+listeMots.get(middleCase)+" case : "+middleCase);
			if(lastCase == firstCase) {
				System.out.println(listeMots.get(middleCase));
				return true;
			}
			if (motATrouver.compareTo(listeMots.get(middleCase)) == 0) {
				System.out.println(listeMots.get(middleCase));
				return true;
			}
			else {
				System.out.println("mon mot : "+motATrouver+" middle : "+listeMots.get(middleCase));
				System.out.println("diff : "+motATrouver.compareTo(listeMots.get(middleCase)));
				if (motATrouver.compareTo(listeMots.get(middleCase)) > 0) {
					firstCase = middleCase + 1;
					System.out.println("first case : "+firstCase+" mot : "+listeMots.get(firstCase));
				} else if (motATrouver.compareTo(listeMots.get(middleCase)) < 0) {
					lastCase = middleCase - 1;
					System.out.println("last case : "+lastCase+" mot : "+listeMots.get(lastCase));
				} 
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
				String stringNoAccent = Normalizer.normalize(line, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]",
						"");
				list.add(stringNoAccent);
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
