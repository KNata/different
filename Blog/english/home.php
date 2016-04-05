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
<legend><h2>Home</h2></legend>

<label><p>Hello, <?php Print "$user"?>!</p></label>
<a href="logout.php">Logout</a><br/><br/>
<form action="add.php" method="POST">
<fieldset>
<legend>Add new post:</legend>
<label>Add post:</label>
<input type="text" placeholder="Type something…"name="details">
<span class="help-block">E.g. Today is sunny weather .</span>
public post? <input type="checkbox" name="public[]" value="yes"/><br/>
<input type="submit" value="Add to list"/>
</fieldset>
</form>

<fieldset>
<legend><h3 align="center">My posts</h3><legend>
<label> </label>
<table class="table">
<col width="130">
<col width="80">
<tr>
<th>Id</th>
<th>Details</th>
<th>Post Time</th>
<th>Edit</th>
<th>Delete</th>
<th>Public Post</th>
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
                    Print '<td align="center"><a href="edit.php?id='. $row['id'] .'">edit</a> </td>';
                    Print '<td align="center"><a href="#" onclick="myFunction('.$row['id'].')">delete</a> </td>';
                    Print '<td align="center">'. $row['public']. "</td>";
                    Print "</tr>";
                }
    ?>
</table>


<?php
    
    function shorter($text, $chars_limit) {
        $string = $text;
        $string = strip_tags($string);
        
        if (strlen($string) > 50) {
            
            // truncate string
            $stringCut = substr($string, 0, 50);
            
            // make sure it ends in a word so assassinate doesn't become ass...
            $string = substr($stringCut, 0, strrpos($stringCut, ' ')).'... <a href="/this/story">Read More</a>';
        }
        echo $string;    }
    ?>


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