<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page isELIgnored="false"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン画面</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>ログイン画面</h1>
<div class="main-contents">

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="login" method="post"><br/>
	<label for="account">アカウント名（ログインID）</label>
	<input name="account" id="account" style="width:400px;height:40px;font-size:20px;" /><c:out value="${account}"/><br/>

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password" style="width:400px;height:40px;font-size:20px;" /><c:out value="${password}"/><br/>

	<label for="is_stop"></label>
	<input name="is_stop" type="hidden" id="is_stop"/><br/>

	<input type="submit" style="width:200px;height:40px;font-size:20px;"value="ログイン" /> <br />
</form>
<br/><br/><br/><br/>
<div class="copyright">Copyright(c)Taiki Yamashita</div>
</div>
</body>
</html>