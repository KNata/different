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


    <title>Блог</title>
	</head>
	<body>
<div class="alert alert-success">
<legend><h2>Увійдіть</h2></legend>
<fieldset>
	<a href="index.php">Назад</a><br/><br/>
		<form action="checklogin.php" method="post" class ="form-inline" role = "form">

<div class="form-group">
Логін: <input type="text" name="username" required="required"/> <br/><br/>
Пароль: <input type="password" name="password" required="required" /> <br/>
<input type="submit" value="Увійти"/>


</div>
					</form>
</fieldset>
</div>
	</body>
</html>