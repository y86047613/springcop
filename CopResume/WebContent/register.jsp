<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script type="text/javascript" src="js/jquery/jquery-1.11.1.min.js" ></script>


<script type="text/javascript">
	

	
	$(document).ready(function(){
		
		$("[name='username']").change(function(){
		var username=$("[name='username']").val();
			alert(username);
			var url='user/user_name';
			$.post(
					url,
					{'username':username}, 
					function(data){
						if(data.flag){
							$("#msg").html("<font color='green'>"+data.msg+"</font>");
						}
						else{
							$("#msg").html("<font color='red'>"+data.msg+"</font>");
						}
					},
					"json"
			);
			
		});
		
	});
		
</script>

</head>
<body>
	<center>
		用户注册
		
		<form action="user/register" method="post">
			<table>
				<tr>
					<td>
						用户名：
					</td>
					<td>
						<input type="text" name="username" /><font color="red">*</font>
						&nbsp;&nbsp;&nbsp;&nbsp;<span id="msg"></span>
					</td>
				</tr>
				<tr>
					<td>
						密码：
					</td>
					<td>
						<input type="password" name="password" /><font color="red">*</font>
						
					</td>
				</tr>
				
				
				<tr>
					<td>
						邮箱：
					</td>
					<td>
						<input type="text" name="email" />
					</td>
				</tr>
				
				<tr>
					<td>
						性别：
					</td>
					<td>
						<input name="sex" type="radio" value="男"> 男
						<input name="sex" type="radio" value="女"> 女
					</td>
				</tr>
				
				
				<tr>
					<td>
						<input type="submit" value="注册" />
					</td>
					<td>
						<input type="reset"  value="重置"/>
					</td>
				</tr>
				
			</table>
		</form>
		
	</center>
</body>
</html>