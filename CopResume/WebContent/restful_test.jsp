<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="restful/test2/10000" method="post">
		<input type="hidden"  name="_method" value="DELETE"/>
		ename:<input type="text" name="ename" />
		job:<input type="text" name="job" />
		<input type="submit" value="提交">
	</form>

</body>
</html>