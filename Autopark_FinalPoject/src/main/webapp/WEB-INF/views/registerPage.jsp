<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 1:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="index.css">
    <title>Hello, world!</title>
</head>
<body>
<div class="container-fluid">

    <h3>Welcome to our community</h3>
    <h3>Please properly fill all text boxes. This information will be confidential</h3>

    <form>
        <div class="form-group">
            <label for="inputAddress">Login:</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="Super Driver">
        </div>
        <div class="form-group col-md-6">
            <label for="inputPassword4">Password:</label>
            <input type="password" class="form-control" id="inputPassword4" placeholder="Secret">
        </div>
        <div class="form-group">
            <label for="inputAddress2">Driver ID:</label>
            <input type="text" class="form-control" id="inputAddress2" placeholder="RV 100432">
        </div>
        <div class="form-group">
            <label for="inputAddress">Full name:</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="Ivan Ivanenko">
        </div>
        <div class="form-group">
            <label for="inputAddress2">Email:</label>
            <input type="text" class="form-control" id="inputAddress2" placeholder="my@email.com">
        </div>
        <button type="submit" class="btn btn-primary">Register</button> <button type="button" class="btn btn-secondary">Cancel</button>
    </form>

</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
