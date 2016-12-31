<%@ page import="vl.vo.Log" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type='text/css'>
.allbox{
width:440px;
heigth:330px;
position:relative;
border:1px solid #000000;
padding-left:20px;
}
.logbox{
width:400px;
height:auto;
position:relative;
background:ghostwhite;
border:5px solid #bbbbbb;
font-size:15px;
color:#0099ff;
padding:10px 10px;
wrap:hard;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>방명록 목록</h1>
<a href='add'>신규 작성</a><br>

<jsp:useBean id="loglist" scope="request" class="java.util.ArrayList" type="java.util.ArrayList<vl.vo.Log>"/>
<%for(Log log : loglist) { %>
<div class=allbox>
<%=log.getEmail() %><br>
<div class=logbox>
<%=log.getBody() %>
</div>
최근 수정 시간 <%=log.getModifiedDate() %>, 등록 시간 <%=log.getCreatedDate() %><br>
<a href="../manage/pwdCheck?no=<%=log.getNo() %>">수정 또는 삭제하기</a>  
</div><br>
<%} %>

<jsp:include page="/Tail.jsp"/>
</body>
</html>