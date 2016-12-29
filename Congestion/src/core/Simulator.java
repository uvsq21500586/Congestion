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
		Ctest.AjoutArete(new Arete(0,fct[0],prio[0], 1,Ctest.getNoeuds().get(0) , Ctest.getNoeuds().get(1),Njoueurs));
		//System.out.println("Noeud 0:"+Ctest.getNoeuds().get(0).getRoutes().size());
		
		
		
		Ctest.getNoeuds().add(new Noeud(2));
		System.out.println("Noeud 0:"+Ctest.getNoeuds().get(0).getRoutes().size());
		for (int i=0; i<Ctest.getNoeuds().size();i++) {
			System.out.println("Noeud "+i+"="+Ctest.getNoeuds().get(i).getType());
		}
		System.out.println();
		
		//chemin s
		Ctest.AjoutArete(new Arete(1,fct[1],prio[1], 3,Ctest.getNoeuds().get(0) , Ctest.getNoeuds().get(2),Njoueurs));
		Ctest.AjoutArete(new Arete(2,fct[2],prio[2], 2,Ctest.getNoeuds().get(2) , Ctest.getNoeuds().get(1),Njoueurs));
		
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
		
		//initialisation des joueurs (on les attribue au premier chemin non saturé
		Arete a;
		int num=0;
		for (int i=0; i<Njoueurs;i++) {
			J[i]=new Joueur(i+1);
			Str[i]=num;
			for (int j=0; j<S[Str[i]].getSt().size();j++) {
				a=S[Str[i]].getSt().get(j);
				a.jincrement(i);
				if (a.getNbjoueurs()==a.getCap()) {
					num++;
				}
				S[Str[i]].getSt().set(j, a);
				
			}
			for (int k=0; k<Njoueurs; k++) {
				System.out.print(S[0].getSt().get(0).getPris()[k]+" ");
			}
			System.out.println();
		}
		
		//calcul du cout
		double ctotal;
		int pos;//position
		int rg; //rang

		for (int i=0;i<Njoueurs;i++) {
			ctotal=0;
			for (int j=0; j<S[Str[i]].getSt().size(); j++) {
				a=S[Str[i]].getSt().get(j);
				if (a.getCap()>=a.getPriorite2(i)) { //le joueur fait parti des joueurs prioritaires de l'arete, ils ne peut donc pas avoir un cout infini
					ctotal+=a.getFcout2(a.getNbjoueurs());
				} else if (a.getSurcharge()==0) { //l'arête n'est pas surchargée
					ctotal+=a.getFcout2(a.getNbjoueurs());
				} else {
					//recherche de la position du joueur pour l'arête a
					rg=a.getPriorite2(i);
					pos=0;
					for (int k=0; k<rg; k++) {
						if (a.getPris()[k]==1) {
							pos++;
						}
					}
					//System.out.println("position joueur"+(i+1)+"="+pos);
					if (pos<=a.getCap()) {
						ctotal+=a.getFcout2(a.getNbjoueurs());
					} else {
						ctotal=-1; //coût infini
					}
					
				}
			}
			cout[i]=ctotal;
		}
		
		for (int i=0;i<Njoueurs;i++) {
			System.out.println("Joueur "+(i+1)+": cout="+cout[i] + "; chemin="+Str[i]);
		}
		System.out.println("fin");
	}

}
