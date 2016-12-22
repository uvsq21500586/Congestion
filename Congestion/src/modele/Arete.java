package modele;

public class Arete {
	private int idr;
	private double fcout[]; //fction du coût
	private int cap; //capacité
	private int priorite[]; //priorité des joueurs
	private int nbjoueurs; //nb de joueurs qui empruntent cette arête
	private Noeud nd1; //origine
	private Noeud nd2; //destination
	
	public Arete(int idr, double[] fcout,int priorite[], int cap, Noeud nd1, Noeud nd2) {
		this.idr = idr;
		this.fcout = fcout;
		this.priorite = priorite;
		this.cap = cap;
		this.nbjoueurs=0;
		this.nd1 = nd1;
		this.nd2 = nd2;
	}

	public int[] getPriorite() {
		return priorite;
	}
	
	public int getPriorite2(int i) {
		return priorite[i];
	}

	public void setPriorite(int[] priorite) {
		this.priorite = priorite;
	}

	public int getIdr() {
		return idr;
	}

	public void setIdr(int idr) {
		this.idr = idr;
	}

	public double[] getFcout() {
		return fcout;
	}

	public double getFcout2(int i) {
		return fcout[i];
	}
	
	public void setFcout(double[] fcout) {
		this.fcout = fcout;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public int getNbjoueurs() {
		return nbjoueurs;
	}

	public void setNbjoueurs(int nbjoueurs) {
		this.nbjoueurs = nbjoueurs;
	}

	public Noeud getNd1() {
		return nd1;
	}

	public void setNd1(Noeud nd1) {
		this.nd1 = nd1;
	}

	public Noeud getNd2() {
		return nd2;
	}

	public void setNd2(Noeud nd2) {
		this.nd2 = nd2;
	}
	
	public void jincrement() {
		this.nbjoueurs++;
	}
	
	public void jdecrement() {
		this.nbjoueurs--;
	}
}
