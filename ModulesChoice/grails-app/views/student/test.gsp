<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<h1>Test table</h1>
<style>
table {
	align: center;
	border: 1px solid black;
}

th {
	border: 1px solid black;
	text-align: center;
	background: #fff000;
}

td {
	width: 100px;
	border: 1px solid gray;
	text-align: center;
	background: #ffffff;
}
</style>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
		</script>
<script>
		$(document).ready(function(){
  		$("td").click(function(){
  			$(this).parent().children().css({"background-color":"#cccccc"});
  			$(this).css({"background-color":"#00ff00"});
 		 });
		});
		</script>
</head>
<body>
	<table>
		<tr>
			<g:each var="i" in="${(1..groups.size())}">
				<th colspan="${nbrByGroups[i-1]}">
					${groups[i-1]}
				</th>
			</g:each>
		</tr>
		<%m=0%>
		<g:while test="${m<modules.size()}">
			<g:each var="k" in="${(1..nbrOfSequences) }">
				<tr>
					<g:each var="i" in="${(1..groups.size())}">
						<g:each var="j" in="${(1..nbrByGroups[i-1])}">
							<g:if test="${modules[m].sequence==k}">
								<g:if test="${modules[m].department.equals(groups[i-1])}">
									<td>
										${modules[m].title}
									</td>
									<%m++%>
								</g:if>
								<g:else>
									<td></td>
								</g:else>
							</g:if>
						</g:each>
					</g:each>
				</tr>
			</g:each>
		</g:while>
	</table>
</body>
</html>