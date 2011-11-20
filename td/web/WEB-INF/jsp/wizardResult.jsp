<%-- 
    Document   : wizardResult
    Created on : 15/11/2011, 06:19:57 AM
    Author     : jose
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1><br/>
        <table> 
            <tr>
        <c:forEach items="${columnas}" var="c" >
            <th>${c}</th>
        </c:forEach>
            <th>Gr. Memb.</th>
        </tr>
        <c:forEach items="${resultado}" var="res" >
        <tr>
            <c:forEach items="${res}" var="valor" >
                <td>${valor.value}</td>
        </c:forEach>
        </tr>
        </c:forEach>
            </table>
    </body>
</html>
