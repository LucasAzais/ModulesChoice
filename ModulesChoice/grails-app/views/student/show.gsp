
<%@ page import="moduleschoice.Student" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-student" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-student" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list student">
			
				<g:if test="${studentInstance?.surname}">
				<li class="fieldcontain">
					<span id="surname-label" class="property-label"><g:message code="student.surname.label" default="Surname" /></span>
					
						<span class="property-value" aria-labelledby="surname-label"><g:fieldValue bean="${studentInstance}" field="surname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="student.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${studentInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.choices}">
				<li class="fieldcontain">
					<span id="choices-label" class="property-label"><g:message code="student.choices.label" default="Choices" /></span>
					
						<g:each in="${studentInstance.choices}" var="c">
						<span class="property-value" aria-labelledby="choices-label"><g:link controller="application" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${studentInstance?.id}" />
					<g:link class="edit" action="edit" id="${studentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
