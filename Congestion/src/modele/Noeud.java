package modele;

import java.util.ArrayList;

public class Noeud {
	private int id;
	private String type;
	private ArrayList<Arete> routes=new ArrayList<Arete>(); //arêtes sortantes
	
	public Noeud(int id) {
		this.id = id;
		if (id==0) {
			this.type="source";
		} else if (id==1) {
			this.type="dest";
		} else {
			this.type="point";
		}
		
		this.routes=new ArrayList<Arete>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Arete> getRoutes() {
		return routes;
	}

	public void setRoutes(ArrayList<Arete> routes) {
		this.routes = routes;
	};
	
	
}
