<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<h1>Test table</h1>
		<style>
		table
		{
			align : center;
			border : 1px solid black;
		}
		th
		{
			border : 1px solid black;
			background : #fff000;
		}
		td
		{
			border : 1px solid gray;
			background : #ffffff;
		}
		</style>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
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
				<th>MIN</th>
				<th>MAS</th>
			</tr>
			<g:each var="i" in="${(1..nbrOfSequences) }">
				<tr>
				<g:each in="${modules}" var="mod">
					<g:if test="${i==mod.sequence}">
						<td>${mod.title}</td>
					</g:if>
				</g:each>
				</tr>
			</g:each>
		</table>		
	</body>
</html>