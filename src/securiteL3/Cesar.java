package securiteL3;

import java.util.ArrayList;
import java.util.HashMap;

public class Cesar implements Transcrire {
	private ArrayList<String> listeMots;
	private StringBuilder str;
	private int decalage;
	private int methode;
	private String motTest;

	public Cesar(StringBuilder str, String decalage) {
		this.str = str;
		this.decalage = Integer.parseInt(decalage) % 26;
		this.listeMots = remplirListe("lexique.txt");
		for(String mot : listeMots) 
		//	System.out.println(mot);
		if(isWordInListeMots(this.listeMots, "marcher")) {
			System.out.println("le mot est present");
		} 
	}

	public Cesar(StringBuilder str) {
		this.str = str;
		this.listeMots = remplirListe("lexique.txt");
		if(isWordInListeMots(this.listeMots, "marcher")) {
			System.out.println("le mot est present");
		}
	}

	public void setMotTest(String i) {
		this.motTest = i;
	}

	public void setMethode(int i) {
		this.methode = i;
	}

	public static String decale(String m, int decale) {
		String retour = "";
		char newChar;
		for (int i = 0; i < m.length(); i++) {
			newChar = (char) (m.charAt(i) + decale);
			if (newChar >= 97 && newChar <= 122) {
				retour += (char) newChar;
			} else if (newChar > 122) {
				newChar -= 26;
				retour += newChar;
			}
		}

		return retour;
	}

	public StringBuilder dechif(int currentDeca, StringBuilder strTemp) {
		int deca = 26 - currentDeca;
		for (int i = 0; i < strTemp.length(); i++) {
			int c = strTemp.charAt(i);
			if (c >= 97 && c <= 122) {
				c += deca;
				if (c > 122) {
					c = c - 26;
				}
				strTemp.setCharAt(i, (char) c);
			}
		}

		return strTemp;
	}

	public StringBuilder decryptageMot() {
		String test;
		boolean correct = false;
		boolean trouve = false;
		int deca = 0;
		StringBuilder strTemp = new StringBuilder(str);
		for (int i = 0; i < 25; i++) {
			test = decale(motTest, i);
			System.out.println(motTest + " = " + test);
			int j = 0, k = 0;
			System.out.println("Je rentre dans la whale");
			while ((trouve != true) && (j < strTemp.length())) {
				if (strTemp.charAt(j) == test.charAt(k)) {
					System.out.println("test : " + k + " " + j);
					k++;
					if (k == test.length()) {
						System.out.println("OK");
						trouve = true;
						deca = i;
					}
				}
				j++;
			}
		}
		if (trouve = true) {
			System.out.println("tente dechiffre");
			strTemp = dechif(deca, strTemp);
			System.out.println(strTemp);
			String s = randomStr(strTemp.toString(), motTest);
			if (s != null) {
				System.out.println("string random : " + s);
				//correct = isInFile("lexique.txt", s);
			}
			if (correct == true) {
				return strTemp;
			} else {
				System.err.println("Votre cle n'est pas bonne");
			}
		}

		return null;
	}

	// a utiliser sur des textes suffisamment long (sujet) et pas un seul mot
	// (analyse
	// frequentielle inutile)
	public StringBuilder decryptageFrequence() {
		int maxFreq = 0;
		int freqChar = 0;
		Character maxChar = new Character('a');
		char[] tabFreq = { 'e', 'a', 's', 'i', 't', 'n', 'r', 'u', 'l', 'o', 'd', 'c', 'p', 'm', 'v', 'q', 'f', 'b',
				'g', 'h', 'j', 'x', 'y', 'z', 'w', 'k' };
		HashMap<Character, Integer> freqTable = new HashMap<Character, Integer>();
		for (Character i = 97; i <= 122; i++) {
			freqTable.put(i, 0);
		}

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
				freqChar = freqTable.get(str.charAt(i));

			if (maxFreq <= freqChar) { // si plusieurs lettre apparaissent un
										// meme nombre maximum de fois ?
				maxFreq = freqChar;
				maxChar = str.charAt(i);
			}
			freqTable.put(str.charAt(i), freqChar + 1);
		}

		int decalage = maxChar - tabFreq[0];
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 'a' && c <= 'z') {
				if ((char) (c - decalage) < 'a')
					str.setCharAt(i, (char) (c + 26 - decalage));
				else
					str.setCharAt(i, (char) (c - decalage));
			}
		}

		return str;
	}

	public StringBuilder decryptageForceBrute() {
		String mot = "";
		int decalage = 1;
		for (int i = 0; i < str.length(); i++) {
			while (str.charAt(i) != ' ') {
				mot += str.charAt(i);
			}
			while (!isWordInListeMots(this.listeMots, mot = decale(mot, decalage)) || (decalage <= 25)) {
				decalage++;
			}
			str.append(mot);
		}

		return str;
	}

	@Override
	public StringBuilder chiffrer() {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 'a' && c <= 'z') {
				c += decalage;
				if (c > 'z')
					c = (char) (c - 26);

				str.setCharAt(i, (char) c);
			}
		}

		return str;
	}

	@Override
	public StringBuilder dechiffrer() {
		switch (methode) {
		case 1:
			return decryptageMot();
		case 2:
			return decryptageFrequence();
		case 3:
			return decryptageForceBrute();
		default:
			System.err.println("Cette methode n'existe pas");
			return null;
		}
	}
}
