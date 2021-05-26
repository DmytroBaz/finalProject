<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<div class="container">
    <form id="login_form" action="controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <div class="mb-3">
            <label for="login" class="form-label"><fmt:message key="login_jsp.label.login"/></label>
            <input name="login" type="text" class="form-control" id="login" >
        </div>
        <div class="mb-3">
            <label for="password" class="form-label"><fmt:message key="login_jsp.label.password"/></label>
            <input name="password" type="password" class="form-control" id="password">
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="login_jsp.button.login"/></button>
    </form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>