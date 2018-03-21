<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Login LMS</title>

    <link href="index.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="https://fonts.googleapis.com/css?family=Rammetto+One|Rubik+Mono+One" rel="stylesheet">

</head>
<header id="header">
<img src="tweet.jpeg" width="55px" height="55px">
<h1 align="center">LMS Pista</h1>
<p1>${loginServlet}</p1>
</header>

<body>

<h3 id="lh">LOG IN</h3>
            <div class="login" id="login">

                <form name="loginform" method="post" action="loginServlet">
                    </br><strong>Your e-mail</strong></br>
                    <input type="text" name="email">
                    </br>

                    <input class="MyButton" type="submit" value="Log in!" />
                    <a href="index.jsp"><button type="button" value="Go Back">Go Back</button></a>
                </form></br>
            </div>


<footer>
<hr>

<p id="brand">&copy; LMS Pista 2018</p>

</footer>
</body>

</html>
