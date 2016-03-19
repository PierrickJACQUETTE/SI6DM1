package securiteL3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public interface Transcrire {

	public StringBuilder chiffrer();

	public StringBuilder dechiffrer(String decalage, StringBuilder strp);

	public default boolean isWordInListeMots(ArrayList<String> listeMots, String motATrouver) {
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

	public default ArrayList<String> remplirListe(String filename) {
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
}
