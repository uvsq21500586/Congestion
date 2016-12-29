package modele;

public class Arete {
	private int idr;
	private double fcout[]; //fction du coût
	private int cap; //capacité
	private int priorite[]; //priorité des joueurs
	private int nbjoueurs; //nb de joueurs qui empruntent cette arête
	private int surcharge; //nb de joueurs qui empruntent cette arête
	private int pris[]; //si un joueur de rang i emprunte ou non l'arête
	private Noeud nd1; //origine
	private Noeud nd2; //destination
	
	public Arete(int idr, double[] fcout,int priorite[], int cap, Noeud nd1, Noeud nd2,int nj) {
		this.idr = idr;
		this.fcout = fcout;
		this.priorite = priorite;
		this.cap = cap;
		this.nbjoueurs=0;
		this.surcharge=0;
		this.pris=new int[nj];
		for (int i=0; i<nj; i++) {
			pris[i]=0;
		}
		this.nd1 = nd1;
		this.nd2 = nd2;
	}
	
	public Arete(Arete a) {
		this.idr = a.idr;
		this.fcout = a.fcout;
		this.priorite = a.priorite;
		this.cap = a.cap;
		this.nbjoueurs=a.nbjoueurs;
		this.surcharge=a.surcharge;
		this.pris=a.pris;
		this.nd1 = a.nd1;
		this.nd2 = a.nd2;
	}
	
	public int[] getPris() {
		return pris;
	}

	public void setPris(int a,int b) {
		this.pris[a] = b;
	}

	public int getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(int surcharge) {
		this.surcharge = surcharge;
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
		return fcout[i-1];
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
	
	public void jincrement(int i) { //joueur i
		int j=priorite[i];
		pris[j-1]=1;
		if (nbjoueurs<cap) {
			nbjoueurs++;
		} else {
			surcharge++;
		}
	}
	
	public void jdecrement(int i) {
		int j=priorite[i];
		pris[j-1]=0;
		if (surcharge>0) {
			surcharge--;
		} else {
			nbjoueurs--;
		}
	}
}
