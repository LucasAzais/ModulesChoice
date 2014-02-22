<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>Tendency</title>

<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load('visualization', '1', {
		packages : [ 'corechart' ]
	});
</script>
<script type="text/javascript">
	function drawVisualization() {

		// Create and populate the data table.
		var array = [
                     ["",'${params.moduleList[0]}','${params.moduleList[1]}', '${params.moduleList[2]}'],
                     [ ,${params.data[0]},${params.data[1]}, ${params.data[2]}]
                    ]
		var data = google.visualization.arrayToDataTable(array);


		// Create and draw the visualization.
		new google.visualization.BarChart(document
				.getElementById('visualization')).draw(data, {
			title : "Tendency bar chart",
			width : 600,
			height : 400,
			vAxis : {
				title : "Number of applicants"
			}
		});
	}

	google.setOnLoadCallback(drawVisualization);
</script>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
}

th,td {
	padding: 5px;
}
</style>
</head>
<body style="font-family: Arial; border: 0 none;">
	<h1>Have a look at the tendencies</h1>
	<div align="center">
		<table border="1" style="width: 300px">
			<thead>
				<tr>
					<g:each in="${params.moduleList}">
						<th>
							${it.toString()}
						</th>
					</g:each>
				</tr>
			</thead>
			<tbody>
				<tr>
					<g:each in="${params.data}">
						<td>
							${it.toString()}
						</td>
					</g:each>
				</tr>
			</tbody>
		</table>

		<div id="visualization" style="width: 600px; height: 400px;"></div>
	</div>
	<div class="nav" role="navigation">

		<fieldset class="buttons">
			<ul>
				<li>
					<g:form action="frontPageStudent" method="post">
						<input type="submit" value="Return">
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