<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <form method="post">
        <div class="form-group">
            <label for="name">Name</label>
            <input class="form-control" type="text" id="name" name="name" placeholder="Name"/>
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <input class="form-control" type="text" id="username" name="username" placeholder="Username"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input class="form-control" type="password" id="password" name="password" placeholder="Password"/>
        </div>
        <div class="form-group">
            <label for="phone">Phone</label>
            <input class="form-control" type="number" id="phone" name="phone" placeholder="Phone"/>
        </div>
        <c:if test="${param.failed}">
            <div class="alert alert-danger">Please fill out all fields to register</div>
        </c:if>
        <c:if test="${param.taken}">
            <div class="alert alert-danger">Username or phone number already taken, select another</div>
        </c:if>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
    
</div>
