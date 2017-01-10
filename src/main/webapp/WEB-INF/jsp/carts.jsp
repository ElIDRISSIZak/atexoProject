<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/index.html" method="get" > 
		<input type="submit" value="restart"/>
	</form:form>
	<form:form action="${pageContext.request.contextPath}/getExercice" method="POST" > 
		<input type="submit" value="get question"/>
	</form:form>
	<c:if test="${not empty exerciceToShow}">
		<h1>the exercice we received </h1>
			
				${exerciceToShow}
			
	</c:if>
	<c:if test="${not empty solutionToShow}">
		<h1>the proposed  solution</h1>
				${solutionToShow}
    	<br>
    	<form:form action="${pageContext.request.contextPath}/solveAndSendSolution" method="POST" > 
		<input type="submit" value="solve and send"/>
		</form:form>
	</c:if>
	<c:if test="${not empty status}">
		<h1>the status of the response from service is </h1>
				${status}
    	<br>
	</c:if>
	
</body>
</html>