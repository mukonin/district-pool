

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.DATA_CONVERSION;

import entities.Patient;
import entities.Person;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ArrayList<Person> list;
		ArrayList<Patient> list2;
		
		switch (action) {
		case "doctors" : 
			//list = dao.DAO.getDoctors();
			list = persistence.PersonService.getDoctors(); 
			request.setAttribute("pagename", "Doctors");
			request.setAttribute("list", list);
			if (request.getParameterMap().containsKey("sort") && request.getParameter("sort").equals("true")) {
				Collections.sort(list, utils.PersonUtils.DATA_SORT);
			}
			request.setAttribute("contentpage", "users.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		case "patients" : 
			//list2 = dao.DAO.getPatients();
			list2 = persistence.PersonService.getPatients();
			request.setAttribute("pagename", "Patients");
			request.setAttribute("list", list2);
			if (request.getParameterMap().containsKey("sort") && request.getParameter("sort").equals("true")) {
				Collections.sort(list2, utils.PersonUtils.DATA_SORT);
			}
			request.setAttribute("contentpage", "users.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		default : request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
