<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Hello ${user.name}</h2>
<c:forEach items="${user.getCollectionList()}" var="collection">
     <c:forEach items="${collection.getModuleList()}" var="module">
        <jsp:include page="/WEB-INF/modules/${module.getJsp()}">
             <jsp:param name="moduleUser" value="${collection.getOwner().getMail()}"/>
            <jsp:param name="moduleCollection" value="${collection.getNodeList()}"/>
        </jsp:include>
    </c:forEach>
</c:forEach>