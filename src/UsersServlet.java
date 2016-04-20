

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Doctor;
import entity.Person;

/**
 * @ author Mukonin Oleksandr
 * 
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UsersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		ArrayList<Person> personList = new ArrayList<>();
		ArrayList<Doctor> doctorList = new ArrayList<>();
		
		switch (action) {
		case "doctors" :
			request.setAttribute("pagename", "Doctors");
			doctorList = persistence.PersonService.getDoctors();
			Collections.sort(doctorList);
			request.setAttribute("doctorList", doctorList);
			request.setAttribute("contentpage", "doctors.jsp");
			break;
		case "patients" : 
			request.setAttribute("pagename", "Patients");
			personList = persistence.PersonService.getPersons();
			Collections.sort(personList);
			request.setAttribute("personList", personList);	
			request.setAttribute("contentpage", "patients.jsp");
			break;
		}		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
