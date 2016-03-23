package securiteL3;

public interface Transcrire {

	public StringBuilder chiffrer();

	public StringBuilder dechiffrer(String decalage, StringBuilder strp);
}
