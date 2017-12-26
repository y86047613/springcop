<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table>
		<thead>
			<tr>
				<th>序号</th><th>员工姓名</th><th>员工职位</th><th>员工经理</th>
				<th>入职时间</th><th>工资</th><th>补助</th><th>部门编号</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${cityList}" var="citymap" varStatus="status">
<%-- 				<tr>${} </tr> --%>
			</c:forEach>
		</tbody>
		
		
	</table>
	
	
</body>
</html>