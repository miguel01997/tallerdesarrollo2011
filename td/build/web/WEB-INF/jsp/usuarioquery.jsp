<%-- 
    Document   : usuarioQuery
    Created on : 08/10/2011, 06:50:22 PM
    Author     : luis
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Consulta </h1>
        Consulta: ${prueba}
        <table>
            <tr>
              <td >nombre</td>
               <td>apellido</td>
               <td>edad</td>
               <td>email</td>
               <td>tipo</td>
            </tr>
           <c:forEach items="${lista}" var="p" >
               <tr>
               <td>${p.nombre}</td>
               <td>${p.apellido}</td>
               <td>${p.edad}</td>
               <td>${p.email}</td>
               <td>${p.tipo}</td>
               </tr> 
           </c:forEach>
        </table> 
        
        
    </body>
</html>
