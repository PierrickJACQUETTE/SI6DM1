package securiteL3;

public class Vigenere implements Transcrire {

	StringBuilder mot;
	StringBuilder motCle;
	
	public Vigenere(StringBuilder mot, StringBuilder motCle) {
		this.mot = mot;
		this.motCle = motCle;
	}
	
	@Override
	public StringBuilder chiffrer() {
		StringBuilder resultat = new StringBuilder();
		char lettre = 0;
		char lettreCle = 0;
		for (int i = 0; i < this.mot.length(); i++) {
			for (int j = 0; j < this.motCle.length(); j++) {
				if (i < this.mot.length()) {
					lettre = this.mot.charAt(i);
					lettreCle = this.motCle.charAt(j);
					if (lettre + lettreCle > 'a' + 'z') {
						resultat.append((char)(lettre + lettreCle - 'z'));
					} else if (lettre + lettreCle < 'a') {
						resultat.append((char)(lettre + lettreCle + 'a'));
					} else {
						resultat.append((char)(lettre + lettreCle - 'a'));
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
		for (int i = 0; i < this.mot.length(); i++) {
			for (int j = 0; j < this.motCle.length(); j++) {
				if (i < this.mot.length()) {

					lettre = this.mot.charAt(i);

					lettreCle = this.motCle.charAt(j);

					if (lettre - lettreCle <= 0) {
						resultat.append((char) (lettre - lettreCle + 'z'));
					} else if (lettre - lettreCle < 'a') {
						resultat.append((char)(lettre - lettreCle + 'a'));
					} else {
						resultat.append((char)(lettre + lettreCle - 'a'));
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
