package securiteL3;

public class Vigenere implements Transcrire {

	StringBuilder str;
	StringBuilder motCle;

	public Vigenere(StringBuilder str, StringBuilder motCle) {
		this.str = str;
		this.motCle = motCle;
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
		this.str = resultat;
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
		this.str = resultat;
		return resultat;
	}

	public static void main(String[] args) {
		StringBuilder perm = new StringBuilder("Durant la guerre des Gaules, Cesar eut l’idee de communiquer des messages secrets a ses allies. Sa maniere de coder les messages fait sourire aujourd’hui mais avec les Gaulois, il eut du succes… Voici sa technique : Le message a coder est en majuscules, par exemple « LE CIEL TOMBE ! », il peut comporter des espaces et des ponctuations. On ne code que les lettres (minuscules et majuscules), les autres caracteres restent inchanges. Cesar commence par choisir un nombre de l’intervalle [1,25], par exemple 3. Ce nombre sera le decalage. Il change chaque lettre du message par la lettre situee 3 crans plus loin dans l’alphabet. Par exemple, A devient D, et E devient H. Il obtient alors le message code « OH FLHO WRPEH ! ».");
		StringBuilder deca = new StringBuilder("bal");
		Vigenere a = new Vigenere(perm, deca);
		System.out.println(a.chiffrer());
		System.out.println(a.dechiffrer("", perm));

	}

}
