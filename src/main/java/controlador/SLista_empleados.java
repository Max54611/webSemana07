package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SLista_empleados extends HttpServlet {
	public Connection conn;
	public Statement sen;
	public ResultSet data;
	public String driver="com.mysql.cj.jdbc.Driver";
	public String cadena="jdbc:mysql://localhost:3308/empresa2024";
	public String usuario="root";
	public String clave="";
	
	public void conectar() {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(cadena,usuario,clave);
		}catch(ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Error en el driver");
		}catch(SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error en la conexión");
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conectar();//llamando al método que se conecta a la base de datos
		ArrayList lista=new ArrayList();//se guardará los datos en un arraylist
		try {
			sen=conn.createStatement();
			data=sen.executeQuery("select * from empleado");
			while(data.next()) {                    //mientras exista datos se ejecutará el sgte código
				Empleado emp=new Empleado();
				emp.setCodigo(data.getString(1));
				emp.setApellidos(data.getString(2));
				emp.setNombres(data.getString(3));
				emp.setSueldo(data.getDouble(4));
				lista.add(emp);
			}
		}catch(SQLException e3){
			JOptionPane.showMessageDialog(null, "Error en la conslta");
		}
		
		request.setAttribute("a_empleado", lista);//
		RequestDispatcher rd=request.getRequestDispatcher("listado.jsp");
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	
	
	
}
