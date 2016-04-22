

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Doctor;
import entity.Patient;
import entity.Person;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("id"));
		
		switch (persistence.PersonService.getRoleById(id)) {
		case "patient" :
			Patient patient = persistence.PersonService.getPatientById(id);
			request.setAttribute("user", patient);
			request.setAttribute("contentpage", "patient.jsp");
			break;
		case "doctor" :
			Doctor doctor = persistence.PersonService.getDoctorById(id);
			request.setAttribute("doctor", doctor);
			request.setAttribute("contentpage", "doctor.jsp");
			break;			
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
