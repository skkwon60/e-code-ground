<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type='text/css'>
.textbox{
position: relative;}
.textbox input[type='text']{
heigth:auto;
line-height: normal;
word-break:normal;
border: 1px solid #999;
padding:.8em .5em;}</style>
<title>방명록 수정</title>
</head>
<body>
<h1>방명록 수정</h1>
<form action='modify' method='post'>
${sessionScope.log.email}<br>
<div class='textbox'>
<textarea name='body' cols='50' rows='20' wrap='hard'>${sessionScope.log.body}</textarea>
</div>
<input type='hidden' name='no' value='${sessionScope.log.no}'>
<input type='submit' name='변경' value='변경'>
<input type='button' value='방명록 삭제' onclick='location.href="delete?no=${sessionScope.log.no}"'>
</form>
<% session.invalidate(); %>
</body>
</html>