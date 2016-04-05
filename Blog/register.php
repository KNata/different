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



<title>Blog</title>
</head>
<body>
<legend><h2>Registration</h2></legend>
<fieldset>

<a href="index.php">Return</a><br/><br/>
<form action="register.php" method="post">
Username:   <input type="text" name="username" required="required"/> <br/><br/>
Password:   <input type="password" name="password" required="required" /> <br/>
<br>
<input type="submit" value="Register"/>
</form>
</fieldset>
</body>
</html>

<?php
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        $username = mysql_real_escape_string($_POST['username']);
        $password = mysql_real_escape_string($_POST['password']);
        $bool = true;
        mysql_connect("localhost", "root","root") or die(mysql_error());
        mysql_select_db("first_db") or die("Cannot connect to database");
        $query = mysql_query("Select * from users");
        while($row = mysql_fetch_array($query))
        {
            $table_users = $row['username'];
            if($username == $table_users) {
                $bool = false;
                Print '<script>alert("Username has been taken!");</script>';
                Print '<script>window.location.assign("register.php");</script>'; 		}
        }
        if($bool) {
            mysql_query("INSERT INTO users (username, password) VALUES ('$username','$password')");  		Print '<script>alert("Successfully Registered!");</script>';
            Print '<script>window.location.assign("register.php");</script>';
        }
    }
    ?>