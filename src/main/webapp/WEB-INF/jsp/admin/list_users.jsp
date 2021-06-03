<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List users" scope="page" />
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
							<td><fmt:message key="list_users_jsp.table.header.first.name"/></td>
							<td><fmt:message key="list_users_jsp.table.header.last.name"/></td>
							<td><fmt:message key="list_users_jsp.table.header.login"/></td>
							<td><fmt:message key="list_users_jsp.table.header.active"/></td>
							<td><fmt:message key="list_users_jsp.table.header.options"/></td>
						</tr>
						</thead>

						<c:set var="k" value="0"/>
						<c:forEach var="item" items="${accounts}">
							<c:set var="k" value="${k+1}"/>
							<tr>
								<td><c:out value="${k}"/></td>
								<td>${item.firstName}</td>
								<td>${item.lastName}</td>
								<td>${item.login}</td>
								<td>${item.active}</td>
								<td><form id="block" action="controller" method="post">
										<input type="hidden" name="command" value="blockUser"/>
										<input type="hidden" name="userId" value="${item.id}"/>
										<input type="submit" value='<fmt:message key="list_users_jsp.table.button.block"/>'/>
									</form>
									<form id="unblock" action="controller" method="post">
										<input type="hidden" name="command" value="unblockUser"/>
										<input type="hidden" name="userId" value="${item.id}"/>
										<input type="submit" value='<fmt:message key="list_users_jsp.table.button.unblock"/>'/>
									</form>
									<form id="make_order" action="controller" method="post">
										<input type="hidden" name="command" value="viewSettings"/>
										<input type="hidden" name="userId" value="${item.id}"/>
										<input type="submit" value='<fmt:message key="list_users_jsp.table.button.update"/>'/>
									</form>
							</tr>
						</c:forEach>
					</table>


			<%-- CONTENT --%>
			</td>
		</tr>
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
</html>