<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>spring第一个例子(ModelAndView返回方式)</title>
</head>
<body>
	你好，${test},欢迎进入springMVC课堂第一课(ModelAndView返回方式)~~
	
	<c:out value="${test1}"></c:out><br>
	<c:out value="${test2}"></c:out>
</body>
</html>