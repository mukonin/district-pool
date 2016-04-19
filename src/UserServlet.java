

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Person person = dao.DAO.getById(id);
		request.setAttribute("user", person);
		request.setAttribute("role", dao.DAO.getRole(person));		
		Person doctor = dao.DAO.getDoctor(person);
		request.setAttribute("doctor", doctor);		
		ArrayList<Person> list = dao.DAO.getPatients(person);
		request.setAttribute("list", list);
		request.setAttribute("contentpage", "user.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
