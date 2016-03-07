<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Hello ${user.name}</h2>
<p>Collectionlist: ${user.getCollectionList()}</p>
<c:forEach items="${user.getCollectionList()}" var="collection">
    <p>ModuleList: ${collection.getModuleList()}</p>
     <c:forEach items="${collection.getModuleList()}" var="module">
         <p>Module: ${module}</p>
         <p>Collection: ${collection}</p>
         <p>CollectionNodeList: ${collection.getNodeList()}</p>
        <jsp:include page="/WEB-INF/modules/${module.getJsp()}">             
             <jsp:param name="moduleUser" value="${collection.getOwner().getMail()}"/>
             <jsp:param name="moduleCollection" value="${collection.size() > 0 ? collection.get(0) : ''}"/>
        </jsp:include>
    </c:forEach>
</c:forEach>