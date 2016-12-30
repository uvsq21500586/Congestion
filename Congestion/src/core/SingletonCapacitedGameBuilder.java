package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import modele.Arete;
import modele.Joueur;

public class SingletonCapacitedGameBuilder {
	
	private int nombreJoueurs;
	private Joueur[] joueurs;
	private ArrayList<Arete> aretes;

	public SingletonCapacitedGameBuilder(String file) throws IOException {
		String nomfichier = file;
		BufferedReader entree = new BufferedReader(new FileReader(nomfichier));
		String LigneLue = entree.readLine();
		StringTokenizer tok = new StringTokenizer(LigneLue, " ");
		
		// Lecture de la premi�re ligne du fichier
		aretes = new ArrayList<>();
		nombreJoueurs = Integer.parseInt((tok.nextToken()));
		
		// Chaque arete est compos� de 3 lignes
		while ((LigneLue = entree.readLine()) != null) {
			
			// Premi�re ligne (capacit�)
			int cap = Integer.parseInt(LigneLue);
			LigneLue = entree.readLine();
			tok = new StringTokenizer(LigneLue, " ");
			
			// Deuxi�me ligne (co�t en fonction du nombre de joueurs accomod�s)
			double[] couts = new double[cap];
			for(int i = 0; i < cap; i++)
				couts[i] = Double.parseDouble(tok.nextToken());
			LigneLue = entree.readLine();
			tok = new StringTokenizer(LigneLue, " ");
			
			// Troisi�me ligne (priorit� des joueurs)
			int[] priorites = new int[nombreJoueurs];
			for(int i = 0; i < nombreJoueurs; i++)
				priorites[i] = Integer.parseInt(tok.nextToken());
			
			aretes.add(new Arete(cap, couts, priorites));
		}
		entree.close();
		
		// Initialisation des joueurs
		joueurs = new Joueur[nombreJoueurs];
		for(int i = 0; i < nombreJoueurs; i++)
			joueurs[i] = new Joueur(i+1);
	}
	
	public static void main(String[] Args) {
		@SuppressWarnings("unused")
		SingletonCapacitedGameBuilder s = null;
		try {
			s = new SingletonCapacitedGameBuilder("H");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
