package source;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxServlet extends HttpServlet {

	private static final long serialVersionUID = -8308514275340556375L;

	public AjaxServlet() {
	}
	public synchronized void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Collection images = new Collection();

		if (!(req.getParameter("listeImages") == null || req
				.getParameter("listeImages") == "")) {

			Integer photoId = Integer.parseInt(req.getParameter("listeImages"));
			Photo photo = images.getPhoto(photoId);

			res.setContentType("text/html");

			req.setAttribute("photo", photo);
			req.setAttribute("dossierVignettes", getServletConfig()
					.getInitParameter("dossierVignettes"));

			RequestDispatcher RequetsDispatcherObj = getServletContext()
					.getRequestDispatcher("/details.jsp");
			RequetsDispatcherObj.include(req, res);

		} else {
			if (req.getParameter("listeImages") != null) {
				if (req.getParameter("listeImages").isEmpty()
						|| req.getParameter("listeImages") == "") {
					RequestDispatcher RequetsDispatcherObj = getServletContext()
							.getRequestDispatcher("/error.jsp");
					RequetsDispatcherObj.forward(req, res);
				}
			} else {
				req.setAttribute("collection", images.getImages());

				RequestDispatcher RequetsDispatcherObj = getServletContext()
						.getRequestDispatcher("/collection.jsp");
				RequetsDispatcherObj.forward(req, res);
			}
		}

	}
}