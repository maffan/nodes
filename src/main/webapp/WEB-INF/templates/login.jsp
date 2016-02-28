<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <form method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input class="form-control" type="text" id="username" name="username" placeholder="Username"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input class="form-control" type="password" id="password" name="password" placeholder="Password"/>
        </div>
        <c:if test="${param.failed}">
        <div class="alert alert-danger">Wrong username and/or password</div>
    </c:if>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
    
</div>
