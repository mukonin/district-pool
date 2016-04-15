

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Patient;
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
		ArrayList<Patient> list2;
		Person doctor;
		Person person;
		Long id;
		Random random;
		
		switch (action) {
		case "delete" : id = Long.parseLong(request.getParameter("id"));
			person = dao.DAO.getById(id);
			request.setAttribute("message", "User " + person.toString() + " deleted");
			dao.DAO.deleteUser(person);
			list = dao.DAO.getPersons();
			request.setAttribute("contentpage", "users.jsp");
			request.setAttribute("list", list);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
			//adding new user
			
		case "new" : request.setAttribute("contentpage", "adduser.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
			// updating existing
			
		case "update" : person = new Person();
			if (request.getParameter("id").equals("")) {
				person.setFirstName(request.getParameter("fname"));
				person.setLastName(request.getParameter("lname"));
				person.setDate(PersonUtils.getDateFromString(request.getParameter("date")));
				random = new Random();
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

		String action = request.getParameter("action");
		ArrayList<Person> list = new ArrayList<>();
		ArrayList<Patient> list2 = new ArrayList<>();
		Person doctor;
		Person patient;
		Person person;
		Long id;
		Random random;
		String role;
		Person person1;
		Person person2;
		
		switch (action) {
		
		// delete user
		
		case "delete" : id = Long.parseLong(request.getParameter("id"));
			person = dao.DAO.getById(id);
			role = dao.DAO.getRole(person);
			dao.DAO.deleteUser(person);
			switch (role) {
			case "doctor" : list = dao.DAO.getDoctors();
				request.setAttribute("pagename", "Doctors");
				request.setAttribute("message", "Doctor " + person + " deleted");	
				request.setAttribute("list", list);
				break;
			case "patient" : list2 = dao.DAO.getPatients();
				request.setAttribute("pagename", "Patients");
				request.setAttribute("message", "Patient " + person + " deleted");	
				request.setAttribute("list", list2);
				break;				
			};
			request.setAttribute("contentpage", "users.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
		// update user info
			
		case "update" : person = new Person();
			id = Long.parseLong(request.getParameter("id"));
			person.setId(id);
			person.setFirstName(request.getParameter("fname"));
			person.setLastName(request.getParameter("lname"));
			person.setDate(PersonUtils.getDateFromString(request.getParameter("date")));
			dao.DAO.updateUser(person);
			role = dao.DAO.getRole(person);
			switch (role) {
			case "doctor" : list = dao.DAO.getDoctors();
				request.setAttribute("pagename", "Doctors");
				request.setAttribute("message", "Doctor " + person + " information updated");	
				request.setAttribute("list", list);	
				break;
			case "patient" : list2 = dao.DAO.getPatients();
				request.setAttribute("pagename", "Patients");
				request.setAttribute("message", "Patient " + person + " information updated");	
				request.setAttribute("list", list2);	
				break;				
			};			
			request.setAttribute("contentpage", "users.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);		
			break;
		
		// add new new user
		
		case "add" : person = new Person();
			person.setFirstName(request.getParameter("fname"));
			person.setLastName(request.getParameter("lname"));
			person.setDate(PersonUtils.getDateFromString(request.getParameter("date")));
			random = new Random();
			person.setId((long) (random.nextDouble() * 9000000000L) + 1000000000L);
			switch (request.getParameter("role")) {
			case "doc" : dao.DAO.addDoctor(person);
				list = dao.DAO.getDoctors();
				request.setAttribute("pagename", "Doctors");
				request.setAttribute("message", "Doctor " + person + " added");	
				request.setAttribute("list", list);
				break;
			case "pat" : dao.DAO.addPatient(person);
				list2 = dao.DAO.getPatients();
				request.setAttribute("pagename", "Patients");
				request.setAttribute("message", "Patient " + person + " added");	
				request.setAttribute("list", list2);
				break;				
			};
			request.setAttribute("contentpage", "users.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);		
			break;
		
		// link doctor patient
		
		case "link" : 
			person1 = dao.DAO.getById(Long.parseLong(request.getParameter("id1")));
			person2 = dao.DAO.getById(Long.parseLong(request.getParameter("id2")));
			dao.DAO.unlinkDoctorPatient(person1, person2);
			if (dao.DAO.isDoctor(person1)) {
				dao.DAO.linkDoctorPatient(person1, person2);
			} else {
				dao.DAO.linkDoctorPatient(person2, person1);
			}
			request.setAttribute("message", "Linked");
			request.setAttribute("user", person1);
			request.setAttribute("contentpage", "user.jsp");
			request.getRequestDispatcher("UserServlet?id=" + request.getParameter("id1")).forward(request, response);
			break;
			
		// unlink doctor and patient
		
		case "unlink" : 
			person1 = dao.DAO.getById(Long.parseLong(request.getParameter("id1")));
			person2 = dao.DAO.getById(Long.parseLong(request.getParameter("id2")));
			dao.DAO.unlinkDoctorPatient(person1, person2);
			request.setAttribute("message", "Unlinked");
			request.setAttribute("user", person1);
			request.setAttribute("contentpage", "user.jsp");
			request.getRequestDispatcher("UserServlet?id=" + request.getParameter("id1")).forward(request, response);	
			break;
			
		// show linkage page
			
		case "linkpage" : 
			person1 = dao.DAO.getById(Long.parseLong(request.getParameter("id1")));
			request.setAttribute("user", person1);
			switch (dao.DAO.getRole(person1)) {
			case "doctor" :
				list2 = dao.DAO.getPatients();
				request.setAttribute("role", "doctor");
				request.setAttribute("list", list2);
				break;
			case "patient" :
				request.setAttribute("role", "patient");
				list = dao.DAO.getDoctors();
				request.setAttribute("list", list);
				break;				
			};
			request.setAttribute("contentpage", "linkpage.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);		
			break;			
			
		default : request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		doGet(request, response);
	}

}
