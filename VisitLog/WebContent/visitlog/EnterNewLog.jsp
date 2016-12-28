<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>신규 방명록 등록</title>
</head>
<body>
<h1>방명록 등록</h1>
<form action='add' method='post'>
Email: <input type='text' name='email'><br>
Password: <input type='password' name='password'><br>
내용: <input type='text' name='body'><br>
<input type='submit' name='등록' value='등록'>
<input type='reset' name='초기화' value='초기화'><br>
</form>
</body>
</html>