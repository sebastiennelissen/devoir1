/**
 *  Démo AJAX en Java EE, par Eric Boivin
 *  Cette classe est le conteneur des objets Image. C'est elle qui
 *  connaît la logique pour populer les JavaBean.
 **/
package ca.etsmtl.gti525.devoir1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Collection {
	private Map<Integer, Image> maCollection = new LinkedHashMap<Integer, Image>();

	public Collection() {
		// Stub de génération des Beans
		Image image1 = new Image();
		Image image2 = new Image();
		Image image3 = new Image();
		Image image4 = new Image();
		Image image5 = new Image();

		image1.setChemin("fallout1.jpg");
		image2.setChemin("fallout2.jpg");
		image3.setChemin("fallout3.jpg");
		image4.setChemin("fallout4.jpg");
		image5.setChemin("fallout5.jpg");

		image1.setTitre("Monument");
		image2.setTitre("Soldats");
		image3.setTitre("Porte-avions");
		image4.setTitre("Washington");
		image5.setTitre("Désert");

		image1.setId(1);
		image2.setId(2);
		image3.setId(3);
		image4.setId(4);
		image5.setId(5);

		maCollection.put(image1.getId(), image1);
		maCollection.put(image2.getId(), image2);
		maCollection.put(image3.getId(), image3);
		maCollection.put(image4.getId(), image4);
		maCollection.put(image5.getId(), image5);
	}

	/**
	 * Pour obtenir le Map de toutes les images
	 * 
	 * @return
	 */
	public List<Image> getImages() {
		return new ArrayList<Image>(maCollection.values());
	}

	/**
	 * Pour obtenir un bean selon son id
	 * 
	 * @param id
	 * @return
	 */
	public Image getImage(int id) {
		return maCollection.get(id);
	}
}
