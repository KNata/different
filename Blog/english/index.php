
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<legend align="center"><h2>Welcome to Blog</h2></legend>



<title>Blog</title>
</head>
<body>
<a href="login.php">Login</a>
<a href="register.php">Register</a>
<a href="loginAdmin.php">Admin</a>
</body>
<br/>
<fieldset>
<legend><h3 align="center">Posts</h3><legend>
<label> </label>
<div class="table-responsive">

<table class="table">
<col width="130">
<col width="80">
<tr>
<th align="center">Content</th>
<th align="center">Date posted</th>



</tr>
</fieldset>




<?php
				mysql_connect("localhost", "root","root") or die(mysql_error()); //Connect to server
				mysql_select_db("first_db") or die("Cannot connect to database"); //connect to database
				$query = mysql_query("Select * from list Where public='yes'"); // SQL Query
				while($row = mysql_fetch_array($query))
				{
                    Print "<tr>";
                    Print '<td align="center">'. $row['title'].$row['details'] . "</td>";
                    Print '<td align="center">'. $row['date_posted']. " - ". $row['time_posted']."</td>";
                    Print "</tr>";
                }
    
    
    function shorter($text, $chars_limit) {
        $string = $text;
        $string = strip_tags($string);
        
        if (strlen($string) > 50) {
            $stringCut = substr($string, 0, 50);
            $string = substr($stringCut, 0, strrpos($stringCut, ' ')).'... <a href="/this/story">Read More</a>';
        }
        echo $string;
    }

    
    ?>
</table>
</html>