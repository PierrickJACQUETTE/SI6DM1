package securiteL3;

public class Permutation implements Transcrire {
	private StringBuilder str;
	private StringBuilder perm;

	public Permutation(StringBuilder texte, StringBuilder permutation) {
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

	private static int[] countLetter(StringBuilder ciphertext) {
		int[] nbLettre = new int[26];
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
		
		return nbLettre;
	}

	public StringBuilder decrypt() {
		int[] nb = countLetter(this.str);
		Character maxChar = new Character('a');
		char[] res = new char[26];
		for (int i = 0; i < res.length; i++) 
			res[i] = 0;
		
		int max = 0;
		int compteur = 0;
		for (int j = 0; j < res.length; j++) {
			for (int i = 0; i < nb.length; i++) {
				if (nb[i] > max) {
					max = nb[i];
					compteur = i;
				}
			}
			res[Constants.TAB_FREQ[j] - 97] = (char) (compteur + 'a');
			nb[compteur] = 0;
			max = 0;
		}
		
		StringBuilder resultat = new StringBuilder();
		for (int i = 0; i < res.length; i++) 
			resultat.append(res[i]);
		
		this.perm = resultat;
		
		return this.dechiffrer("", resultat);
	}

	@Override
	public StringBuilder chiffrer() {
		char[] tabl = new char[26];
		tabl = perm.toString().toCharArray();
		for (int i = 0; i < this.str.length(); i++) {
			char tmp = this.str.charAt(i);
			if (tmp >= 'a' && tmp <= 'z') 
				this.str.setCharAt(i, tabl[tmp - 'a']);
		}
		
		return this.str;
	}

	@Override
	public StringBuilder dechiffrer(String decalage, StringBuilder strp) {
		char[] tabl = new char[26];
		for (int i = 0; i < tabl.length; i++) 
			tabl[this.perm.charAt(i) - 'a'] = (char) (i + 'a');
		
		for (int i = 0; i < this.str.length(); i++) {
			char tmp = this.str.charAt(i);
			if (tmp >= 'a' && tmp <= 'z') 
				this.str.setCharAt(i, tabl[tmp - 'a']);
		}
		
		return this.str;
	}
}
