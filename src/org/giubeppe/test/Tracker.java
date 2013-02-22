package org.giubeppe.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Tracker
 */
@WebServlet(name = "tracker", urlPatterns = { "/tracker" })
public class Tracker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tracker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String eventValue = request.getParameter("event");
		boolean wrapper = (request.getParameter("wrap") != null);

		System.out.println((wrapper?"WRAPPER ": "")+"tracking "+eventValue);
	}


}
