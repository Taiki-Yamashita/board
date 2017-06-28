<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

function check(){

	if(window.confirm('削除してよろしいですか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行

	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルしました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}
function stop(){

	if(window.confirm('停止してよろしいですか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行

	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルしました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}
function reverse(){

	if(window.confirm('復活してよろしいですか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行

	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルしました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}
</script>
</head>
<body>
<h1>ユーザー管理画面</h1>
<div class="header">
<br/>
<a class="right" href="home">ホーム</a><br/>
<a href="signup">ユーザー登録</a><a class="right" href="./home">戻る</a><br/>
<a class="right" href="logout">ログアウト</a>

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
<c:if test="${ user.department_id == 1 }">
	<br/>
</c:if>
<br/>

<div class="MyTable">
	<div class="row">
	    <div class="cell2">【名前】</div>
	    <div class="cell2">【ログインID】</div>
	    <div class="cell2">【支店】</div>
	    <div class="cell2">【部署】</div>
	    <div class="cell2">【プロフィール】</div>
	    <div class="cell2">【復活or停止】</div>
	    <div class="cell2">【削除】</div>
    </div>

<table>
<c:forEach items="${usermanage}" var="usermanage">

<div class="row">

		<div class="cell"><c:out value="${usermanage.name}" /></div>
		<div class="cell"><c:out value="${usermanage.account}" /></div>
	<c:forEach items="${branches}" var="branch">
		<c:if test="${usermanage.branch_id == branch.id}">
			<div class="cell"><c:out value="${branch.branchName}"/></div>
		</c:if>
	</c:forEach>
	<c:forEach items="${departments}" var="department">
		<c:if test="${usermanage.department_id == department.id}">
			<div class="cell"><c:out value="${department.departmentName}"/></div>
		</c:if>
	</c:forEach>
	<div class="cell"><a href="useredit?id=${usermanage.id }" name="id">編集</a></div>
		<div class="cell"><c:if test="${usermanage.is_stop == false && user.id != usermanage.id}">
		<form action="stop" method="post" onSubmit="return stop()">
			<input type="hidden"value="${usermanage.id}" name="id">
			<input type="hidden"value="true" name="1">
			<input type="submit"name="is_stop"value="停止" >
		</form>
		</c:if>
		<c:if test="${ usermanage.is_stop == true}">
		<form action="stop" method="post" onSubmit="return reverse()">
			<input type="hidden" value="${usermanage.id}" name="id">
			<input type="hidden" value="false" name="0">
			<input type="submit" name="is_stop" value="復活" >
		</form>
		</c:if></div>
		<div class="cell"><c:if test="${ user.department_id == 1 && user.id != usermanage.id}">
		<form action="delete" method="post" onSubmit="return check()">
		<input type="hidden" value="${usermanage.id}" name="id">
			<input type="submit" name="id" value="削除" >
		</form>
		</c:if>
		</div>
		</div>
	</c:forEach>
	</table>
</div>
<br/><br/>





	<div class="copyright">Copyright(c)Taiki Yamashita</div>
</div>
</body>
</html>