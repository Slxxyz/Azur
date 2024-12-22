
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul>
    <c:forEach var="category" items="${categories}">

            <a href="<c:url value='/categories/productsByCategory'/>?categoryID=${category.getCategoryID()}&locale=${language}">
                <c:choose>
                    <c:when test="${language == 'fr'}">
                        ${category.getCategoryFR()}
                    </c:when>
                    <c:otherwise>
                        ${category.getCategoryEN()}
                    </c:otherwise>
                </c:choose>
            </a>

    </c:forEach>
</ul>
