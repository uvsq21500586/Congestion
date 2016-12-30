package modele;

public class Joueur{
	private int id;
	private Arete strategie;
	
	
	public Joueur(int id) {
		super();
		this.id = id;
		this.strategie = null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Arete getStrategie() {
		return strategie;
	}
	public void setStrategie(Arete strategie) {
		this.strategie = strategie;
	}
}
