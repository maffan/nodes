<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="res" value="${pageContext.request.contextPath}/resources" />
<!--
    This templates iterates over the users active modules. For each module,
    the related JSP gets included and the necessary parameters gets injected.

    Modules are stored in the WEB-INF/modules folder.

    All modules must put all initiation code into the global startupFunctions 
    object. This code will be executed by 'user.js' once all dependencies (for example google
    charts ) have been loaded.

    Ex. example_module.jsp
    <script>
        startupFunctions.push(function(){
            //Do stuff
        })
    </script>
-->
<script>var startupFunctions = [];</script>

<div class="row pad">
    <div class="col-md-12">
        <div class="dropdown pull-left">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
                Resolution
                <span class="caret"></span>
            </button>
            <button class="btn btn-success" onclick="setPause()" id="pauseBtn">Module updates enabled <span class="glyphicon glyphicon-pause" aria-hidden="true"></span></button>
            <ul class="dropdown-menu">
                <li><a href="home?resolution=1">Latest Hour</a></li>
                <li><a href="home?resolution=24">Latest Day</a></li>
                <li><a href="home?resolution=168">Latest Week</a></li>
                <li><a href="home?resolution=672">Latest Month</a></li>
                <li><a href="home?resolution=8064">Latest Year</a></li>
                <li><a href="home">100 Latest Values (Default)</a></li>
            </ul>
        </div>
        <input type="hidden" id="doPause" value="false">
        <a href="modules" class="btn btn-default pull-right">Edit Modules</a>
    </div>
</div>
<c:forEach items="${user.getCollectionList()}" var="collection">
    <c:if test="${collection.getModuleList().size() > 0}">
        <div class="row">
        <div class="panel panel-info">
            <div class="panel-heading">
                ${collection.getName()}
            </div>
            <div class="panel-body">
            <c:forEach items="${collection.getModuleList()}" var="module">
                <h3>${module.getName()}</h3>
                <div>
                    <jsp:include page="/WEB-INF/modules/${module.getJsp()}">             
                        <jsp:param name="moduleUser" value="${collection.getOwner().getMail()}"/>
                        <jsp:param name="moduleNodeList" value="${collection.getNodeListAsJson()}"/>
                        <jsp:param name="resolution" value="${param.resolution}"/>
                        <jsp:param name="collectionId" value="${collection.getId()}"/>
                    </jsp:include>
                </div>
            </c:forEach>
            </div>
        </div>
    </div>
    </c:if>            
</c:forEach>
<script type="text/javascript" src="${res}/js/user.js"></script>
