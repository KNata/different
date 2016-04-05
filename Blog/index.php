<?php
    if ($_SESSION[lang] == "") {
        $_SESSION[lang] = "en";
        $currLang = "en";
    } else {
        $currLang = $_GET[lang];
        $_SESSION[lang] = $currLang;
    }
    switch($currLang) {
        case "en":
            define("CHARSET","UTF-8");
            define("LANGCODE", "en");
            break;
        case "de":
            define("CHARSET","ISO-8859-1");
            define("LANGCODE", "de");
            break;
        case "ja":
            define("CHARSET","UTF-8");
            define("LANGCODE", "ja");
            break;
        default:
            define("CHARSET","ISO-8859-1");
            define("LANGCODE", "en");
            break;
    }
    header("Content-Type: text/html;charset=".CHARSET);
    header("Content-Language: ".LANGCODE);
?>


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
</body>
<br/>
<fieldset>
<legend><h3 align="center">Posts</h3><legend>
<label> </label>
<div class="table-responsive">

<table class="table" border="1px" width="100%">
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
                    Print '<td align="center">'. $row['details'] . "</td>";
                    Print '<td align="center">'. $row['date_posted']. " - ". $row['time_posted']."</td>";
                    Print "</tr>";
                }
    
    
    ?>
</table>
</html>