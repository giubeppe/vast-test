package org.giubeppe.test;

import java.io.IOException;
import java.util.logging.Logger;

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
	private static Logger logger = Logger.getAnonymousLogger();
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

		logger.info((wrapper?"WRAPPER ": "")+"tracking "+eventValue);
	}


}
