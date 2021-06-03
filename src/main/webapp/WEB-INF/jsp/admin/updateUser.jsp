<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Settings" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf" %>

		<tr>
			<td class="content">
				<%-- CONTENT --%>

				<form id="settings_form" action="controller" method="get">
					<input type="hidden" name="command" value="updateUser" />
					<input type="hidden" name="userId" value="${userToUpdate.id}" />

					<div>
						<p>
							<fmt:message key="settings_jsp.label.first_name"/>
						</p>
						<input name="firstName" value="${userToUpdate.firstName}">
					</div>
					
					<div>
						<p>
							<fmt:message key="settings_jsp.label.last_name"/>
						</p>
						<input name="lastName" value="${userToUpdate.lastName}">
					</div>
					
					<input type="submit" value='<fmt:message key="settings_jsp.button.update"/>'><br/>
				</form> 
				
				<%-- CONTENT --%>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
</html>