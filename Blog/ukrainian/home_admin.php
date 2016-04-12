<html>

<head>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


<title>Blog</title>
</head>
<?php
    session_start();
    if($_SESSION['admin']) {
    }
    else{
        header("location:index.php");
    }
    $user = $_SESSION['user'];
    ?>
<body>
    <div class = "panel panel-success">
    <div class = "panel-heading">
    <h3 class = "panel-title"><h2>Кабінет адміна</h2></h3>
    </div>

    <div class = "panel-body">
    <label><p>Hello, <?php Print "$user"?>!</p></label>
    <a href="logout.php">Вийти</a><br/><br/>
    <form action="add.php" method="POST">

<fieldset>
    <div class = "panel panel-info">
    <div class = "panel-heading">
        <h3 class = "panel-title">Додати нову публікацію:</h3>
    </div>
    <br/>
    <div class = "panel-body">
        <label>Add post:</label>
        <input type="text" placeholder="Напиши щось…"name="details">
         <div id = "content"><span class="help-block">Наприклад, сьогодні сонячна погода.</span></div>
        Загальнодостепний пост? <input type="checkbox" name="public[]" value="yes"/><br/>
        <input type="submit" value="Add to list"/>
    </div>
</fieldset>
</form>


<fieldset>
<div class = "panel panel-warning">
<div class = "panel-headin"><h3>Користувачі</h3>
</div>
<div class = "panel-body">
<table class="table">
<col width="100">
<col width="80">
<tr>
<th>Логін</th>
    <th>Редагувати</th>
    <th>Видалити</th>
</tr>
</div>
</fieldset>
<?php
				mysql_connect("localhost", "root","root") or die(mysql_error());
				mysql_select_db("first_db") or die("Cannot connect to database");
				$query = mysql_query("Select * from users");
				while($row = mysql_fetch_array($query)) {
                    Print "<tr>";
                    Print '<td align="center">'. $row['username']."</td>";
                    Print '<td align="center"><a href="editUser.php?id='. $row['id'] .'">редагувати</a> </td>';
                    Print '<td align="center"><a href="#" onclick="deleteUser('.$row['id'].')">видалити</a> </td>';
                    Print "</tr>";
                }
    ?>
</table>
</div>
<br/>
<br/>
<br/>


<fieldset>
<div class = "panel panel-primary">
<div class = "panel-heading">
<h3 class = "panel-title"><h3>Публікації</h3></h3>
</div>

<div class = "panel-body">
    <table class="table">
    <tr>
    <th width = 100>Деталі</th>
    <th width = 30>Час публікації</th>
    <th width = 30>Редагувати</th>
    <th width = 30>Видалити</th>
    <th width = 30>Загальнодоступний пост</th>
    </tr>
</fieldset>
<?php
				mysql_connect("localhost", "root","root") or die(mysql_error());
				mysql_select_db("first_db") or die("Cannot connect to database");
				$query = mysql_query("Select * from list");
				while($row = mysql_fetch_array($query)) {
                    Print "<tr>";
                    Print '<td align="center" <span class="more">'. $row['details']."</span></td>";
                    Print '<td align="center">'. $row['date_posted']. " - ". $row['time_posted']."</td>";
                    Print '<td align="center"><a href="edit.php?id='. $row['id'] .'">редагувати</a> </td>';
                    Print '<td align="center"><a href="#" onclick="myFunction('.$row['id'].')">видалити</a> </td>';
                    Print '<td align="center">'. $row['public']. "</td>";
                    Print "</tr>";
                }
    ?>
</table>
</div>



<script>
function myFunction(id)
{
    var r = confirm("Ти впевнений що хочеш видалити цей запис?");
    if (r == true)
    {
        window.location.assign("delete.php?id=" + id);
    }
}

function deleteUser(id)
{
    var r = confirm("Ти впевнений що хочеш видалити цього користувача?");
    if (r == true)
    {
        window.location.assign("deleteUser.php?id=" + id);
    }
}

$(document).ready(function() {
                  var showChar = 100;
                  var ellipsestext = "...";
                  var moretext = "Show less";
                  var lesstext = "Show more";
                  
                  
                  $('.more').each(function() {
                                  var content = $(this).html();
                                  
                                  if(content.length > showChar) {
                                  
                                  var c = content.substr(0, showChar);
                                  var h = content.substr(showChar, content.length - showChar);
                                  
                                  var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span><span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';
                                  
                                  $(this).html(html);
                                  }
                                  
                                  });
                  
                  $(".morelink").click(function(){
                                       if($(this).hasClass("less")) {
                                       $(this).removeClass("less");
                                       $(this).html(moretext);
                                       } else {
                                       $(this).addClass("less");
                                       $(this).html(lesstext);
                                       }
                                       $(this).parent().prev().toggle();
                                       $(this).prev().toggle();
                                       return false;
                                       });
                  });

$(document).ready(function(v){
                  var v = $("#content").text();
                  
                  $("#content").mouseover(function(){
                                          $("#content").text("Ваш приклад");
                                          });
                  $("#content").mouseout(function(){
                                         $("#content").text(v);
                                         });
                  });

</script>
</div>
</body>
</html>