<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registry</title>
    <p1>${register}</p1>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<form action="register" style="border:1px solid #ccc" method="post">
    <div class="container">
        <h1>Sign Up</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" required>

        <label for="psw"><b>Name</b></label>
        <input type="text" placeholder="Enter Name" name="name" required>

        <form action="">
            <input type="radio" name="role" value="Student"> Student<br>
            <input type="radio" name="role" value="Mentor"> Mentor<br>
        </form>


        <div class="clearfix">
            <button type="button" class="cancelbtn">Cancel</button>
            <button type="submit" class="signupbtn">Sign Up</button>
        </div>

        <a href="login.jsp"><input class="MyButton" type="button" value="Already a user? Log in!" /></a>
    </div>
</form>

</body>
</html>