<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
<a href="modules" class="pull-right">Edit Modules</a>
</div>
<c:forEach items="${user.getCollectionList()}" var="collection">
     <c:forEach items="${collection.getModuleList()}" var="module">
         <div class="row">
             <div class="panel panel-info">
                 <div class="panel-heading">
                     ${collection.getName()}
                 </div>
                 <div class="panel-body">
                    <jsp:include page="/WEB-INF/modules/${module.getJsp()}">             
                         <jsp:param name="moduleUser" value="${collection.getOwner().getMail()}"/>
                         <jsp:param name="moduleNodeList" value="${collection.getNodeListAsJson()}"/>
                         <jsp:param name="resolution" value="${param.resolution}"/>
                    </jsp:include>
                 </div>
            </div>
         </div>
    </c:forEach>
</c:forEach>

