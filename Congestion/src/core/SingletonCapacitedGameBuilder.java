package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import modele.Arete;
import modele.Arete2;
import modele.Joueur;
import modele.Joueur2;

public class SingletonCapacitedGameBuilder {
	
	private int nombreJoueurs;
	private Joueur2[] joueurs;
	private ArrayList<Arete2> aretes;

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
			s = new SingletonCapacitedGameBuilder("H");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//initialisation des joueurs (on les attribue au premier chemin non saturé
		Arete2 a;
		int num=0;
		int Str[]= new int[s.nombreJoueurs];
		for (int i=0; i<s.nombreJoueurs;i++) {
			s.joueurs[i]=new Joueur2(i+1);
			Str[i]=num;
			s.aretes.get(Str[i]).jincrement(i);
			a=s.aretes.get(Str[i]);
			if (a.getNbjoueurs()==a.getCap()) {
				num++;
			}
						
			for (int k=0; k<s.aretes.size(); k++) {
				System.out.print(s.aretes.get(k).getNbjoueurs()+" ");
			}
			System.out.println();
		}
		System.out.println("Succès" + s);
	
		for (int i=0;i<s.nombreJoueurs; i++) {
			System.out.println("Joueur"+(i+1)+":route"+Str[i]);
		}
	}
}
