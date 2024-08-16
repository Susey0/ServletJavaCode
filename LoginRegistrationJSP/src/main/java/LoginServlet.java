

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
	    System.out.println("Username: " + username + ", Password: " + password);

		
		try {
			Class.forName("org.postgresql.Driver");

	        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ktmcentral","postgres","2050");

			String sql = "SELECT * FROM myuser WHERE username=? AND password = ?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2,password);
			rs=stmt.executeQuery();
			
			if(rs.next()) {
				response.sendRedirect("success.jsp");
			}else {
				response.sendRedirect("index.jsp");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
