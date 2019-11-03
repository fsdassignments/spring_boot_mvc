<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books</title>
</head>
<body>
	<h2>Books</h2>
	<c:if test="${empty booksList}">
	        There are no books
	</c:if>
	<c:if test="${not empty booksList}">
		<form action="/books/searchBook">
			<div class="row">
				<div>
					<div>Search Books:</div>
					<div>
						<input type="text" name="searchName" id="searchName">
					</div>
				</div>
				<div>
					<input class="btn btn-success" type='submit' value='Search' />
				</div>
			</div>
		</form>
		<table border="1">
			<tr>
				<th>Book ID</th>
				<th>Book Name</th>
				<th>Price</th>
				<th>Volume</th>
				<th>Published Date</th>
				<th>Subject</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${booksList}" var="book">
				<tr>
					<td><c:out value="${book.bookId}" /></td>
					<td><c:out value="${book.title}" /></td>
					<td><c:out value="${book.price}" /></td>
					<td><c:out value="${book.volume}" /></td>
					<td><c:out value="${book.publishDate}" /></td>
					<td>
						<div>
							<select id="subjects" name="Subjects">
								<option value="top">Select subject</option>
								<c:forEach items="${subjectsList}" var="subject">
									<option value="${subject.subjectId}"
										${not empty book.subject.subjectId && book.subject.subjectId == subject.subjectId ? 'selected' : ''}>${subject.subTitle}</option>
								</c:forEach>
							</select>
						</div>
					</td>
					<td align="center"><a href="<c:url value='/books/deleteBook/${book.bookId}' />">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<ul>
		<li><a href="/books/createBook">Add Book</a></li>
		<li><a href="/books/exportBooks">Export Books</a></li>
	</ul>
</body>
</html>