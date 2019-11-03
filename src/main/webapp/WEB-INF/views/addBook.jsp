<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Add Book</title>
</head>
<body>
	<fieldset>
		<legend>Book Details From</legend>
		<form:form id="bookForm" cssClass="form-horizontal"
			modelAttribute="book" method="post" action="saveBook">
			<table>
				<tr>
					<td><form:label path="title">Name</form:label></td>
					<td><form:hidden path="bookId" /> <form:input
							cssClass="form-control" path="title" /></td>
				</tr>
				<tr>
					<td><form:label path="price" cssClass="control-label col-xs-3">Price</form:label></td>
					<td><form:input cssClass="form-control" path="price" /></td>
				</tr>
				<tr>
					<td><form:label path="volume">Volume</form:label></td>
					<td><form:input cssClass="form-control" path="volume" /></td>
				</tr>
				<tr>
					<td><form:label path="publishDate">Publish Date</form:label></td>
					<td><form:input type="date" path="publishDate"
							class="date-picker" /></td>
				</tr>
				<tr>
					<td><form:label path="subjectId">Subject</form:label></td>
					<td><form:select path="subjectId" items="${subjectsList}"
							itemValue="subjectId" itemLabel="subTitle">
						</form:select></td>
				</tr>
				<tr>
					<td colspan="1">
						<button type="submit" class="btn btn-primary">Submit</button>
					</td>
				</tr>
			</table>
		</form:form>
	</fieldset>
</body>
</html>