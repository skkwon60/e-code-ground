<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 입력</title>
</head>
<body>
<h1>비밀번호 확인 절차</h1>
<form action='pwdCheck' method='post'>
Email: <input type='text' name='email' value='${log.email}' readonly><br>
Password:  <input type='text' name='password'><br>
<input type='submit' name='제출' value='승인 요청'>
<input type='button' name='취소' value='취소' onclick='location.href=\"/visitlog/mainpage\"'>
</form>
</body>
</html>