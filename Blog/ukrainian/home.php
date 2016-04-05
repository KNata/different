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
<?php
    session_start();
    if($_SESSION['user']) {
    }
    else{
        header("location:index.php");
    }
    $user = $_SESSION['user'];
    ?>
<body>
<legend><h2>Кабінет користувача</h2></legend>

<label><p>Привіт, <?php Print "$user"?>!</p></label>
<a href="logout.php">Вийти з системи</a><br/><br/>
<form action="add.php" method="POST">
<fieldset>
<legend>Додати новий пост:</legend>
<label>Введіть текст:</label>
<input type="text" placeholder="Введіть щось…"name="details">
<span class="help-block">Наприклад, Сьогодні чудова погода .</span>
Загальнодоступний пост? <input type="checkbox" name="public[]" value="yes"/><br/>
<input type="submit" value="Додати"/>
</fieldset>
</form>

<fieldset>
<legend><h3 align="center">Мої пости</h3><legend>
<label> </label>
<table class="table">
<col width="130">
<col width="80">
<tr>
<th>Порядковий номер</th>
<th>Детальніше</th>
<th>Час публікації</th>
<th>Редагувати</th>
<th>Видалити</th>
<th>Загальнодоступний пост</th>
</tr>
</fieldset>
<?php
				mysql_connect("localhost", "root","root") or die(mysql_error());
				mysql_select_db("first_db") or die("Cannot connect to database");
				$query = mysql_query("Select * from list");
				while($row = mysql_fetch_array($query)) {
                    Print "<tr>";
                    Print '<td align="center">'. $row['id'] . "</td>";
                    Print '<td align="center" >'. $row['details']."</td>";
                    Print '<td align="center">'. $row['date_posted']. " - ". $row['time_posted']."</td>";
                    Print '<td align="center"><a href="edit.php?id='. $row['id'] .'">редагувати</a> </td>';
                    Print '<td align="center"><a href="#" onclick="myFunction('.$row['id'].')">видалити</a> </td>';
                    Print '<td align="center">'. $row['public']. "</td>";
                    Print "</tr>";
                }
    ?>
</table>



<script>
function myFunction(id)
{
    var r = confirm("Are you sure you want to delete this record?");
    if (r == true)
    {
        window.location.assign("delete.php?id=" + id);
    }
}


</script>
</body>
</html>