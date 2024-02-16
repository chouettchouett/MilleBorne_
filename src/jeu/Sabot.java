package jeu;
import cartes.*;

public class Sabot {
	private int nbCartesMax = 110;
	private int nbCartes;
	private Carte[] tabcartes;

	public Sabot(int nbCartesMax) {
		this.tabcartes = new Carte[nbCartesMax];
		this.nbCartes = 0;
	}

	public boolean estVide(int nbCartes) {
		return nbCartes == 0;
	}
	
	private void ajouterCarte(Carte cartes) {
		if (nbCartes == nbCartesMax) {
			throw new ArrayIndexOutOfBoundsException("Le sabot est plein");
			
		}
		else {
			tabcartes[nbCartes] = cartes;
			nbCartes = nbCartes + 1;
			
		}
		
	}
	
	public void ajouterFamilleCarte (Carte cartechoisi){
		
	}
	

	public void ajouterFamilleCarte (int nbCartes,Carte cartechoisi){
		
	}
	
}
