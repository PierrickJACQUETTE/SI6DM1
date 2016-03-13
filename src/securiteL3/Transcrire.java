package securiteL3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public interface Transcrire {

	public StringBuilder chiffrer();

	public StringBuilder dechiffrer();

	public default boolean isInFile(String nameFile, String mot) {
		File f = new File(nameFile);
		int c;
		FileInputStream ips;
		BufferedReader br = null;
		InputStreamReader ipsr = null;
		try {
			ips = new FileInputStream(f);
			ipsr = new InputStreamReader(ips);
			br = new BufferedReader(ipsr);
			String texte = "";
			while ((c = br.read()) != -1) {
				if (c != '\n') {
					texte += (char) c;
				} else {
					if (texte.equals(mot))
						return true;
					else
						texte = "";
				}
			}
			br.close();
		} catch (IOException exception) {
			exception.getStackTrace();
			System.out.println("Erreur lors de la lecture : " + exception.getMessage());
		}

		return false;
	}

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
