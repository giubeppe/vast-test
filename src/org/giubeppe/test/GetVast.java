package org.giubeppe.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.giubeppe.test.utils.Macros;

/**
 * Servlet implementation class GetVast
 * The servlet needs to live in the root "/" context. The following commands are available:
 * http://.../getvast				returns a vast file
 * http://.../getvast?wrap			returns a wrapper pointing to a vast file
 * http://.../getvast?comp			returns a vast file with companion
 * http://.../getvast?compw			returns a wrapper with a companion  
 * http://.../getvast?wrap&comp		returns a wrapper pointing to a vast file with a companion
 */
@WebServlet(name = "getvast", urlPatterns = { "/getvast" })
public class GetVast extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetVast() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		boolean wrapper = (request.getParameter("wrap") != null);
		boolean comp = (request.getParameter("comp") != null);
		boolean compw = (request.getParameter("compw") != null);
		
		String vastTemplate;
		if (compw) {
			vastTemplate = "/vast-wrapper-WITH-companion.xml";
		} else {
			if (wrapper) {
				if (comp)
					vastTemplate = "/vast-companion-wrapper.xml";
				else
					vastTemplate = "/vast-wrapper.xml";
			} else {
				if (comp) 
					vastTemplate = "/vast-companion.xml";
				else
					vastTemplate = "/vast-basic.xml";
	//				vastTemplate = "/vast-fox.xml";
			}
		}

		System.out.println("Serving template: '"+vastTemplate+"'");
		
		String lines = getResourceAsStringAndExpandMacros(vastTemplate, getMacros(request));

		response.setContentType("text/xml");
		response.getWriter().write(lines);
	}

	private Map<String, String> getMacros(HttpServletRequest request) {
		
		
		Map<String, String> theMacros = new HashMap<String, String>();
		
		String serverName = request.getServerName();
		String serverPort = new Integer(request.getServerPort()).toString();
		String serverScheme = request.getScheme();

		theMacros.put("\\$\\{DOMAIN\\}", serverScheme+"://"+serverName+":"+serverPort);
		theMacros.put("\\$\\{DATE\\}", new java.util.Date().toString());
		
		return theMacros;
	}
	
	private String getResourceAsStringAndExpandMacros(String resourcePath, Map<String, String> macros) throws IOException {
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream(resourcePath);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String line = "";
		String lines = "";
		while ((line = in.readLine()) != null) {
			lines += line + "\n";
		}
		
		lines = Macros.expandMacros(lines, macros);
		
		return lines;
	}

}
