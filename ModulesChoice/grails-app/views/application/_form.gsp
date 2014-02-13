<%@ page import="moduleschoice.Application" %>



<div class="fieldcontain ${hasErrors(bean: applicationInstance, field: 'choice', 'error')} required">
	<label for="choice">
		<g:message code="application.choice.label" default="Choice" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="choice" type="number" min="1" max="2" value="${applicationInstance.choice}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationInstance, field: 'preference', 'error')} required">
	<label for="preference">
		<g:message code="application.preference.label" default="Preference" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="preference" type="number" min="1" max="7" value="${applicationInstance.preference}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationInstance, field: 'module', 'error')} required">
	<label for="module">
		<g:message code="application.module.label" default="Module" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="module" name="module.id" from="${moduleschoice.Module.list()}" optionKey="id" required="" value="${applicationInstance?.module?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationInstance, field: 'student', 'error')} required">
	<label for="student">
		<g:message code="application.student.label" default="Student" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="student" name="student.id" from="${moduleschoice.Student.list()}" optionKey="id" required="" value="${applicationInstance?.student?.id}" class="many-to-one"/>
</div>

