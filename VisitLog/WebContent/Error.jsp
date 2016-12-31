<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="3;url=../visitlog/mainpage">
<title>에러 발생</title>
<%String e = (String)request.getAttribute("e"); %>
</head>
<body>
<%if(e == "EmailDupError"){ %>
기존에 존재하는 Email을 입력하였습니다. 기존에 등록한 방명록을 수정해 주세요.<br>
<%} else if(e == "EmailError"){%>
Email이 옳바르지 않습니다. <br>
<%} else if(e == "NullError"){%>
수정해야할 내용의 값을 넘겨받지 못했습니다.<br> 
<%} else if(e == "NoValueError"){%>
이메일, 패스워드, 본문중에 값이 없는 것이 존재합니다.<br>
<%} else if(e == "SpaceInPwd"){%>
패스워드에 공란이 존재합니다.<br>
<%}%>
잠시 후 자동으로 메인 화면으로 이동합니다.<br>
</body>
</html>