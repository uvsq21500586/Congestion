package core;

import modele.Arete;
import modele.Congestion;
import modele.Joueur;
import modele.Noeud;
import modele.Strategie;

public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int Njoueurs=3;
		Congestion Ctest=new Congestion();
		
		for (int i=0; i<Ctest.getNoeuds().size();i++) {
			System.out.println("Noeud "+Ctest.getNoeuds().get(i).getId()+"; type="+Ctest.getNoeuds().get(i).getType());
		}
		System.out.println();
		
		//fonctions des coûts et priorités des arêtes
		double fct[][] = {{3,3},{1,2},{1,2}};
		int prio[][] = {{3,2,1},{1,3,2},{1,2,3}};
		double cout[]=new double[Njoueurs];
		for (int i=0;i<Njoueurs;i++){
			cout[i]=-1;
		}
		//chemin r
		Ctest.AjoutArete(new Arete(0,fct[0],prio[0], 2,Ctest.getNoeuds().get(0) , Ctest.getNoeuds().get(1)));
		//System.out.println("Noeud 0:"+Ctest.getNoeuds().get(0).getRoutes().size());
		
		
		
		Ctest.getNoeuds().add(new Noeud(2));
		System.out.println("Noeud 0:"+Ctest.getNoeuds().get(0).getRoutes().size());
		for (int i=0; i<Ctest.getNoeuds().size();i++) {
			System.out.println("Noeud "+i+"="+Ctest.getNoeuds().get(i).getType());
		}
		System.out.println();
		
		//chemin s
		Ctest.AjoutArete(new Arete(1,fct[1],prio[1], 2,Ctest.getNoeuds().get(0) , Ctest.getNoeuds().get(2)));
		Ctest.AjoutArete(new Arete(2,fct[2],prio[2], 2,Ctest.getNoeuds().get(2) , Ctest.getNoeuds().get(1)));
		
		for (int i=0; i<Ctest.getAretes().size();i++) {
			System.out.println("Arete "+i+"="+Ctest.getAretes().get(i).getIdr());
		}
		
		//jeu
		Strategie S[]=new Strategie[2]; //différents chemins possibles
		
		for (int i=0; i<2;i++) {
			S[i]=new Strategie();
		}
		S[0].ajoutarete(Ctest.getAretes().get(0));
		S[1].ajoutarete(Ctest.getAretes().get(2));
		//S[1].ajoutarete(Ctest.getAretes().get(2));
		Joueur J[]=new Joueur[Njoueurs];
		int Str[]= new int[Njoueurs]; //Chemin choisi par le joueur i 
		
		//initialisation des joueurs (on les attribue au premier chemin
		Arete a;
		for (int i=0; i<Njoueurs;i++) {
			J[i]=new Joueur(i+1);
			Str[i]=0;
			for (int j=0; j<S[0].getSt().size();j++) {
				a=S[0].getSt().get(j);
				if (a.getNbjoueurs()<a.getCap()) {
					a.jincrement();
				} else {
					a.setSurcharge(a.getSurcharge()+1);
				}
			}
		}
		
		//calcul du cout
		double ctotal;
		for (int i=0;i<Njoueurs;i++) {
			ctotal=0;
			for (int j=0; j<S[Str[i]].getSt().size(); j++) {
				a=S[Str[i]].getSt().get(j);
				if (a.getCap()>=a.getPriorite2(i)) {
					ctotal+=a.getFcout2(S[Str[i]].getSt().get(j).getNbjoueurs());
				} else if (a.getSurcharge()==0) {
					ctotal+=a.getFcout2(a.getNbjoueurs());
				} else {
					ctotal=-1; //coût infini
				}
			}
			cout[i]=ctotal;
		}
		
		for (int i=0;i<Njoueurs;i++) {
			System.out.println("Joueur "+(i+1)+": cout="+cout[i]);
		}
		System.out.println("fin");
	}

}
