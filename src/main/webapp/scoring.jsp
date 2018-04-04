<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
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
                       <li><a class="selected" href="userprofile">User Profile</a></li>
                       <li><a class="selected" href="attendance">Attendance List</a></li>
                       <li><a class="selected" href="curriculum">Curriculum Page</a></li>
                       <li><a class="selected" href="logout">Log out</a></li>
                   </ul>
               </div>
               <div id="main">
                   <h2>Scoring Assignment</h2>
                      <table>
                      <tr>
                      <th>Title:${student.email}</th>
                      </tr>
                      <tr>
                      <td>Title:${aPage.title}</td>
                      </tr>
                      <tr>
                      <td>Question:${aPage.question}</td>
                      </tr>
                      <tr>
                       <td>Answer:${aPage.answer}</td>
                      </tr>
                      <tr>
                       <form action="scoring"  method="post" >
                      <td><input type="number" name="actualScore" min="${aPage.minimumScore}" max="${aPage.maxScore}" style="width: 40px" required></td>
                      </tr>
                      <tr>
                      <td><button type="submit" class="submitbtn">Assign</button></td>
                       </form>
                      </tr>
                   </table>
               </div>
           </div>
           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
</body>
</html>