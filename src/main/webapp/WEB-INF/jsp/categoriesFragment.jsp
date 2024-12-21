<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul>
    <c:forEach var="category" items="${categories}">
        <li>${category}</li>
    </c:forEach>
</ul>
