<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー編集画面</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>ユーザー編集</h1>

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
<div class="header">
<a class="right" href="./home">ホーム</a><br/>
<a class="right" href="./usermanage">戻る</a><br/>
<a class="right" href="./logout">ログアウト</a>
<form action="useredit" method="post">
	<input type="hidden" value="${editUser.id}" name="id">

	<label for="name"><p1>名前<p1/></label>
	<input name="name" id="name"style="width:400px;height:40px;font-size:20px;"  value="${editUser.name}"/>（10文字以下）<br />

	<label for="account"><p1>ログインID<p1/></label>
	<input name="account" id="account"style="width:400px;height:40px;font-size:20px;"  value="${editUser.account}"/>（半角英数字6文字以上20文字以下）<br />

	<label for="password"><p1>パスワード<p1></label>
	<input name="password" type="password"style="width:400px;height:40px;font-size:20px;"  id="password"/>（半角文字6文字以上255文字以下） <br />

	<label for="password"><p1>パスワード(確認用)<p1/></label>
	<input name="confirmedpassword"style="width:400px;height:40px;font-size:20px;"  type="password" id="password"/> <br />

	<p1>支店<p1/><br/><select style="width:400px;height:40px;font-size:20px;" name="branch_id">
		<c:forEach items="${branches}" var="branch">
			<c:if test="${editUser.branch_id != branch.id}">
				<option value="${branch.id}">${branch.branchName}</option>
			</c:if>
				<c:if test="${editUser.branch_id == branch.id}">
						<option selected value="${branch.id}">${branch.branchName}</option>
				</c:if>
		</c:forEach>
	</select><br/>

	<p1>部署<p1/><br/><select style="width:400px;height:40px;font-size:20px;" name="department_id">
		<c:forEach items="${departments}" var="department">
			<c:if test="${editUser.department_id != department.id}">
				<option value="${department.id}">${department.departmentName}</option>
			</c:if>
				<c:if test="${editUser.department_id == department.id}">
					<option selected value="${department.id}">${department.departmentName}</option>
				</c:if>
		</c:forEach>
	</select><br/>
<br/>
	<input type="submit"style="width:200px;height:40px;font-size:20px;"  value="登録" /> <br />
</form>
<br/><br/><br/><br/>
<div class="copyright">Copyright(c)Taiki Yamashita</div>
</div>
</div>
</body>
</html>
