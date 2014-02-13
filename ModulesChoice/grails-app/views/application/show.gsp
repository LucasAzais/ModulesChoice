
<%@ page import="moduleschoice.Application" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-application" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-application" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list application">
			
				<g:if test="${applicationInstance?.choice}">
				<li class="fieldcontain">
					<span id="choice-label" class="property-label"><g:message code="application.choice.label" default="Choice" /></span>
					
						<span class="property-value" aria-labelledby="choice-label"><g:fieldValue bean="${applicationInstance}" field="choice"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationInstance?.preference}">
				<li class="fieldcontain">
					<span id="preference-label" class="property-label"><g:message code="application.preference.label" default="Preference" /></span>
					
						<span class="property-value" aria-labelledby="preference-label"><g:fieldValue bean="${applicationInstance}" field="preference"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationInstance?.module}">
				<li class="fieldcontain">
					<span id="module-label" class="property-label"><g:message code="application.module.label" default="Module" /></span>
					
						<span class="property-value" aria-labelledby="module-label"><g:link controller="module" action="show" id="${applicationInstance?.module?.id}">${applicationInstance?.module?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationInstance?.student}">
				<li class="fieldcontain">
					<span id="student-label" class="property-label"><g:message code="application.student.label" default="Student" /></span>
					
						<span class="property-value" aria-labelledby="student-label"><g:link controller="student" action="show" id="${applicationInstance?.student?.id}">${applicationInstance?.student?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${applicationInstance?.id}" />
					<g:link class="edit" action="edit" id="${applicationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
