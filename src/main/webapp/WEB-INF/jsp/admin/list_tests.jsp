<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="All tests" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

	<%@ include file="/WEB-INF/jspf/header.jspf" %>

	<tr>
		<td class="content">
			<%-- CONTENT --%>
			<table id="list_menu_table">
				<thead>
				<tr>
					<td>№</td>
					<td><fmt:message key="list_menu_jsp.table.header.name"/></td>
					<td><fmt:message key="list_menu_jsp.table.header.complex"/></td>
					<td><fmt:message key="list_menu_jsp.table.header.subject"/></td>
					<td><fmt:message key="list_menu_jsp.table.header.duration"/></td>
				</tr>
				</thead>

				<c:set var="k" value="0"/>
				<c:forEach var="item" items="${tests}">
					<c:set var="k" value="${k+1}"/>
					<tr>
						<td><c:out value="${k}"/></td>
						<td>${item.name}</td>
						<td>${item.complexity}</td>
						<td>${item.subjectId}</td>
						<td>${item.decisionTime}</td>
						<td><form id="make_order" action="controller">
							<input type="hidden" name="command" value="makeOrder"/>
							<input type="submit" value='<fmt:message key="list_users_jsp.table.button.update"/>'/>
						</form>
							<form id="make_order" action="controller">
								<input type="hidden" name="command" value="makeOrder"/>
								<input type="submit" value='<fmt:message key="list_menu_jsp.button.delete"/>'/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>



			<%-- CONTENT --%>
		</td>
	</tr>

	<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
