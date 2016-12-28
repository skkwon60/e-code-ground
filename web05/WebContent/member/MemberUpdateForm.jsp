<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보</title>
</head>
<body><h1>회원정보</h1>
<jsp:useBean id="member2" scope="request" class="spms.vo.Member"></jsp:useBean>
<form action='update' method='post'>
번호: <input type='text' name='no' value='<%=request.getParameter("no")%>' readonly><br>
이름: <input type='text' name='name' value='<%=member2.getName()%>'><br>
이메일: <input type='text' name='email' value='<%=member2.getEmail()%>'><br>
가입일: <%=member2.getCreatedDate()%><br>
<input type='submit' value='저장'>
<input type='button' value='삭제' onclick='location.href=\"delete?no="request.getParameter("no")\"'>
<input type='button' value='취소' onclick='location.href=\"list\"'></form>
</body>
</html>