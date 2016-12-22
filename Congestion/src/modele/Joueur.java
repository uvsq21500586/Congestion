package modele;

import java.util.ArrayList;

public class Joueur {
	private int id;
	private Strategie strategie;
	
	
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
	
	public Strategie getStrategie() {
		return strategie;
	}
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}
}
