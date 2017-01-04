package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import modele.Arete2;
import modele.Joueur2;



public class SingletonCapacitedGameBuilder {
	
	private int nombreJoueurs;
	private Joueur2[] joueurs;
	private ArrayList<Arete2> aretes;
	
	//fonction min
	public int min(int a,int b){
		if (a>b) {
			return b;
		}
		return a;
	}
	

	public SingletonCapacitedGameBuilder(String file) throws IOException {
		String nomfichier = file;
		BufferedReader entree = new BufferedReader(new FileReader(nomfichier));
		String LigneLue = entree.readLine();
		StringTokenizer tok = new StringTokenizer(LigneLue, " ");
		
		// Lecture de la première ligne du fichier
		aretes = new ArrayList<>();
		nombreJoueurs = Integer.parseInt((tok.nextToken()));
		
		// Chaque arete est composé de 3 lignes
		int j=0;
		while ((LigneLue = entree.readLine()) != null) {
			
			// Première ligne (capacité)
			int cap = Integer.parseInt(LigneLue);
			LigneLue = entree.readLine();
			tok = new StringTokenizer(LigneLue, " ");
			
			// Deuxième ligne (coût en fonction du nombre de joueurs accomodés)
			double[] couts = new double[cap];
			for(int i = 0; i < cap; i++)
				couts[i] = Double.parseDouble(tok.nextToken());
			LigneLue = entree.readLine();
			tok = new StringTokenizer(LigneLue, " ");
			
			// Troisième ligne (priorité des joueurs)
			int[] priorites = new int[nombreJoueurs];
			for(int i = 0; i < nombreJoueurs; i++)
				priorites[i] = Integer.parseInt(tok.nextToken());
			
			aretes.add(new Arete2(j,couts, priorites, cap,nombreJoueurs));
		}
		entree.close();
		
		// Initialisation des joueurs
		joueurs = new Joueur2[nombreJoueurs];
		for(int i = 0; i < nombreJoueurs; i++)
			joueurs[i] = new Joueur2(i+1);
	}
	
	public static void main(String[] Args) {
		SingletonCapacitedGameBuilder s = null;
		try {
			s = new SingletonCapacitedGameBuilder("H2G2");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//initialisation des joueurs (on les attribue au premier chemin non saturé
		Arete2 a;
		int Str[]= new int[s.nombreJoueurs];
		System.out.println("Succès" + s);
		
		int n2; //n2=^n
		int k=0; //k=somme des kr
		for (int i=0;i<s.aretes.size(); i++){
			k=k+s.aretes.get(i).getCap();
		}
		int j;
		//etape 2
		n2=s.min(s.nombreJoueurs,k);
		int temps=0;
		int indicearete=0;
		System.out.println("n="+s.nombreJoueurs+"; n2="+n2+"; k="+k);
		//etapes 3-7
		Arete2 a2;
		double couti,couti2;
		couti=0;
		int k2=0;
		int k3=0;
		a=s.aretes.get(0);
		couti2=-1;
		while (n2>0 && a!=null) {
			//etape 4
			a=null;
			j=0;
			indicearete=0;
			couti2=Double.MAX_VALUE;
			while (j<s.aretes.size()) {
				a2=s.aretes.get(j);
				//if (a2.getNr()<s.min(a2.getNr()+n2,a2.getCap()))
				//a=a2;
				k=a2.getNr()+1;
				if (k<=s.min(a2.getNr()+n2,a2.getCap())){
					couti=a2.getFcout2(k);
					k3=k;
					while (k<=s.min(a2.getNr()+n2,a2.getCap())) {
						if (a2.getFcout2(k)<couti) {
							k3=k;
							couti=a2.getFcout2(k);
						}
						k++;
					}

					if (couti<couti2 && k3>a2.getNr()){
						a=a2;//mise à jour de l'arete à remplir
						k2=k3;
						couti2=couti; //mise à jour du cout minimum
						indicearete=j; //mise à jour de l'indice de l'arete
					}
				}
				j++;
			}
			if (a!=null) {
				//etape 5
				n2=n2-(k2-a.getNr());
				
				//etape 6
				s.aretes.get(indicearete).setNr(k2);
				//a.setNr(a.getCap());
				System.out.println("n2="+n2+"; route"+indicearete+": nr="+s.aretes.get(indicearete).getNr());
			}
			temps++;
		}
		
		if (n2>0) {
			System.out.println("Erreur equilibre de Nash non trouve, " +temps +" iterations");
		} else {
			double[] tabdelay=new double[s.aretes.size()]; //tableau des fonctions des dr(nr) de chaque arete
			for (int i=0;i<s.aretes.size(); i++) {
				a=s.aretes.get(i);
				tabdelay[i]=a.getFcout2(a.getNr());
				System.out.print("arete"+(i+1)+":"+tabdelay[i]+"; ");
			}
			System.out.println();
			
			//etape 8 tri du tableau tabdelay
			double[] tabdelay2=new double[s.aretes.size()]; //clone
			int[] tabindice=new int[s.aretes.size()]; //table des indices des aretes
			for (int i=0;i<s.aretes.size();i++) {
				if (i==0) {
					tabdelay2[0]=tabdelay[0];
					tabindice[0]=0;
				}
				else {
					j=0;
					while (j<i && tabdelay[i]>tabdelay2[j]) {
						j++;
					}
					if (j==i) {
						tabdelay2[j]=tabdelay[i];
						tabindice[j]=i;
					}
					else {
						for (k=i;k>j;k--) {
							tabdelay2[k]=tabdelay2[k-1];
							tabindice[k]=tabindice[k-1];
						}
						tabdelay2[j]=tabdelay[i];
						tabindice[j]=i;
					}
				}
			}
			tabdelay=tabdelay2;
			for (int i=0;i<s.aretes.size(); i++) {
				System.out.print("arete"+tabindice[i]+":"+tabdelay[i]+" nr="+s.aretes.get(tabindice[i]).getNr()+"; ");
			}
			System.out.println();
			
			//etape 9: attribuer les joueurs aux aretes
			int[] joueursrestants = new int[s.nombreJoueurs]; //joueurs non classés
			for (int i=0;i<s.nombreJoueurs; i++) {
				joueursrestants[i]=1;
			}
			int N=s.nombreJoueurs; //nombre de joueurs restants
			int j2; //indice d'un joueur
			j=0;
			while (N>0) { //on continue tant que les joueurs ne sont pas tous classés
				k=0; //compteur de joueurs attribués à l'arete
				k2=0;//k2 balaiera le tableau des priorités de l'arete
				a=s.aretes.get(tabindice[j]);
				while (k<a.getNr() && N>0){
					j2=a.getPriorite2(k2)-1;
					if (joueursrestants[j2]>0) {
						Str[j2]=tabindice[j];
						k++;
						joueursrestants[j2]=0;
						N--;
					}
					k2++;
				}
				j++;
			}
			
			
			//etape 14: output NE
			for (int i=0;i<s.nombreJoueurs; i++) {
				a=s.aretes.get(Str[i]);
				System.out.println("Joueur"+(i+1)+":route"+Str[i]+"; cout="+a.getFcout2(a.getNr()));
			}
		}
	}
}
