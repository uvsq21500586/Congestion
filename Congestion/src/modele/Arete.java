package modele;

import java.util.TreeSet;

public class Arete {
	private double fcout[]; //fction du coût
	private int cap; //capacité
	private int priorite[]; //priorité des joueurs
	private int charge; //nb de joueurs qui empruntent cette arête
	private TreeSet<Integer> pris; //si un joueur de rang i emprunte ou non l'arête
	
	public Arete(int cap, double[] fcout, int priorite[]) {
		this.fcout = fcout;
		this.priorite = priorite;
		this.cap = cap;
		this.charge = 0;
		this.pris = new TreeSet<>();
	}
	
	public boolean addJoueur(int id) {
		return this.pris.add(id);
	}
	public boolean removeJoueur(int id) {
		return this.pris.remove(id);
	}
	public int getSurcharge() {
		return charge;
	}
	public void setSurcharge(int surcharge) {
		this.charge = surcharge;
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
}
