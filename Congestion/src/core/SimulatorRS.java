package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import modele.Arete2;
import modele.Joueur2;

//Algorithme 1: 2-ressource

public class SimulatorRS {
	
	private int nombreJoueurs;
	private Joueur2[] joueurs;
	private Arete2 R;
	private Arete2 S;
	
	//fonction min
	public int min(int a,int b){
		if (a>b) {
			return b;
		}
		return a;
	}
	

	public SimulatorRS(String file) throws IOException {
		String nomfichier = file;
		BufferedReader entree = new BufferedReader(new FileReader(nomfichier));
		String LigneLue = entree.readLine();
		StringTokenizer tok = new StringTokenizer(LigneLue, " ");
		
		// Lecture de la première ligne du fichier
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
			
			//Aretes R et S
			if (j==0){
				R=new Arete2(j,couts, priorites, cap,nombreJoueurs);
				//aretes.add(new Arete2(j,couts, priorites, cap,nombreJoueurs));
				for (int i=0; i<nombreJoueurs; i++) {
					R.setPris(i, 1);
				}
			} else {
				S=new Arete2(j,couts, priorites, cap,nombreJoueurs);
				S.setNbjoueurs(0);
			}
			j++;
		}
		entree.close();
		
		// Initialisation des joueurs
		joueurs = new Joueur2[nombreJoueurs];
		for(int i = 0; i < nombreJoueurs; i++)
			joueurs[i] = new Joueur2(i+1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimulatorRS s = null;
		try {
			s = new SimulatorRS("H2");
		} catch (IOException e) {
			e.printStackTrace();
		}
		char Str[]= new char[s.nombreJoueurs]; //stratégies des joueurs
		for (int i=0; i<s.nombreJoueurs; i++) {
			Str[i]='R'; //initialisation des joueurs à R, ligne 2
		}
		
		char N[]= new char[s.nombreJoueurs]; //indiquer si un joueur appartient à Ninfini('i') ou à Nf('f')
		//Dans ce tableau, les joueurs sont classés selon les priorités de S
		
		int surcR=0; //indique si le nb de joueurs est supérieur à la capacité de R
		int posR=0; //position du joueur pour la ressource R
		int numj=0; //numéro du joueur
		int bool=0; //0 ou 1
		if (s.nombreJoueurs>s.R.getCap()) surcR=1;
		if (surcR==0) {
			for (int i=0; i<s.nombreJoueurs; i++){
				N[i]='f';
			}
		} else {
			s.R.setSurcharge(s.nombreJoueurs-s.R.getCap());
			s.R.setNbjoueurs(s.R.getCap());
			System.out.println("surcharge de R="+s.R.getSurcharge());
			for (int i=0; i<s.nombreJoueurs; i++){
				numj=s.S.getPriorite2(i);
				posR=0;
				bool=0; //joueur non trouvé
				while (bool==0 && posR<s.R.getCap()) {
					if (s.R.getPriorite2(posR)==numj) {
						bool=1;
					} else {
						posR++;
					}
				}
				if (posR<s.R.getCap()) {
					N[i]='f';
				} else {
					N[i]='i';
				}
			}
		}
		int numjoueur[]=new int[s.nombreJoueurs];
		for (int i=0; i<s.nombreJoueurs; i++){
			numjoueur[i]=s.S.getPriorite2(i);
			System.out.print(numjoueur[i]+" ");
		}
		System.out.println();
		for (int i=0; i<s.nombreJoueurs; i++){
			System.out.print(N[i]+" ");
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Initialisation");
		for (int i=0; i<s.nombreJoueurs; i++){
			System.out.print(Str[i]+" ");
		}
		System.out.println();
		//Etapes 5 à 7
		for (int i=0; i<s.nombreJoueurs; i++){
			if (N[i]=='i' && s.S.getNbjoueurs()<s.S.getCap()) {
				Str[i]='S';
				s.S.jincrement(numjoueur[i]);
				s.R.jdecrement(numjoueur[i]);
			}
		}
		
		System.out.println("Apres la premiere boucle for");
		for (int i=0; i<s.nombreJoueurs; i++){
			System.out.print(Str[i]+" ");
		}
		System.out.println();
		
		//Etapes 8 à 15
		int posS=0;
		int k2=0;
		for (int i=0; i<s.nombreJoueurs; i++){
			if (N[i]=='f'){
				posS=1;
				for (int k=0; k<i; k++) {
					if (Str[k]=='S') posS++;
				}
				if (s.S.getNbjoueurs()<s.S.getCap() && s.R.getFcout2(s.R.getNbjoueurs())>s.S.getFcout2(s.S.getNbjoueurs()+1) ) {
					Str[i]='S';
					s.S.jincrement(numjoueur[i]);
					s.R.jdecrement(numjoueur[i]);
				} else if (posS<=s.S.getCap() && s.R.getFcout2(s.R.getNbjoueurs())>s.S.getFcout2(s.S.getNbjoueurs())) {
					s.S.jincrement(numjoueur[i]);
					bool=0;
					k2=s.nombreJoueurs;
					while (bool==0) {
						if (Str[numjoueur[k2-1]]=='S') {
							bool=1;
						} else {
							k2--;
						}
					}
					Str[i]='S';
					Str[k2-1]='R';
					s.S.jdecrement(numjoueur[k2-1]);
					s.R.jincrement(numjoueur[k2-1]);
				}
			}
		}
		
		System.out.println("Final");
		for (int i=0; i<s.nombreJoueurs; i++){
			System.out.print(Str[i]+" ");
		}
		System.out.println();
		
		
	}

}
