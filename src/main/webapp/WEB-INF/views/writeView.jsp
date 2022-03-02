<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<html>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	<head>
	 	<title>작성페이지</title>
	 <style type="text/css">
		body {
		  padding-top: 5rem;
		  padding-bottom: 3rem;
		  color: #5a5a5a;
		}
		
		.table{
		  width: 100%;
		  max-width: 1000px;
		  padding: 15px;
		  margin: auto;
		}
	</style>
		
	</head>
	
	<body>
	
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	    <div class="container px-5">
	        <a class="navbar-brand">StudyMatch</a>
	        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
	        <div class="collapse navbar-collapse" id="navbarResponsive">
	            <ul class="navbar-nav ms-auto">
	                <li class="nav-item">
	      <a class="nav-link active" aria-current="page" href="<c:url value='/'/>">홈</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/studyroom/list'/>">스터디</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/listQuiz'/> ">퀴즈</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<c:url value='/mypage.do'/>">마이룸</a>
	    </li>
	    <c:choose>
	    	<c:when test="${!empty user }">
	    		<li class="nav-item">
	    			${user.id}
	    		</li>
	     	<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/logout'/>">로그아웃</a>
	      </li>
	    	</c:when>
	    	<c:otherwise>
	    		<li class="nav-item">
	        <a class="nav-link" href="<c:url value='/login'/>">로그인</a>
	      </li>
	    	</c:otherwise>
	    </c:choose>
	            </ul>
	        </div>
	    </div>
	</nav>
		
		<br>
		
		<div id="root">
			<header>
				<h1>Q&A</h1>
			</header>
			<hr />
			 
			<section id="container">
				<form role="form" method="post" action="<c:url value="/writeView"/>">
					<table class="table table-bordered">
						<tr>
							<th scope="row" class="w-25 p-3">제목</th>
							<td colspan="2"><input type="text" name="title" required></td>
						</tr>
						
						<tr>
							<th scope="row" class="w-25 p-3">작성자</th>
							<td colspan="2">${sessionScope.user.nickname}</td>
							<input type="hidden" name="writer" value="${sessionScope.user.nickname }">
						</tr>
						
						<tr>
							<th scope="row" class="w-25 p-3">내용</th>
								<td colspan="2"><textarea name="content" cols="100" rows="20" required></textarea></td>
						</tr>
					</table>
					
					<div class="col text-center">
							<button type="submit" class="btn-primary me-md-2">등록</button>
							<button type="button" class="btn-primary me-md-2" onClick="location.href='<c:url value="/board/list"/>'">목록</button>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>