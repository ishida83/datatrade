<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
    <title>Login page</title>
	</head>
	<body>
	    <hr>
		<h4>Enter your name</h4> 	
		<s:form action="login">
    	<s:textfield name="username"  label="Username"/>
        <h4>Enter your password</h4> 	
        <s:textfield name="password"  label="Password"/> 
    	<s:submit/>
		</s:form>
	    <hr>	  
	</body>
</html>
  