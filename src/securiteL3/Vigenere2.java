package securiteL3;

public class Vigenere2 extends Vigenere implements Transcrire {

	public Vigenere2(StringBuilder str) {
		super(str, null);
	}

	/**
	 * Retourne le nombre d'occurences de chaque lettre dans le texte
	 * 
	 * @param str
	 * @return
	 */
	private static int[] occurenceLettres(StringBuilder str) {
		int[] tabOccurence = new int[26];
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c <= 'z' && c >= 'a') {
				tabOccurence[c - 'a']++;
			}
		}

		return tabOccurence;
	}

	private static double indiceCoincidence(StringBuilder str) {
		int[] tab = occurenceLettres(str);
		double somme = 0, nbTotalLettres = 0;
		for (int i = 0; i < tab.length; i++) {
			somme += (tab[i] * (tab[i] - 1));
			nbTotalLettres += tab[i];
		}

		return somme / (nbTotalLettres * (nbTotalLettres - 1));
	}

	private static double[] listeIndices(StringBuilder str, int n) {
		double[] tabTmpIndice = new double[n];
		double[] tabFinalIndice = new double[n];
		StringBuilder strTmp = new StringBuilder();
		char c;
		int cpt = 0, j = 0;
		double somme = 0.0;
		for (int i = 0; i < n; i++) {
			while (cpt < i + 1) {
				while (j < str.length()) {
					c = str.charAt(j);
					if (c <= 'z' && c >= 'a')
						strTmp.append(c);

					j += (i + 1);
				}
				cpt++;
				j = cpt;
				tabTmpIndice[j - 1] = indiceCoincidence(strTmp);
				strTmp.setLength(0);
			}
			j = 0;
			cpt = 0;

			for (int k = 0; k <= i; k++)
				somme += tabTmpIndice[k];

			tabFinalIndice[i] = somme / (i + 1);
			somme = 0;
		}

		return tabFinalIndice;
	}

	public CoupleCleIndice trouveLengthCle(StringBuilder str, int n) {
		double tab[] = listeIndices(str, n);
	/*	for (int i = 0; i < tab.length; i++) {
			System.out.println("i : " + tab[i]);
		}
	*/	for (int i = 0; i < tab.length; i++) {
			if (Double.compare(tab[i], Constants.SEUIL) > 0)
				return new CoupleCleIndice(i + 1, tab[i]);
		}
		return new CoupleCleIndice(0, 0);
	}

	@Override
	public StringBuilder chiffrer() {
		return null;
	}

	@Override
	public StringBuilder dechiffrer(String decalage, StringBuilder strp) {
		return null;
	}

	static class CoupleCleIndice {
		protected int cle;
		private double indice;

		public CoupleCleIndice(int cle, double indice) {
			this.cle = cle;
			this.indice = indice;
		}
	}
}
