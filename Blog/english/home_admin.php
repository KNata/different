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
    <h3 class = "panel-title"><h2>Home</h2></h3>
    </div>

    <div class = "panel-body">
    <label><p>Hello, <?php Print "$user"?>!</p></label>
    <a href="logout.php">Logout</a><br/><br/>
    <form action="add.php" method="POST">

<fieldset>
    <div class = "panel panel-info">
    <div class = "panel-heading">
        <h3 class = "panel-title">Add new post:</h3>
    </div>
    <br/>
    <div class = "panel-body">
        <label>Add post:</label>
        <input type="text" placeholder="Type something…"name="details">
       <div id = "content"> <span class="help-block">E.g. Today is sunny weather .</span></div>
        public post? <input type="checkbox" name="public[]" value="yes"/><br/>
        <input type="submit" value="Add to list"/>
    </div>
</fieldset>
</form>


<fieldset>
<div class = "panel panel-warning">
<div class = "panel-headin"><h3>Users</h3>
</div>
<div class = "panel-body">
<table class="table">
<col width="100">
<col width="80">
<tr>
    <th>Name</th>
    <th>Edit</th>
    <th>Delete</th>
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
                    Print '<td align="center"><a href="editUser.php?id='. $row['id'] .'">edit</a> </td>';
                    Print '<td align="center"><a href="#" onclick="deleteUser('.$row['id'].')">delete</a> </td>';
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
<h3 class = "panel-title"><h3>Posts</h3></h3>
</div>

<div class = "panel-body">
    <table class="table">
    <tr>
    <th width = 100>Details</th>
    <th width = 30>Post Time</th>
    <th width = 30>Edit</th>
    <th width = 30>Delete</th>
    <th width = 30>Public Post</th>
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
                    Print '<td align="center"><a href="edit.php?id='. $row['id'] .'">edit</a> </td>';
                    Print '<td align="center"><a href="#" onclick="myFunction('.$row['id'].')">delete</a> </td>';
                    Print '<td align="center">'. $row['public']. "</td>";
                    Print "</tr>";
                }
    ?>
</table>
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

function deleteUser(id)
{
    var r = confirm("Are you sure you want to delete this user?");
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
                                          $("#content").text("Your text...");
                                          });
                  $("#content").mouseout(function(){
                                         $("#content").text(v);
                                         });
                  });


</script>
</div>
</body>
</html>