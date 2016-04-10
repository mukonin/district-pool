

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		Person person = dao.DAO.getById(id);
		request.setAttribute("pagename", "User: " + person);
		request.setAttribute("user", person);
		if (dao.DAO.getRole(person) == null) {
			request.setAttribute("role", "none");
		} else {
			request.setAttribute("role", dao.DAO.getRole(person));
		}
		request.setAttribute("showcontent", true);
		request.setAttribute("contentpage", "user.jsp");
		ArrayList<Person> list;
		Person doctor = dao.DAO.getDoctor(person);
		list = dao.DAO.getPatients(person);
		request.setAttribute("list", list);
		request.setAttribute("doctor", doctor);
		

		request.getRequestDispatcher("index.jsp").forward(request, response);
		
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
