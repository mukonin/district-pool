

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import entities.Person;
import utils.PersonIOException;
import utils.PersonUtils;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MainServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String action = request.getParameter("action");

		ArrayList<Person> list;
		Person person;
		
		switch (action) {
		case "Users" : list = dao.DAO.getPersons();
			request.setAttribute("page", "Users");
			request.setAttribute("list", list);
			request.getRequestDispatcher("users.jsp").forward(request, response);
			break;
		case "Doctors" : list = dao.DAO.getDoctors();
			request.setAttribute("page", "Doctors");
			request.setAttribute("list", list);
			request.getRequestDispatcher("users.jsp").forward(request, response);
			break;
		case "Patients" : list = dao.DAO.getPatients();
			request.setAttribute("page", "Patients");
			request.setAttribute("list", list);
			request.getRequestDispatcher("users.jsp").forward(request, response);
			break;
		case "New" : 
			request.getRequestDispatcher("adduser.jsp").forward(request, response);
			break;
		case "Add User" : person = new Person();
			person.setFirstName(request.getParameter("fname"));
			person.setLastName(request.getParameter("lname"));
			person.setDate(PersonUtils.getDateFromString(request.getParameter("date")));
			switch (request.getParameter("role")) {
			case "doc" : dao.DAO.addDoctor(person);
				break;
			case "pat" : dao.DAO.addPatient(person);
				break;
			};
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		case "user" : person = dao.DAO.getById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("person", person);
			request.setAttribute("role", dao.DAO.getRole(person));
			list = dao.DAO.getPatients(person);
			request.setAttribute("list", list);
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}
		
		
		
		
		/*
		
		if (action.equals("user")) {
			Person person = dao.DAO.getById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("user", true);	
			request.setAttribute("person", person);
			if (dao.DAO.isDoctor(person)) {
				//ArrayList<Person> list = dao.DAO.getPatients(person);
				request.setAttribute("patientslist", true);	
				//request.setAttribute("list", list);
			} else {
				request.setAttribute("doctorslist", true);
				Person doctor = dao.DAO.getDoctor(person);
				request.setAttribute("doctor", doctor);
			}
		}
		
		

		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		
		*/
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
