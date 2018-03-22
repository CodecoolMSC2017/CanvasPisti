<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Curriculum Page</title>
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
                       <li><a class="selected" href="userprofile">User Profile</a></li>
                        <li><a class="selected" href="attendance">Attendance List</a></li>
                        <li><a class="selected" href="curriculum">Curriculum Page</a></li>
                   </ul>
               </div>
               <div id="main">
                   <h2>Text Page</h2>
                   <form name="createtext" method="post" action="textPage">
                        <br><strong>Title</strong></br>
                        <input type="text" name="title">
                        <textarea name="content"></textarea></br>
                        <input type="submit" value="post">
                   </form>
               </div>
           </div>
           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
</body>
</html>