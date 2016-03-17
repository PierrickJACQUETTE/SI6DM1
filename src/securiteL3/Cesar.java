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
		// for(String mot : listeMots)
		// System.out.println(mot);
		
		  if(isWordInListeMots(this.listeMots, "beau")) {
		  System.out.println("mot present"); }
		  else System.out.println("tg");
	}

	public Cesar(StringBuilder str) {
		this.str = str;
		this.listeMots = remplirListe("lexique.txt");
		 if(isWordInListeMots(this.listeMots, "fait")) 
			 System.out.println("le mot est present");
		 else System.out.println("tg");
		
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
			newChar = (char) (m.charAt(i));
			if (newChar >= 97 && newChar <= 122) {
				newChar =(char)(newChar+decale);
				if (newChar > 122) {
					newChar -= 26;
					retour += newChar;
				}
				else{
					retour += (char) newChar;
				}
			}
			else{
				retour += newChar;
			}
		}

		return retour;
	}

	@Override
	public StringBuilder dechiffrer(String currentDeca, StringBuilder strTemp) {

		int deca = 26 - Integer.parseInt(currentDeca);
		for (int i = 0; i < strTemp.length(); i++) {
			int c = strTemp.charAt(i);
			if (c >= 'a' && c <= 'z') {
				c += deca;
				if (c > 'z') {
					c = c - 26;
				} else if (c < 'a') {
					c = c + deca;
				}
				strTemp.setCharAt(i, (char) c);
			} else {
				strTemp.setCharAt(i, (char) c);
			}
		}
		return strTemp;
	}

	public StringBuilder decryptageMot() {
		String test;
		boolean correct = false;
		boolean trouve = false;
		String deca = "";
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
						deca += i;
					}
				}
				j++;
			}
		}
		if (trouve = true) {
			System.out.println("tente dechiffre");
			strTemp = dechiffrer(deca, strTemp);
			System.out.println(strTemp);
			String s = randomStr(strTemp.toString(), motTest);
			if (s != null) {
				System.out.println("string random : " + s);
				// correct = isInFile("lexique.txt", s);
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
		System.out.println(str);
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
		int tmp = maxChar - tabFreq[0];
		int decalage = (tmp < 'a') ? tmp : tmp + 26;

		str = dechiffrer(String.valueOf(decalage), str);
		return str;
	}

	public String firstWorld(StringBuilder s) {
		int i = 0;
		String tmp = "";
		while (i < s.length()) {
			if (s.charAt(i) != ' ') {
				tmp += s.charAt(i);
			} else {
				return tmp;
			}
			i++;
		}
		return tmp;
	}

	public StringBuilder decryptageForceBrute() {
		int calculDecalage = 0;
		String firstWorld = firstWorld(str);
		while (calculDecalage < 26) {
			String sss = decale(firstWorld, calculDecalage);
			if (isWordInListeMots(listeMots, sss)) {
				StringBuilder s = dechiffrer(String.valueOf(26-calculDecalage), new StringBuilder(str.toString()));
				if (mysplit(s)) {
					
					return s;
				}

			}
			calculDecalage++;

		}
		return null;
	}

	private boolean mysplit(StringBuilder s) {
		String tmp = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				tmp += s.charAt(i);
			}
			else if (s.charAt(i) == ' ') {
				
				if (!isWordInListeMots(listeMots,tmp)) {
					return false;
				}
				tmp = "";
			}
		}
		return true;
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

	public StringBuilder decrypter() {
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
