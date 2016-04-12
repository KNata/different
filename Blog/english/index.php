<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

        <div class = "panel panel-success">
        <div class = "panel-heading">
            <h1 class = "panel-title", align = "centr">Welcome to Blog</h1>
        </div>
        <div class = "panel-body">
            <title>Blog</title>
    </head>
    <body>
        <a href="login.php">Login</a>
        <a href="register.php">Register</a>
        <a href="loginAdmin.php">Admin</a>
    <br/>
    <br/>
    <fieldset>
    <div class = "panel panel-info">
    <div class = "panel-heading">
        <h3 class = "panel-title"><h3 align="center">Posts</h3></h3>
    </div>
    <label> </label>
<div class="table-responsive">

    <table class="table">
    <tr>
        <th align="center", width="130">Content</th>
        <th align="center", width="80">Date posted</th>
    </tr>
</fieldset>




<?php
				mysql_connect("localhost", "root","root") or die(mysql_error());
				mysql_select_db("first_db") or die("Cannot connect to database");
				$query = mysql_query("Select * from list Where public='yes'");
				while($row = mysql_fetch_array($query)) {
                    Print "<tr>";
                    Print '<td align="center" width = 200><span class="more">'. $row['title'].$row['details'] . "</span></td>";
                    Print '<td align="center", width = 40>'. $row['date_posted']. " - ". $row['time_posted']."</td>";
                    Print "</tr>";
                }
    
    ?>


</table>
</div>


<script>
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
</script>
</div>
</body>
</html>