/*Servlet gérant un système d'achat d'images 
  Code inspiré de l'article "Watermarking images in a Java Servlet"
  Disponible au http://www.codebeach.com/2008/02/watermarking-images-in-java-servlet.html
  Code modifié par Eric Boivin, dans le cadre du cours GTI525, été 2007
  Dernière modification, été 2014
 */

package ca.etsmtl.gti525.devoir1;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

public class WatermarkServlet extends HttpServlet {
	private static final long serialVersionUID = -4865099303373864287L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		if (req.getParameter("carteCredit") != null
				&& !req.getParameter("carteCredit").equals("")) {
			req.setAttribute("paye", true);
			doGet(req, res);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		/*
		 * L'appel de ce servlet se fait en donnant l'url de la miniature de
		 * l'image. Ici, on remplace le chemin du répertoire de vignettes par le
		 * répertoire contenant les originaux.
		 */
		String path = req.getPathTranslated().replaceAll(
				getServletConfig().getInitParameter("dossierVignettes"),
				getServletConfig().getInitParameter("dossierOriginaux"));

		try {
			File file = new File(path);
			if (!file.exists()) {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			ImageIcon photo = new ImageIcon(path);

			BufferedImage bufferedImage = new BufferedImage(
					photo.getIconWidth(), photo.getIconHeight(),
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();

			g2d.drawImage(photo.getImage(), 0, 0, null);

			/*
			 * Si le client n'a pas "payé", on ajoute le watermark. Sinon, on
			 * change l'attribut de paye pour faire en sorte que l'utilisateur
			 * doive repayer à chaque nouvelle image
			 */
			if (req.getAttribute("paye") != null
					&& (Boolean) req.getAttribute("paye") == true) {
				req.removeAttribute("paye");
			} else {
				g2d = addWatermark(g2d, photo);
			}

			// Libérer les ressources graphiques
			g2d.dispose();

			// Fixe le type MIME de l'image pour que le navigateur l'affiche
			// correctement
			res.setContentType("image/jpg");

			/*
			 * Au lieu de faire l'insertion de l'hyperlien de l'image, on écrit
			 * directement les octets de l'image dans la réponse, masquant ainsi
			 * l'URL de l'image originale
			 */
			OutputStream out = res.getOutputStream();
			ImageIO.write(bufferedImage, "jpg", out);
			out.close();
		} catch (IOException ioe) {
		}
	}

	/**
	 * Méthode qui écrit un texte au centre d'une image
	 * 
	 * @param g2d
	 * @param photo
	 * @return
	 */
	private Graphics2D addWatermark(Graphics2D g2d, ImageIcon photo) {
		AlphaComposite alpha = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 0.5f);
		g2d.setComposite(alpha);

		g2d.setColor(Color.white);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g2d.setFont(new Font("Arial", Font.BOLD, 30));

		String watermark = getServletConfig().getInitParameter("message");

		FontMetrics fontMetrics = g2d.getFontMetrics();
		Rectangle2D rect = fontMetrics.getStringBounds(watermark, g2d);

		g2d.drawString(watermark,
				(photo.getIconWidth() - (int) rect.getWidth()) / 2,
				(photo.getIconHeight() - (int) rect.getHeight()) / 2);

		return g2d;
	}
}