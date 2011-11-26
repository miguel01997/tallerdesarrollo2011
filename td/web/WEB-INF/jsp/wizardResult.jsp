<%-- 
    Document   : wizardResult
    Created on : 15/11/2011, 06:19:57 AM
    Author     : jose
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../interfaz/inicio.html" %>
<!DOCTYPE html>
<!--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
    </head>-->
    
<!--    <body>-->
        <div id="content2">
<!--    <div class="inner_copy"><a href="http://www.freetemplatesonline.com/">Free Web Templates</a> <a href="http://www.websitetemplates.org/">Website Templates</a> <a href="http://www.webdesign.org/website-design">Website Design</a></div>-->
    <div class="post">
        <h2>ERROR PostgresSQL NO PUEDE INTERPRETAR ESTA EXPRESION</h2><br/>
        
        <h3>${error}</h3><br/><br/>
        <input type="button" value="Regresar" onclick="window.location='index.htm'" />
    </div>
		
</div>
    <%@include file="../../interfaz/footer.html" %> 

