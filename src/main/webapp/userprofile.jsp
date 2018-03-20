<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
   <div id="container">
           <div id="header">
               <h1>Canvas for Everybody</h1>
           </div>
           <div id="content">
               <div id="nav">
                   <h3>Navigation</h3>
                   <ul>
                       <li><a class="selected" href="registeredList">Registered Users</a></li>
                       <li><a class="selected" href="main.jsp">Homepage</a></li>
                   </ul>
               </div>
               <div id="main">
                   <h2>User Profile</h2>
                   <form action="edit" method="post">
                         Username:
                         <input type="text" name="name" value="${username}"><br>
                         <p>Current role: ${userrole}</p>
                         <input type="radio" name="role" value="Student"> Student<br>
                         <input type="radio" name="role" value="Mentor"> Mentor<br>
                         <p>Email: ${useremail}</p><br>
                         <input type="submit" value="Submit/Homepage">
                       </form>
               </div>
           </div>
           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
</body>
</html>