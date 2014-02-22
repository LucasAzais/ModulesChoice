<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Front page Teacher</title>
</head>
<body>
	<h1>Welcome to the modules choice application</h1>
	<p>Hoc inmaturo interitu ipse quoque sui pertaesus excessit e vita
		aetatis nono anno atque vicensimo cum quadriennio imperasset. natus
		apud Tuscos in Massa Veternensi, patre Constantio Constantini fratre
		imperatoris, matreque Galla sorore Rufini et Cerealis, quos trabeae
		consulares nobilitarunt et praefecturae.</p>

	<div class="nav" role="navigation">
		<fieldset class="buttons">
			<ul>
				<li>
					<g:form controller="module" action="create" method="post">
						<input type="submit" value="Create a new module">
					</g:form>
				</li>
				<li>
					<g:form controller="module" action="edit" method="post">
						<input type="submit" value="Edit the modules">
					</g:form>
				</li>
				<li>
					<g:form action="ReviewResultsPage" method="post">
						<input type="submit" value="Review the results">
					</g:form>
				</li>
				<li>
					<g:form action="choicePage" method="post">
						<input type="submit" value="Log out">
					</g:form>
				</li>
			</ul>
		</fieldset>
	</div>

</body>
</html>