<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>수정페이지</title>
	</head>
	<script type="text/javascript">
		$(document).ready(function(){
			
			$(".cancel_btn").on("click", function(){
				event.preventDefault();
				location.href = "<c:url value="/writeList"/>";
			})
		})
	
	</script>
	<body>
	
		<div id="root">
			<header>
				<h1> 글 수정</h1>
			</header>
			<hr />
			 
			<section id="container">
				<form name="updateForm" role="form" method="post" action="<c:url value="/update"/>">
					<input type="hidden" name="bno" value="${update.bno}" readonly="readonly"/>
					
					<table class="table table-bordered">
						<tr>
							<th scope="row" class="w-25 p-3">글번호</th>
								<td>${articleVO.bno }</td>
								<input type="hidden" name="num" value="${articleVO.bno }" />
						</tr>
						
						<tr>
					
						<tr>
							<th scope="row" class="w-25 p-3">제목</th>
							<td><input type="text" name="title" value="${update.title }" required></td>
						</tr>
						
						<tr>	
								<th scope="row" class="w-25 p-3">내용</th>
								<td><textarea name="content" cols="100" rows="20" required>${update.content }</textarea></td>
						</tr>
						
						<tr>
							<th scope="row" class="w-25 p-3">등록일</th>
							<td>${update.regdate }</td>
						</tr>
				</table>
		
					<div>
						<button type="submit" class="update_btn">저장</button>
						<button type="submit" class="cancel_btn">취소</button>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>