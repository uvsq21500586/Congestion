package modele;

public class Joueur2{
	private int id;
	private Arete2 strategie;
	
	
	public Joueur2(int id) {
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
	
	public Arete2 getStrategie() {
		return strategie;
	}
	public void setStrategie(Arete2 strategie) {
		this.strategie = strategie;
	}
}
