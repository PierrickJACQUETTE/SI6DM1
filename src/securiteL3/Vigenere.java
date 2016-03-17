package securiteL3;

public class Vigenere implements Transcrire {

	private StringBuilder str;
	private StringBuilder motCle;

	public Vigenere(StringBuilder str, StringBuilder motCle) {
		this.str = str;
		this.motCle = motCle;
	}

	private int mostFreq(StringBuilder ciphertext) {
		int[] nbLettre = new int[26];
		int maxCount = 0;
		char indexOfHighestCount = 0;
		for (int i = 0; i < 26; i++) {
			nbLettre[i] = 0;
		}
		for (int i = 0; i < ciphertext.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if (ciphertext.charAt(i) == (char) (j + 'a')) {
					nbLettre[j] += 1;
					break;
				}
			}
		}
		for (char i = 0; i < 26; i++) {
			if (nbLettre[i] > maxCount) {
				indexOfHighestCount = i;
				maxCount = nbLettre[i];
			}
		}
		return indexOfHighestCount - 4; // c'est e ? non faudrait chek dicto etc
										// etc
	}

	public StringBuilder decrypter(int longeur) {
		StringBuilder cle = new StringBuilder();
		for (int i = 0; i < longeur; i++) {
			StringBuilder tmp = new StringBuilder();
			int current = i;
			while (current < this.str.length()) {
				tmp.append(this.str.charAt(current));
				current += longeur;
			}
			int lettreMostFreq = mostFreq(tmp);
			char res = (char) ((lettreMostFreq + 26) % 26);
			cle.append((char) ('a' + res));
		}
		return this.dechiffrer("", new StringBuilder());
	}

	@Override
	public StringBuilder chiffrer() {
		StringBuilder resultat = new StringBuilder();
		char lettre = 0;
		char lettreCle = 0;
		for (int i = 0; i < this.str.length(); i++) {
			for (int j = 0; j < this.motCle.length(); j++) {
				if (i < this.str.length()) {
					lettre = this.str.charAt(i);
					lettreCle = (char) (this.motCle.charAt(j) - 'a');
					if (lettre >= 'a' && lettre <= 'z') {
						if (lettre + lettreCle > 'z') {
							resultat.append((char) (lettre + lettreCle - 26));
						} else {
							resultat.append((char) (lettre + lettreCle));
						}
					} else {
						resultat.append(lettre);
					}
					if (j < this.motCle.length() - 1) {
						i++;
					}
				}
			}
		}
		return resultat;
	}

	@Override
	public StringBuilder dechiffrer(String decalage, StringBuilder strp) {
		StringBuilder resultat = new StringBuilder();
		char lettre = 0;
		char lettreCle = 0;
		for (int i = 0; i < this.str.length(); i++) {
			for (int j = 0; j < this.motCle.length(); j++) {
				if (i < this.str.length()) {
					lettre = this.str.charAt(i);
					lettreCle = (char) (this.motCle.charAt(j) - 'a');
					if (lettre >= 'a' && lettre <= 'z') {
						if (lettre - lettreCle < 'a') {
							resultat.append((char) (lettre - lettreCle + 26));
						} else {
							resultat.append((char) (lettre - lettreCle));
						}
					} else {
						resultat.append(lettre);
					}
					if (j < this.motCle.length() - 1) {
						i++;
					}
				}
			}
		}
		return resultat;
	}
}
