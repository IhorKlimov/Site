<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Database</title>
        <link rel="stylesheet" type="text/css" href="../css/mdb.css">
    </head>
    <body>
        <%@include file="header.jspf"%>
        <section>
            <div class="movie-title">
                <h1>${movie.title}</h1>
                <p>${movie.description}</p>
            </div>

            <c:if test="${!empty user}">
                <c:if test="${!movie.likers.contains(user)}">
                    <form action="like" method="POST">
                        <input type="hidden" name="movieId" value="${movie.movieId}" />
                        <input type="submit" value="Like" />
                    </form>
                </c:if>
                <c:if test="${movie.likers.contains(user)}">
                    <form action="unlike" method="POST">
                        <input type="hidden" name="movieId" value="${movie.movieId}" />
                        <input type="submit" value="Unlike" />
                    </form>
                </c:if>
                <br>
                <form style="width: 100%;" action="comment" method="POST">
                    <div class="username">${user.name}</div>
                    <input type="hidden" name="movieId" value="${movie.movieId}" />
                    <textarea class="boxsizingBorder" name="text"></textarea>
                    <input type="submit" value="Comment" />
                </form> 
            </c:if>
            <hr>
            <c:forEach var="comment" items="${movie.comments}">
                <div class="username">
                    <c:out value="${comment.user.name}"/> ---
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${comment.date}" />
                </div>
                <div class="comment">
                    <c:out value="${comment.text}"/>
                </div>
                <br>
            </c:forEach>
        </section>  
        <%@include file="footer.jspf"%>
    </body>
</html>
