<%@ page import="moduleschoice.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'surname', 'error')} required">
	<label for="surname">
		<g:message code="student.surname.label" default="Surname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="surname" required="" value="${studentInstance?.surname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="student.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${studentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'choices', 'error')} ">
	<label for="choices">
		<g:message code="student.choices.label" default="Choices" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${studentInstance?.choices?}" var="c">
    <li><g:link controller="application" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="application" action="create" params="['student.id': studentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'application.label', default: 'Application')])}</g:link>
</li>
</ul>

</div>

