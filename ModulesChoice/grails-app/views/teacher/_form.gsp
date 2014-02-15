<%@ page import="moduleschoice.Teacher" %>



<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'surname', 'error')} required">
	<label for="surname">
		<g:message code="teacher.surname.label" default="Surname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="surname" required="" value="${teacherInstance?.surname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="teacher.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${teacherInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'modules', 'error')} ">
	<label for="modules">
		<g:message code="teacher.modules.label" default="Modules" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${teacherInstance?.modules?}" var="m">
    <li><g:link controller="module" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="module" action="create" params="['teacher.id': teacherInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'module.label', default: 'Module')])}</g:link>
</li>
</ul>

</div>

