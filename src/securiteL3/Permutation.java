package securiteL3;

public class Permutation implements Transcrire {

	StringBuilder str;
	StringBuilder perm;

	Permutation(StringBuilder texte, StringBuilder permutation) {
		this.str = texte;
		this.perm = permutation;

	}

	private boolean verifArg() {
		if (this.perm.length() < 26) {
			System.out.println("La permutation donnee en argument en trop courte ");
			return false;
		}
		if (this.perm.length() > 26) {
			System.out.println("La permutation donnee en argument en trop longue ");
			return false;
		}
		return true;
	}

	@Override
	public StringBuilder chiffrer() {
		char[] tabl = new char[26];
		for (int i = 0; i < tabl.length; i++) {
			tabl[i] = this.perm.charAt(i);
		}
		for (int i = 0; i < this.str.length(); i++) {
			char tmp = this.str.charAt(i);
			if (tmp >= 'a' && tmp <= 'z') {
				this.str.setCharAt(i, tabl[tmp - 'a']);
			}
		}
		return this.str;
	}

	@Override
	public StringBuilder dechiffrer(String decalage, StringBuilder strp) {
		char[] tabl = new char[26];
		for (int i = 0; i < tabl.length; i++) {
			tabl[this.perm.charAt(i) - 'a'] = (char) (i + 'a');
		}
		for (int i = 0; i < this.str.length(); i++) {
			char tmp = this.str.charAt(i);
			if (tmp >= 'a' && tmp <= 'z') {
				this.str.setCharAt(i, tabl[tmp - 'a']);
			}
		}
		return this.str;
	}

	/*public static void main(String[] args) {
		StringBuilder perm = new StringBuilder("Durant la guerre des Gaules, Cesar eut l’idee de communiquer des messages secrets a ses allies. Sa maniere de coder les messages fait sourire aujourd’hui mais avec les Gaulois, il eut du succes… Voici sa technique : Le message a coder est en majuscules, par exemple « LE CIEL TOMBE ! », il peut comporter des espaces et des ponctuations. On ne code que les lettres (minuscules et majuscules), les autres caracteres restent inchanges. Cesar commence par choisir un nombre de l’intervalle [1,25], par exemple 3. Ce nombre sera le decalage. Il change chaque lettre du message par la lettre situee 3 crans plus loin dans l’alphabet. Par exemple, A devient D, et E devient H. Il obtient alors le message code « OH FLHO WRPEH ! ».");
		StringBuilder deca = new StringBuilder("azertyuiopqsdfghjklmwxcvbn");
		Permutation a = new Permutation(perm, deca);
		if (a.verifArg()) {
			System.out.println(a.chiffrer());
			System.out.println(a.dechiffrer("", perm));
		}
	}
	*/

}
