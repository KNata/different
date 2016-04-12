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

    <div class = "panel panel-info">
    <div class = "panel-heading">
        <h3 class = "panel-title">Кабінет користувача</h3>
    </div>
    <div class = "panel-body">
    <label><p>Привіт, <?php Print "$user"?>!</p></label>
    <a href="logout.php">Вийти з системи</a><br/><br/>

    <div class = "panel panel-success">
    <div class = "panel-heading">
        <h3 class = "panel-title">Додати новий пост</h3>
    </div>

    <div class = "panel-body">
        <form action="add.php" method="POST">
        <fieldset>
            <label>Введіть текст:</label>
            <input type="text" placeholder="Введіть щось…"name="details">
           <div id = "content"> <span class="help-block">Наприклад, Сьогодні чудова погода .</span></div>
            Загальнодоступний пост? <input type="checkbox" name="public[]" value="yes"/><br/>
            <br/>
            <input type="submit" value="Додати"/>
        </fieldset>
        </form>
    </div>
    </div>

    <div class = "panel panel-warning">
    <div class = "panel-heading">
        <h3 class = "panel-title">Мої пости</h3>
    </div>

    <div class = "panel-body">
    <fieldset>
        <table class="table">
        <tr>
        <th width="100">Детальніше</th>
        <th width="30">Час публікації</th>
        <th width="30">Редагувати</th>
        <th width="30">Видалити</th>
        <th width="30">Загальнодоступний пост</th>
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
</div>


<script>
function myFunction(id)
{
    var r = confirm("Are you sure you want to delete this record?");
    if (r == true)
    {
        window.location.assign("delete.php?id=" + id);
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
</div>
</body>
</html>