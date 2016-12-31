<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>신규 방명록 등록</title>
</head>
<script language = "javascript">
function writeCheck(){
	var frm1 =  document.frm1;
	var email = frm1.email.value;
	var password = frm1.password.value;
	var body = frm1.body.value;
	email = email.replace(/^\s*|\s*$/g,'');
	
	var space = /\s/g;
	var email_pattern = /^[a-zA-Z0-9_\.]+@[a-zA-Z0-9]+(\.[a-zA-Z0-9]{2,4}){1,2}$/
	
	if(!email){
		alert("이메일 안적음");
		frm1.email.focus();
		return;
	}
	
	if(email.match(space)){
		alert("이메일에 공백 존재");
		frm1.email.focus();
		return;
	}
	
	if(!email_pattern.test(email)){
		alert("이메일이 틀렸음 재 입력 요구")
		frm1.email.focus();
		return;
	}
	
	if(!password){
		alert("비밀번호 안적음");
		frm1.password.focus();
		return;
	}
	
	if(password.match(space)){
		alert("비밀번호에 공백 존재");
		frm1.password.focus();
		return;
	}
	
	if(!body){
		alert("내용 없음");
		frm1.body.focus();
		return;
	}
	
	frm1.submit();
}
</script>
<body>
<h1>방명록 등록</h1>
<form action='add' method='post' name='frm1'>
Email: <input type='text' name='email'><br>
Password: <input type='password' name='password'><br>
<textarea name='body' cols='50' rows='20' wrap='hard'></textarea><br>
<input type='button' name='등록' value='등록' onclick='javascript:writeCheck()'>
<input type='reset' name='초기화' value='초기화'><br>
</form>
</body>
</html>