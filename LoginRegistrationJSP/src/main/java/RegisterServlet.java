

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet("/RegisterServlet")

public class RegisterServlet extends HttpServlet {
	private Connection con = null;
	private PreparedStatement stmt = null;
	
	private static final long serialVersionUID = 1L;
   
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			Class.forName("org.postgresql.Driver");
	        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ktmcentral","postgres","2050");

			String sql = "INSERT INTO myuser (username,password) VALUES (?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2,password);
			int result = stmt.executeUpdate();
			if(result>0) {
				response.sendRedirect("success.jsp");
			}else {
				response.sendRedirect("register.jsp");
			}
		
	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
