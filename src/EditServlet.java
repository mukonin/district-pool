

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;
import utils.PersonUtils;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		
		String action = request.getParameter("action");
		ArrayList<Person> list;
		Person doctor;
		Person person;
		Long id;
		
		switch (action) {
		case "delete" : id = Long.parseLong(request.getParameter("id"));
			person = dao.DAO.getById(id);
			request.setAttribute("user", person);
			dao.DAO.deleteUser(person);
			request.setAttribute("message", "User " + person + " deleted"); 
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
			//show add new user form
			
		case "new" : request.setAttribute("showcontent", true); 
			request.setAttribute("pagename", "Add New User");
			request.setAttribute("contentpage", "edituser.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			//adding new user / updating existing
			
		case "update" : person = new Person();
			if (request.getParameter("id").equals("")) {
				person.setFirstName(request.getParameter("fname"));
				person.setLastName(request.getParameter("lname"));
				person.setDate(PersonUtils.getDateFromString(request.getParameter("date")));
				Random random = new Random();
				person.setId((long) (random.nextDouble() * 9000000000L) + 1000000000L);
				switch (request.getParameter("role")) {
				case "doc" : dao.DAO.addDoctor(person);
					break;
				case "pat" : dao.DAO.addPatient(person);
					break;
				};
				request.setAttribute("message", "Added to DB");			
				request.setAttribute("pagename", "User: " + person);
				request.setAttribute("user", person);
				if (dao.DAO.getRole(person) == null) {
					request.setAttribute("role", "none");
				} else {
					request.setAttribute("role", dao.DAO.getRole(person));
				}
				request.setAttribute("showcontent", true);
				request.setAttribute("contentpage", "user.jsp");
				doctor = dao.DAO.getDoctor(person);
				list = dao.DAO.getPatients(person);
				request.setAttribute("list", list);
				request.setAttribute("doctor", doctor);
				request.getRequestDispatcher("index.jsp").forward(request, response);			
			} else {
				id = Long.parseLong(request.getParameter("id"));
				person = dao.DAO.getById(id);
				person.setFirstName(request.getParameter("fname"));
				person.setLastName(request.getParameter("lname"));
				person.setDate(PersonUtils.getDateFromString(request.getParameter("date")));
				dao.DAO.updateUser(person);
				switch (request.getParameter("role")) {
				case "doc" : dao.DAO.setRole(person, "doctor");
					break;
				case "pat" : dao.DAO.setRole(person, "patient");
					break;				
				};
				request.setAttribute("message", "User Data Updated");			
				request.setAttribute("pagename", "User: " + person);
				request.setAttribute("user", person);
				if (dao.DAO.getRole(person) == null) {
					request.setAttribute("role", "none");
				} else {
					request.setAttribute("role", dao.DAO.getRole(person));
				}
				request.setAttribute("showcontent", true);
				request.setAttribute("contentpage", "user.jsp");
				doctor = dao.DAO.getDoctor(person);
				list = dao.DAO.getPatients(person);
				request.setAttribute("list", list);
				request.setAttribute("doctor", doctor);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			break;
			
			//show edit user window
			
		case "edit" : person = dao.DAO.getById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("pagename", "Edit User Data: " + person);
			request.setAttribute("user", person);
			request.setAttribute("showcontent", true);
			request.setAttribute("contentpage", "edituser.jsp");	
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;		
		default : request.getRequestDispatcher("index.jsp").forward(request, response);
		};	
		
		
		
		
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
