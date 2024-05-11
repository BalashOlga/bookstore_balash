<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title><%=request.getServletContext().getServerInfo() %></title>
        <link href="bookstore.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <c:if test="${book != null}">
            <h1>Books Info</h1>
            <table>
                <tr>
                <th>Author</th>
                <th>Isbn</th>
                <th>Year</th>
                <th>Cover</th>
                <th>Cost</th>
                <th>Action</th>
                </tr>

                <tr>
                    <th>${book.author}</th>
                    <th>${book.isbn}</th>
                    <th>${book.year}</th>
                    <th>${book.coverType.name()}</th>
                    <th>${book.cost}</th>
                    <th><a href="controller?command=books">All books</a></th>
                </tr>
            </table>
        </c:if>

        <c:if test="${book == null}">
            <h1>The book was not found</h1>
        </c:if>
    </body>
</html>