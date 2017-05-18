package src;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final long serialVersionUID = -8308514275340556375L;

	public AjaxServlet() { } 

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res); 
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{ 
		Collection images = new Collection();
		 String param = req.getParameter("listeImages"); 
		if(param==null){
 			//premier appel d'ajax servlet, on affiche la liste d'images en appellant collections.jsp
			req.setAttribute("collection", images.getImages());
 			RequestDispatcher rd = getServletContext().getRequestDispatcher("/collection.jsp"); 
			rd.forward(req, res);
		}
		
		 else if (param.isEmpty()) {
 			//appel d'ajaxservlet avec un parametre vide, on affiche la page d'erreur
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp"); 
			rd.forward(req, res);
		 }
	}
}
