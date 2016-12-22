package modele;

import java.util.ArrayList;

public class Congestion {
	private ArrayList<Arete> aretes= new ArrayList<Arete>();
	private ArrayList<Noeud> noeuds= new ArrayList<Noeud>();
	
	public Congestion() {
		super();
		aretes = new ArrayList<Arete>();
		noeuds = new ArrayList<Noeud>();
		noeuds.add(new Noeud(0));
		noeuds.add(new Noeud(1));
	}
	
	public ArrayList<Arete> getAretes() {
		return aretes;
	}

	public void setAretes(ArrayList<Arete> aretes) {
		this.aretes = aretes;
	}

	public ArrayList<Noeud> getNoeuds() {
		return noeuds;
	}

	public void setNoeuds(ArrayList<Noeud> noeuds) {
		this.noeuds = noeuds;
	}

	public boolean AjoutArete(Arete a){
		boolean test=false;
		Noeud noeu=a.getNd1();
		for (int i=0; i<noeuds.size(); i++) {
			if (noeuds.get(i).getId()==noeu.getId()){ //si le noeud existe
				ArrayList<Arete> rout = noeuds.get(i).getRoutes();
				rout.add(a); //on ajoute une route parmi les routes sortantes du noeud
				noeuds.get(i).setRoutes(rout);
				aretes.add(a);
				test=true;
			}
		}
		return test;
	}
}
