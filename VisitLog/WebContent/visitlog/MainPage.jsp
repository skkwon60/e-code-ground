<%@ page import="vl.vo.Log" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>방명록 목록</h1>
<a href='add'>신규 작성</a>
<jsp:useBean id="loglist" scope="request" class="java.util.ArrayList" type="java.util.ArrayList<vl.vo.Log>"/>
<%for(Log log : loglist) { %>
<%=log.getEmail() %>,<%=log.getBody() %>,<%=log.getCreatedDate() %>,<%=log.getModifiedDate() %>
<a href="login">수정하기</a><br>
<%} %>
<jsp:include page="/Tail.jsp"/>
</body>
</html>