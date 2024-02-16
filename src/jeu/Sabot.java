package jeu;
import cartes.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Sabot implements Iterable<Carte> {
	private int nbCartesMax = 110;
	private int nbCartes;
	private Carte[] tabcartes;
	private Iterator<Carte> iter = iterator(); 

	public Sabot(int nbCartesMax) {
		this.tabcartes = new Carte[nbCartesMax];
		this.nbCartes = 0;
	}

	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = 0;
		private int nombreOperations = 0;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}

		@Override
		public Carte next() {
			if (hasNext()) {
				Carte res = tabcartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return res;
			} else {
				throw new NoSuchElementException("Il n y a plus de carte apres");
			}
		}

		@Override
		public void remove() {
			verificationConcurrence();
			if (nbCartes < 1 || !nextEffectue) {
				throw new IllegalStateException("Il n y a pas de carte a supprimer");
			} else {
				for (int i = indiceIterateur - 1; i < nbCartes; i++) {
					tabcartes[i] = tabcartes[i + 1];
				}
				nextEffectue = false;
				indiceIterateur--;
				nbCartes--;
				nombreOperationsReference++; 
				nombreOperations++;
			}
		}
		
		private void verificationConcurrence(){
			if (nombreOperations != nombreOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
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
	
	public void ajouterFamilleCarte(Carte... carte) {
		for (Carte carte2 : carte) {
			for (int i = 0; i < carte2.getNombre(); i++) {
				ajouterCarte(carte2);
			}
		}
	}
	/* */
	
	public Carte piocher() {
		Carte res = iter.next();
		iter.remove();
		return res;
	}
	
}
