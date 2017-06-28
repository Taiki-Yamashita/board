<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ホーム画面</title>
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
</script>
</head>
<body>
<h1>わったい菜掲示板</h1>
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
</div>
<div class="header">
	<c:if test="${empty loginUser}">
		<a href="signup">ユーザー登録</a>
		<a href="login">ログイン</a>
	</c:if>
<c:if test="${not empty loginUser }">
<br/>
		<a href="newpost">新規投稿</a>
	<c:if test="${ user.department_id == 1 }">
		<a href="usermanage">ユーザー管理</a>
	</c:if>
	<a class="right" href="logout">ログアウト</a>
<form action="home" method="get">
<br/>
	カテゴリ：<select name="category" style="width:200px;height:40px;font-size:20px;">
		<option selected value=""><c:out value="選択してください"></c:out></option>
		<c:forEach items="${categories}" var="category">
			 <c:if test="${category.category == categorybox }">
			 	<option selected value="${category.category}">${category.category}</option>
			 </c:if>
			 <c:if test="${category.category != categorybox }">
				<option value="${category.category}">${category.category}</option>
			</c:if>
		</c:forEach>
	</select>
	日付：<input type="date" name="calender" style="width:200px;height:40px;font-size:20px; value="${startcalender}" style="width:150px;">
	<input type="date" name="calender2" style="width:200px;height:40px;font-size:20px; value="${endcalender}" style="width:150px;">
	<input type="submit" value="絞込み" style="width:200px;height:40px;font-size:20px;" >

</form>

<div class="posts">
<c:forEach items="${posts}" var="post">
			<div class="title-text">
			<br/><br/><br/><br/>
				<span class="title"><p1>件名:<c:out value="${post.title }"/></p1></span><br/><br/>
				<span class="text"><p1>投稿内容:</p1><pre><p1><c:out value="${post.text }"/></p1></pre></span>
			</div>
			<span class="category">カテゴリー名:『<c:out value="${post.category}"></c:out>』</span><br/>
			<span class="date">投稿日時:<c:out value="${post.insert_datetime}"></c:out></span><br/>
			<span class="name">投稿者:<c:out value="${post.name}"></c:out></span>
				<br/>
		<c:if test="${ user.department_id != 3 && (user.department_id == 2 || user.id == post.userId) }">
		<br/>
				<form action="delete" method="post" onSubmit="return check()">
					<input type="hidden" value="${post.id}" name="post_id">
					<input type="submit" name="post_id" value="削除" />
				</form>
		</c:if>
		<c:if test="${ user.department_id == 3 && user.branch_id == post.branch_id}">
		<br/>
				<form action="delete" method="post" onSubmit="return check()">
					<input type="hidden" value="${post.id}" name="post_id">
					<input type="submit" name="post_id" value="削除" />
				</form>
		</c:if>
		<c:forEach items="${comments}" var="comment">
			<c:if test="${post.id == comment.postId}">
			<hr>
			<br/><br/>
			<div class="iro" >
			<c:out value="${post.name}"></c:out>
				<div class="name">投稿者：<c:out value="${comment.name}"></c:out></div>
				<div class="comment">コメント：<c:out value="${comment.text}"></c:out></div>
				<div class="insert_datetime">投稿日時：<c:out value="${comment.insert_datetime}"></c:out>
			</div>
			</div>
		<c:if test="${user.department_id == 2 || user.id == comment.userId}">
		<br/>
				<form action="delete" method="post" onSubmit="return check()">
					<input type="hidden" value="${comment.id}" name="comment_id">
					<input type="submit" name="comment_id" value="削除" />

				</form>
		</c:if>
			<hr>
			</c:if>

</c:forEach>
<div class="comments">
			<br>【コメント】（500文字以下）<br/>
	<form action="home" method="post">
		<input type=hidden name="post_id" value="${post.id}" >
		<c:if test="${keepPostId != post.id}">
			<textarea name="comment" rows="5" cols="100" class="comment-box" ><c:out value=""/></textarea>
		</c:if>
		<c:if test="${keepPostId == post.id}">
			<textarea name="comment" rows="5" cols="100" ><c:out value="${keepComment}"/></textarea>
			<c:remove var="keepComment" scope="session"/>
			<c:remove var="keepPostId" scope="session"/>
		</c:if>
		<input type="submit" value="投稿">
	</form>
	</div>
	<br/>
</c:forEach>
</div>
<br/><br/><br/><br/>
<div class="copyright">Copyright(c)Taiki Yamashita</div>
</c:if>
</div>

</body>
</html>