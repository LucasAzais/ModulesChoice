
<%@ page import="moduleschoice.Module" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-module" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-module" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'module.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="sequence" title="${message(code: 'module.sequence.label', default: 'Sequence')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'module.description.label', default: 'Description')}" />
					
						<th><g:message code="module.headTeacher.label" default="Head Teacher" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${moduleInstanceList}" status="i" var="moduleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${moduleInstance.id}">${fieldValue(bean: moduleInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: moduleInstance, field: "sequence")}</td>
					
						<td>${fieldValue(bean: moduleInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: moduleInstance, field: "headTeacher")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${moduleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>