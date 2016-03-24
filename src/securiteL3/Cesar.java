package securiteL3;

import java.util.HashMap;

public class Cesar implements Transcrire {
	private StringBuilder str;
	private int decalage;
	private int methode;
	private String motTest;

	public Cesar(StringBuilder str, String decalage) {
		this.str = str;
		this.decalage = Integer.parseInt(decalage) % 26;
	}

	public Cesar(StringBuilder str) {
		this.str = str;
	}

	public void setMotTest(String mot) {
		this.motTest = mot;
	}

	public void setMethode(int methode) {
		this.methode = methode;
	}

	private static String decale(String str, int decalage) {
		String retour = "";
		char newChar;
		for (int i = 0; i < str.length(); i++) {
			newChar = (char) (str.charAt(i));
			if (newChar >= 'a' && newChar <= 'z') {
				newChar = (char) (newChar + decalage);
				if (newChar > 'z') {
					newChar -= 26;
					retour += newChar;
				} else
					retour += (char) newChar;
			} else
				retour += newChar;
		}

		return retour;
	}

	public StringBuilder decryptageMot() {
		String test;
		boolean correct = false;
		String deca = "";
		StringBuilder strTemp = new StringBuilder(str);
		// boucle for qui teste les codages du mot cle
		for (int i = 0; i <= 25; i++) {
			test = decale(motTest, i); // decale le motTest au decalage i
			int j = 0, k = 0, c = 0;
			// boucle while qui regarde si le mot cle est present dans le texte
			// s'arrete si trouve == true ou si on a atteint la fin du texte
			while (j < strTemp.length()) {
				// regarde si le caractere courant est dans l'alphabet
				if (strTemp.charAt(j) > 'a' && strTemp.charAt(j) < 'z') {
					c++;
					// teste si le char courant est = au char correcpondant au
					// mot cle
					// et regarde si le mot c n'est pas plus grand que le mot
					// cle
					if (strTemp.charAt(j) == test.charAt(k) && !(c > test.length())) {
						k++;
						// teste si le mot en traitement est aussi long que le
						// mot cle
						if (k == test.length() && k == c) {
							deca += i; // current decalage de teste
							strTemp = dechiffrer(deca, strTemp); // dechiffre le
																	// texte
							correct = mySplit(strTemp); // teste si le teste est
														// en francais
							if (correct)
								return strTemp; // retourne le texte correct
							else {
								// remete le texte de test au texte crypter
								// et le decalage de teste a ""
								strTemp = str;
								deca = "";
								break;
							}
						}
					}
				} else {
					// si on catch un separateur remets la taille du mot courant
					// a 0;
					c = 0;
					k = 0;
				}
				j++;
			}
		}

		return null;
	}

	public StringBuilder decryptageFrequence() {
		System.out.println(str);
		int maxFreq = 0;
		int freqChar = 0;
		Character maxChar = new Character('a');
		HashMap<Character, Integer> freqTable = new HashMap<Character, Integer>();
		for (Character i = 97; i <= 122; i++) {
			freqTable.put(i, 0); // initialise la hastable a une valeur 0 pour
									// chaque cle
		}

		// boucle for qui regarde quel est le caractere le plus present dans le
		// texte
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

		/*
		 * boucle for qui permet de tester pour le caractere le plus frequent
		 * son codage par rapport au tableau des frequences
		 */
		for (int i = 0; i < 26; i++) {
			int tmp = maxChar - Constants.TAB_FREQ[i]; // decalage par rapport a
														// tabFreq
			// (peut etre negatif)
			// decalage final corrige si il est negatif
			int decalage = ((tmp + 97) < 'a') ? (tmp + 26) : tmp;
			// dechiffre le texte par rapport a decalage
			StringBuilder strTemp = new StringBuilder(dechiffrer(String.valueOf(decalage), str));
			// teste pour savoir si le texte dechiffre est correct (strTemp)
			if (mySplit(strTemp))
				// si c'est bon renvoie le texte dechiffre (strTemp)
				return strTemp;
		}

		return null;
	}

	/*
	 * Teste comme un gros bourrin tout les decalages possible
	 */
	public StringBuilder decryptageForceBrute() {
		int calculDecalage = 0;
		String firstWorld = firstWord(str);
		while (calculDecalage < 26) {
			String s1 = decale(firstWorld, calculDecalage);
			if (Util.isWordInListeMots(Util.getListeMots(), s1)) {
				StringBuilder s2 = dechiffrer(String.valueOf(26 - calculDecalage), new StringBuilder(str.toString()));
				if (mySplit(s2))
					return s2;
			}
			calculDecalage++;
		}

		return null;
	}
	
	private static String firstWord(StringBuilder s) {
		int i = 0;
		String tmp = "";
		while (i < s.length()) {
			if (s.charAt(i) != ' ')
				tmp += s.charAt(i);
			else
				return tmp;

			i++;
		}

		return tmp;
	}

	/*
	 * separe les mots par rapport au caractere speciaux verifie si ils sont
	 * dans le lexique
	 */
	private boolean mySplit(StringBuilder s) {
		String tmp = "";
		for (int i = 0; i < s.length(); i++) {
			// teste si le char lu est dans l'alphabet et l'ajoute
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				tmp += s.charAt(i);
			}
			// teste si le char est un apostrophe et reset le string
			else if (s.charAt(i) == 39) {
				tmp = "";
			}
			// teste si le char est un caractere special et essaye de trouver
			else if (((s.charAt(i) >= ' ' && s.charAt(i) <= '.') || (s.charAt(i) >= ':' && s.charAt(i) <= '?'))
					& !tmp.equals("") & s.charAt(i) != 39) {
				if (!Util.isWordInListeMots(Util.getListeMots(), tmp)) {
					return false;
				}
				tmp = "";
			}
		}
		return true;
	}

	public StringBuilder decrypter() {
		switch (this.methode) {
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
	public StringBuilder dechiffrer(String currentDecalage, StringBuilder strTemp) {
		int decalage = 26 - Integer.parseInt(currentDecalage);
		for (int i = 0; i < strTemp.length(); i++) {
			int c = strTemp.charAt(i);
			if (c >= 'a' && c <= 'z') {
				c += decalage;
				if (c > 'z')
					c = c - 26;
				else if (c < 'a')
					c = c + decalage;

				strTemp.setCharAt(i, (char) c);
			} else
				strTemp.setCharAt(i, (char) c);
		}

		return strTemp;
	}
}
