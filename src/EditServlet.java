import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Doctor;
import entity.Patient;
import entity.Person;
import util.PersonValidator;

/**
 * @ author Mukonin Oleksandr
 * 
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch (action) {
		
			//show add new user window
			
		case "new" : request.setAttribute("contentpage", "adduser.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
			//show edit user window
			
		case "edit" : //person = dao.DAO.getById(Long.parseLong(request.getParameter("id")));
			//request.setAttribute("pagename", "Edit User Data: " + person);
			//request.setAttribute("user", person);
			request.setAttribute("contentpage", "edituser.jsp");	
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;	
		};
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		ArrayList<Patient> patientList = new ArrayList<>();
		ArrayList<Doctor> doctorList = new ArrayList<>();
		Doctor doctor;
		Patient patient;	
		Person person;
		Long id;
		
		switch (action) {
		
		// delete user
		
		case "delete" : 
			
			id = Long.parseLong(request.getParameter("id"));
			
			switch (persistence.PersonService.getRoleById(id)) {
			case "p" :				
				patient = persistence.PersonService.getPatientById(id);
				patient.getDoctor().getPatients().remove(patient);
				persistence.PersonService.updateDoctor(patient.getDoctor());
				patient.setDoctor(null);				
				persistence.PersonService.updatePatient(patient);
				persistence.PersonService.deletePatient(patient);
				request.setAttribute("message", "Patient " + patient + " deleted");	
				request.getRequestDispatcher("UsersServlet?action=patients").forward(request, response);
				break;
			case "d" : 				
				doctor = persistence.PersonService.getDoctorById(id);
				for (Patient p : doctor.getPatients()) {
					p.setDoctor(null);
					persistence.PersonService.updatePatient(p);
				}
				doctor.getPatients().clear();
				persistence.PersonService.updateDoctor(doctor);
				persistence.PersonService.deleteDoctor(doctor);
				request.setAttribute("message", "Doctor " + doctor + " deleted");	
				request.getRequestDispatcher("UsersServlet?action=doctors").forward(request, response);
				break;
			};		
			
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
					patient = new Patient(person);
					persistence.PersonService.addPatient(patient);
					request.setAttribute("message", "Patient " + patient + " added");	
					request.getRequestDispatcher("UsersServlet?action=patients").forward(request, response);
					break;
				case "doctor" : 
					doctor = new Doctor(person);
					persistence.PersonService.addDoctor(doctor);
					request.setAttribute("message", "Doctor " + doctor + " added");	
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

			Long id1 = id = Long.parseLong(request.getParameter("id1"));
			Long id2 = id = Long.parseLong(request.getParameter("id2"));
			
			doctor = persistence.PersonService.getDoctorById(id1);
			patient = persistence.PersonService.getPatientById(id2);
			
			doctor.getPatients().add(patient);
			patient.setDoctor(doctor);

			persistence.PersonService.updateDoctor(doctor);
			persistence.PersonService.updatePatient(patient);
			
			request.getRequestDispatcher("UserServlet?id=" + request.getParameter("id1")).forward(request, response);
			
			break;
			
		// unlink doctor and patient
		
		case "unlink" :

			doctor = persistence.PersonService.getDoctorById(Long.parseLong(request.getParameter("id1")));
			patient = persistence.PersonService.getPatientById(Long.parseLong(request.getParameter("id2")));
			
			doctor.getPatients().remove(patient);
			patient.setDoctor(null);
			
			persistence.PersonService.updateDoctor(doctor);
			persistence.PersonService.updatePatient(patient);
			
			request.setAttribute("message", "Unlinked");
			request.getRequestDispatcher("UserServlet?id=" + request.getParameter("id1")).forward(request, response);	
			
			break;
			
		// show linkage page
			
		case "linkpage" :			
			id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("role", persistence.PersonService.getRoleById(id));
			switch (persistence.PersonService.getRoleById(id)) {
			case "patient" :
				patient = persistence.PersonService.getPatientById(id);
				request.setAttribute("user", patient);
				doctorList = persistence.PersonService.getDoctors();
				Collections.sort(doctorList);
				request.setAttribute("list", doctorList);
				break;
			case "doctor" :
				doctor = persistence.PersonService.getDoctorById(id);
				request.setAttribute("user", doctor);
				patientList = persistence.PersonService.getPatients();
				Collections.sort(patientList);
				request.setAttribute("list", patientList);	
				break;
			}
			request.setAttribute("contentpage", "linkpage.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
		default : request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		doGet(request, response);
	}

}
