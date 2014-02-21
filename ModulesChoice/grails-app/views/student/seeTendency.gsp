<h1>SeeTendency:</h1>
<p>
	${params.newData}
</p>
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

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

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
</head>
<body style="font-family: Arial; border: 0 none;">
	<div id="visualization" style="width: 600px; height: 400px;"></div>
</body>
</html>