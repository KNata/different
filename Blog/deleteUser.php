<?php
	session_start();
	if($_SESSION['admin']) {
	} else {
		header("location:index.php");
	}

	if($_SERVER['REQUEST_METHOD'] == "GET") {
		mysql_connect("localhost", "root","root") or die(mysql_error());
		mysql_select_db("first_db") or die("Cannot connect to database");
        $id = $_GET['id'];
		mysql_query("DELETE FROM users WHERE id='$id'");
		header("location: home_admin.php");
	}
?>