<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="modelo.Empleado" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Empresa ABC - Reporte de empleados</title>
</head>
<body>
<h3>LISTADO</h3>
<jsp:useBean id="a_empleado" class="java.util.ArrayList" scope="request"></jsp:useBean>
<table>
	<tr>
		<th>Código</th>
		<th>Apellido</th>
		<th>Nombre</th>
		<th>Sueldo</th>
	</tr>
	<%
		Empleado emp=new Empleado();
		for(int i=0;i<a_empleado.size();i++){
			emp=(Empleado)a_empleado.get(i);
	%>
	<tr>
		<td><%=emp.getCodigo()%></td>
		<td><%=emp.getApellidos()%></td>
		<td><%=emp.getNombres()%></td>
		<td><%=emp.getSueldo()%></td>
	</tr>
	<%}%>
</table>
</body>
</html>