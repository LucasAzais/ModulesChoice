<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Choice</title>
</head>
<body>
	<h1> Choices</h1>
	<div class="body"></div>
	
	<div class="nav role="navigation">
		<fieldset class="buttons">
			<ul>
				<li>
					<g:form action="frontPageStudent" method="post">
						<input type="submit" value="Return">
					</g:form>
				</li>
				<li>
					<g:form action="seeTendency" method="post">
						<input type="submit" value="See the tendencies">
					</g:form>
				</li>
				<li>
					<g:form action="choicePage" method="post">
						<input type="submit" value="See the descriptions of module">
					</g:form>
				</li>
			</ul>
		</fieldset>
	</div>
	
</body>
</html>