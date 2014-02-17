<h1> Download the list of applicants for ${fileName}</h1>
<%--<a href="${resource(file:fileName)}"> Download the .xls file</a>--%>
<g:link controller="teacher" action="download" params="[module: fileName]">Download ${fileName}.xls</g:link>