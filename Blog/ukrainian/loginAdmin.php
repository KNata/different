<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


<title>Блог</title>
</head>
<body>
    <div class = "panel panel-success">
    <div class = "panel-heading">
        <h3 class = "panel-title">Ти є адмін? Доведи!</h3>    <br/>
    </div>
    <fieldset>
    <div class = "panel-body">
        <a href="index.php">Повернутися</a><br/><br/>
        <form action="checklogin.php" method="post" class ="form-inline" role = "form">
    <div class="form-group">
        Логін: <input type="text" name="username" required="required"/> <br/><br/>
        Пароль: <input type="password" name="password" required="required" /> <br/> <br/>
        <input type="submit" value="Увійти"/>
    </form>
</fieldset>
</div>
</div>


</div>
</body>
</html>