<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                   <h2>Assignment page</h2>
                   <table>
                        <tr>
                        <th>Title:${textcontent.title}</th>
                        </tr>
                        <tr>
                        <td>Question:${textcontent.question}</td>
                        </tr>
                        <tr>
                        <td><form action="evaluate" method="post">
                        <input type="text" name="answerAssign" placeholder="Answere"></td>
                        </tr>
                        <tr>
                        <td>Max Score:${textcontent.maxScore}</td>
                        </tr>
                        <c:if test = "${userrole == 'Student'}">
                        <tr>
                        <td><button type="submit" class="submitbtn">Assign</button></td>
                        </tr>
                        </c:if>
                   </form>
                  </table>
               </div>
           </div>
           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
</body>
</html>