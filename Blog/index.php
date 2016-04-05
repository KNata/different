<html>
	<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


		<title>Blog</title>
	</head>
	<body>
		<a href="login.php">Login</a> <br/>
		<a href="register.php">Register</a>
	</body>
	<br/>
	<h2 align="center">Posts</h2>
	<table class="table table-bordered">
			<tr>
				<th align="center">Id</th>
				<th align="center">Content</th>
				<th align="center">Date added</th>
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
					Print "</tr>";
				}
                
                
               			?>
	</table>
</html>