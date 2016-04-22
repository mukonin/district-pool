

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import entity.Doctor;
import entity.Person;
import util.PersonUtils;
import util.PersonValidator;

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
		Person person;
		
		switch (action) {
		
			//show add new user window
			
		case "new" : request.setAttribute("contentpage", "adduser.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
			//show edit user window
			
		case "edit" : person = dao.DAO.getById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("pagename", "Edit User Data: " + person);
			request.setAttribute("user", person);
			request.setAttribute("contentpage", "edituser.jsp");	
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;	
		};
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		ArrayList<Person> personList = new ArrayList<>();
		ArrayList<Doctor> doctorList = new ArrayList<>();
		ArrayList<Person> list = new ArrayList<>();
		Doctor doctor;
		Person person;
		Long id;
		String role;
		Person person1;
		Person person2;
		
		switch (action) {
		
		// delete user
		
		case "delete" : /*id = Long.parseLong(request.getParameter("id"));
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
			request.getRequestDispatcher("index.jsp").forward(request, response);*/
			break;
			
		// update user info
			
		case "update" : /*PersonValidator validator = new PersonValidator(request.getParameter("fname"), 
				request.getParameter("lname"), PersonUtils.getDateFromString(request.getParameter("date")));
				
				if (request.getParameter("id") != null) {
					person.setId(Long.parseLong(request.getParameter("id")));					
				}
			if (validator.isValid()) {
				person = validator.getPerson();
				id = Long.parseLong(request.getParameter("id"));
				person.setId(id);
				dao.DAO.updateUser(person);
				role = dao.DAO.getRole(person);
				switch (role) {
				case "doctor" : 
					list = dao.DAO.getDoctors();
					request.setAttribute("pagename", "Doctors");
					request.setAttribute("message", "Doctor " + person + " information updated");	
					request.setAttribute("list", list);	
					break;
				case "patient" : 
					list2 = dao.DAO.getPatients();
					request.setAttribute("pagename", "Patients");
					request.setAttribute("message", "Patient " + person + " information updated");	
					request.setAttribute("list", list2);	
					break;				
				};
				request.setAttribute("contentpage", "users.jsp");
			} else {
				request.setAttribute("message", "Error updating to DB, error message " + validator.getErrorMessage());
				person = dao.DAO.getById(Long.parseLong(request.getParameter("id")));
				request.setAttribute("pagename", "Edit User Data: " + person);
				request.setAttribute("user", person);
				request.setAttribute("contentpage", "edituser.jsp");
			};*/
			request.getRequestDispatcher("index.jsp").forward(request, response);		
			break;
		
		// add new new user
		
		case "add" : 
			PersonValidator validator = new PersonValidator(request.getParameter("fname")
					, request.getParameter("lname"), request.getParameter("date"));
			if (validator.isValid()) {
				person = validator.getPerson();
				switch (request.getParameter("role")) {
				case "patient" : 
					persistence.PersonService.addPatient(person);
					request.setAttribute("message", "Patient " + person + " added");	
					request.getRequestDispatcher("UsersServlet?action=patients").forward(request, response);
					break;
				case "doctor" : 
					doctor = new Doctor();
					doctor.setPerson(person);
					persistence.PersonService.addDoctor(doctor);
					request.setAttribute("message", "Doctor " + person + " added");	
					request.getRequestDispatcher("UsersServlet?action=doctors").forward(request, response);
					break;
				};		
			} else {
				request.setAttribute("message", "Error adding to DB, error message:\n " + validator.getErrorMessage());
				request.setAttribute("contentpage", "adduser.jsp");
				request.setAttribute("fname", request.getParameter("fname"));
				request.setAttribute("lname", request.getParameter("lname"));
			}			
			request.getRequestDispatcher("index.jsp").forward(request, response);		
			break;
		
		// link doctor patient
		
		case "link" :
			
			/*person = persistence.PersonService.getPersonById(Long.parseLong(request.getParameter("id2")));
			person.setDoctor(null);
			persistence.PersonService.updatePatient(person);
			doctor = persistence.PersonService.getDoctorById(Long.parseLong(request.getParameter("id1")));
			if (doctor.getPatients() == null) {
				doctor.setPatients(new HashSet<Person>());
			}
			//doctor.getPatients().add(person);			
			persistence.PersonService.updateDoctor(doctor);*/
			
			person = persistence.PersonService.getPersonById(Long.parseLong(request.getParameter("id2")));
			doctor = persistence.PersonService.getDoctorById(Long.parseLong(request.getParameter("id1")));
			person.setDoctor(null);
			//person.setDoctor(doctor);
			if (doctor.getPatients() == null) {
				doctor.setPatients(new HashSet<Person>());
			}
			doctor.getPatients().add(person);
			persistence.PersonService.updatePatient(person);
			persistence.PersonService.updateDoctor(doctor);
			
			//person.getDoctor().getPatients().remove(person); // ?? is these operation necessary
			
			//persistence.PersonService.updatePatient(person);
			request.setAttribute("message", "Patient " + person + " added to doctor " + doctor + " list");
			request.getRequestDispatcher("UserServlet?id=" + request.getParameter("id1")).forward(request, response);
			break;
			
		// unlink doctor and patient
		
		case "unlink" : 
			person = persistence.PersonService.getPersonById(Long.parseLong(request.getParameter("id1")));
			doctor = persistence.PersonService.getDoctorById(Long.parseLong(request.getParameter("id2")));
			person.getDoctor().getPatients().remove(person); // ?? is these operation necessary
			doctor.getPatients().add(person);
			persistence.PersonService.updateDoctor(doctor);
			persistence.PersonService.updatePatient(person);
			request.setAttribute("message", "Unlinked");
			request.getRequestDispatcher("UserServlet?id=" + request.getParameter("id1")).forward(request, response);	
			break;
			
		// show linkage page
			
		case "linkpage" :			
			id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("role", persistence.PersonService.getRoleById(id));	
			person = persistence.PersonService.getPersonById(id);
			request.setAttribute("user", person);	
			switch (persistence.PersonService.getRoleById(id)) {
			case "patient" :
				doctorList = persistence.PersonService.getDoctors();
				Collections.sort(doctorList);
				request.setAttribute("list", doctorList);
				break;
			case "doctor" :
				personList = persistence.PersonService.getPatients();
				Collections.sort(personList);
				request.setAttribute("list", personList);	
				break;			
			}
			request.setAttribute("contentpage", "linkpage.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
		//default : request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		doGet(request, response);
	}

}
