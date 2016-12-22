package modele;

import java.util.ArrayList;

public class Strategie {
	private ArrayList<Arete> st = new ArrayList<Arete>();

	public Strategie() {
		super();
		this.st = new ArrayList<Arete>();
	}

	public ArrayList<Arete> getSt() {
		return st;
	}
	
	public void ajoutarete(Arete a) {
		this.st.add(a);
	}
	
	public void setSt(ArrayList<Arete> st) {
		this.st = st;
	}
	
	
	
}
