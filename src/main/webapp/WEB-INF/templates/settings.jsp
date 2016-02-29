<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="res" value="${pageContext.request.contextPath}/resources" />
<script src="http://crypto-js.googlecode.com/svn/tags/3.0.2/build/rollups/md5.js"></script>
<script src="${res}/js/settings.js"></script>
<div>
    <c:if test="${param.updated}">
        <div class="alert alert-success">Profile updated!</div>
    </c:if>
    <form id="settings" method="post">
        <div class="form-group">
            <label for="name">Name</label>
            <input class="form-control" type="text" id="name" name="name" placeholder="Name" value="${user.name}"/>
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <input class="form-control" type="text" id="username" name="username" placeholder="Username" value="${user.mail}"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input class="form-control" type="password" id="password" name="password" placeholder="Password" value="${user.password}"/>
        </div>
        <div class="form-group">
            <label for="phone">Phone</label>
            <input class="form-control" type="number" id="phone" name="phone" placeholder="Phone" value="${user.phone}"/>
        </div>
        <div class="form-group">
            <label for="hash">Hash</label>
            <input class="form-control" type="text" id="hash" name="hash" placeholder="Hash" value="${user.api}"/>
        </div>
        <c:if test="${param.failed}">
            <div class="alert alert-danger">Please fill out all fields to update</div>
        </c:if>
        <c:if test="${param.taken}">
            <div class="alert alert-danger">Username or phone number already taken, select another</div>
        </c:if>
    </form>
    <button class="btn btn-default" id="genHash">Generate new hash</button>
    <button form="settings"type="submit" class="btn btn-primary">Update settings</button>
    
</div>
