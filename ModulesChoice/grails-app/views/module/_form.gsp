<%@ page import="moduleschoice.Module" %>



<div class="fieldcontain ${hasErrors(bean: moduleInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="module.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${moduleInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleInstance, field: 'sequence', 'error')} required">
	<label for="sequence">
		<g:message code="module.sequence.label" default="Sequence" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="sequence" type="number" min="1" max="7" value="${moduleInstance.sequence}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="module.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${moduleInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleInstance, field: 'applications', 'error')} ">
	<label for="applications">
		<g:message code="module.applications.label" default="Applications" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${moduleInstance?.applications?}" var="a">
    <li><g:link controller="application" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="application" action="create" params="['module.id': moduleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'application.label', default: 'Application')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: moduleInstance, field: 'headTeacher', 'error')} required">
	<label for="headTeacher">
		<g:message code="module.headTeacher.label" default="Head Teacher" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="headTeacher" name="headTeacher.id" from="${moduleschoice.Teacher.list()}" optionKey="id" required="" value="${moduleInstance?.headTeacher?.id}" class="many-to-one"/>
</div>

