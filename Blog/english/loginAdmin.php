

<html>
	<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


    <title>Blog</title>
	</head>
	<body>
<div class="alert alert-success">
<legend><h2>Are you admin?</h2></legend>
<fieldset>
	<a href="index.php">Return</a><br/><br/>
		<form action="checklogin_admin.php" method="post" class ="form-inline" role = "form">

<div class="form-group">
Username: <input type="text" name="username" required="required"/> <br/><br/>
Password: <input type="password" name="password" required="required" /> <br/>
<input type="submit" value="Login"/>


</div>
					</form>
</fieldset>
</div>
	</body>
</html>