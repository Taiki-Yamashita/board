<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="header">
<br/>
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
<h1>新規投稿画面</h1>
<a class="right" href="./home">ホーム</a><br/>
<a class="right" href="logout">ログアウト</a>
		<form action="newpost" method="post"><p1>件名(50文字以下)<p1/><br/>
			<pre><textarea name="title" rows="1" cols="50" style="width:400px;height:23px;font-size:20px;" class="message-box" ><c:out value="${message.title}"/></textarea></pre>
					<br/><p1>投稿内容(1000文字以下)<p1/>
			<pre><textarea name="text" rows="10" cols="100"style="font-size:20px;" wrap="hard" class="message-box"><c:out value="${message.text}"/></textarea></pre>
			<br><p1>新規カテゴリー名<p1/><br/>
			<input name="category" id="category" style="width:400px;height:40px;font-size:20px;" value="${category.category}"/><c:out value="${category.category}"/><br/>
			<br><p1>カテゴリー名<p1/><br/>
			<select style="width:400px;height:40px;font-size:20px;" name="category2">
				<option value=""><c:out value="選択してください"></c:out></option>

					<c:forEach items="${categories}" var="category">
						 <c:if test="${category.category == category2 }">
							<option selected id="category2" value="${category.category}">${category.category}</option>
						</c:if>
						<c:if test="${category.category != category2 }">
							<option id="category2" value="${category.category}">${category.category}</option>
						</c:if>
					</c:forEach>
			</select>
			<br/><br/><br/>

			<input type="submit"style="width:200px;height:40px;font-size:20px;" value="投稿">
		</form>
</div>
<div class="messages">
	<c:forEach items="${ messages }" var="message">
		<div class="message">
			<div class="account-name">
				<span class="account"><c:out value="${message.account }"/></span>
				<span class="name"><c:out value="${message.name }"/></span>
			</div>
			<div class="text"><c:out value="${message.text}"/></div>
		</div>
	</c:forEach>

</div>
<br/><br/><br/><br/>
<div class="copyright">Copyright(c)Taiki Yamashita</div>

</body>
</html>