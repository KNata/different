
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
	<?php
	session_start();
	if($_SESSION['admin']) {
	}
	else{
		header("location:index.php");
	}
	$user = $_SESSION['user'];
	$id_exists = false;
	?>
	<body>
<h2>Кабінет користувача:</h2>
		<p>Привіт, <?php Print "$user"?>!</p>
		<a href="logout.php">Вийти з системи</a><br/>
		<a href="home_admin.php">Повернутися назад</a>
		<h2 align="center">Щойно відзначені</h2>
		<table border="1px" width="100%">
			<tr>
				<th>Порядковий номер</th>
				<th>Ім'я'</th>
				<th>Пароль</th>
			</tr>
			<?php
				if(!empty($_GET['id'])) {
					$id = $_GET['id'];
					$_SESSION['id'] = $id;
					$id_exists = true;
                    mysql_connect("localhost", "root","root") or die(mysql_error()); 					mysql_select_db("first_db") or die("Cannot connect to database");					$query = mysql_query("Select * from users Where id='$id'");
					$count = mysql_num_rows($query);
					if($count > 0) {
						while($row = mysql_fetch_array($query)) {
							Print "<tr>";
								Print '<td align="center">'. $row['id'] . "</td>";
								Print '<td align="center">'. $row['username'] ."</td>";
								Print '<td align="center">'. $row['password']."</td>";
							Print "</tr>";
						}
					}
					else {
						$id_exists = false;
					}
				}
			?>
		</table>
		<br/>
		<?php
		if ($id_exists) {
		Print '
		<form action="editUser.php" method="POST">
            Введіть новий пароль: <input type="text" name="password"/><br/>
			public post? <input type="checkbox" name="public[]" value="yes"/><br/>
			<input type="submit" value="Update Password"/>
		</form>
		';
		}
		else {
			Print '<h2 align="center">There is no data to be edited</h2>';
		}
		?>
	</body>
</html>

<?php
	if($_SERVER['REQUEST_METHOD'] == "POST") {
		mysql_connect("localhost", "root","root") or die(mysql_error());
		mysql_select_db("first_db") or die("Cannot connect to database");
		$details = mysql_real_escape_string($_POST['details']);
		$public = "no";
		$id = $_SESSION['id'];
		

		foreach($_POST['public'] as $list) {
			if($list != null) {
				$public = "yes";
			}
		}
		mysql_query("UPDATE users SET password='$password' WHERE id='$id'") ;

		header("location: home_admin.php");
	}
?>