<?php
	session_start();
	$username = mysql_real_escape_string($_POST['login']);
	$password = mysql_real_escape_string($_POST['password']);

	mysql_connect("localhost", "root","root") or die(mysql_error());
    mysql_select_db("first_db") or die("Cannot connect to database");
	$query = mysql_query("SELECT * from admin WHERE login='$username'");
    $exists = mysql_num_rows($query);
	$table_users = "";
	$table_password = "";
	if($exists > 0) {
		while($row = mysql_fetch_assoc($query)){
			$table_users = $row['login'];
			$table_password = $row['password'];
		}
		if(($username == $table_users) && ($password == $table_password)) {
				if($password == $table_password) {
					$_SESSION['admin'] = $username;
                    header("location: home_admin.php");
                }
                    
        				
		} else {
			Print '<script>alert("Incorrect Password!");</script>';
			Print '<script>window.location.assign("login_admin.php");</script>';
		}

	} else {
		Print '<script>alert("Incorrect Username!");</script>';
        Print '<script>window.location.assign("login_admin.php");</script>';
	}
?>