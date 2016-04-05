<html>
	<head>
		<title>Blog</title>
	</head>
	<body>
		<a href="login.php">Login</a> <br/>
		<a href="register.php">Register</a>
	</body>
	<br/>
	<h2 align="center">Posts</h2>
	<table width="100%" border="1px">
			<tr>
				<th>Id</th>
				<th>Content</th>
				<th>Date added</th>
				<th>Date modified</th>
			</tr>
			<?php
				mysql_connect("localhost", "root","root") or die(mysql_error()); //Connect to server
				mysql_select_db("first_db") or die("Cannot connect to database"); //connect to database
				$query = mysql_query("Select * from list Where public='yes'"); // SQL Query
				while($row = mysql_fetch_array($query))
				{
					Print "<tr>";
						Print '<td align="center">'. $row['id'] . "</td>";
						Print '<td align="center">'. $row['details'] . "</td>";
						Print '<td align="center">'. $row['date_posted']. " - ". $row['time_posted']."</td>";
						Print '<td align="center">'. $row['date_edited']. " - ". $row['time_edited']. "</td>";
					Print "</tr>";
				}
			?>
	</table>
</html>