<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Subjects</title>
</head>
<body>
	<h2>Subjects</h2>
	<c:if test="${empty subjectsList}">
	        There are no Subjects
	</c:if>
	<c:if test="${not empty subjectsList}">
		<form action="/subjects/searchSubject">
			<div class="row">
				<div>
					<div>Search Subjects:</div>
					<div>
						<input type="text" name="searchName" id="searchName">
					</div>
				</div>
				<div>
					<input class="btn btn-success" type='submit' value='Search' />
				</div>
			</div>
		</form>
		<form action="/subjects/searchSubjectByDuration">
			<div class="row">
				<div>
					<div>Search By Duration:</div>
					<div>
						<input type="text" name="searchDuration" id="searchDuration">
					</div>
				</div>
				<div>
					<input class="btn btn-success" type='submit' value='Search By Duration' />
				</div>
			</div>
		</form>
		<table border="1">
			<tr>
				<th>Subject ID</th>
				<th>Subject Name</th>
				<th>Duration</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${subjectsList}" var="subject">
				<tr>
					<td><c:out value="${subject.subjectId}" /></td>
					<td><c:out value="${subject.subTitle}" /></td>
					<td><c:out value="${subject.duration}" /></td>
					<td align="center"><a
						href="<c:url value='/subjects/deleteSubject/${subject.subjectId}' />">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>