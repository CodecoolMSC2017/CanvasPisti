<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link rel="stylesheet" href="att.css">


</head>
<body>
    <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Menu</span>
           <div id="mySidenav" class="sidenav">

               <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>

                <ul>
                <li><a class="selected" href="registeredList">Registered Users</a></li>
                <li><a class="selected" href="userprofile">User Profile</a></li>
                <li><a class="selected" href="attendance">Attendance List</a></li>
                <li><a class="selected" href="curriculum">Curriculum Page</a></li>
                <li><a class="selected" href="logout">Log out</a></li>
               </ul>
            </div>
              <div id="header">
                  <h1>Canvas for Everybody</h1>
              </div>

                  <div id="main">
                   <h2>Assignment page</h2>
                   <table align="center">
                        <tr>
                        <th>Title:${textcontent.title}</th>
                        </tr>
                        <tr>
                        <td>Question:${textcontent.question}</td>
                        </tr>
                        <tr>
                        <td><form action="evaluate" method="post">
                        <input type="text" name="answerAssign" placeholder="Answer"></td>
                        </tr>
                        <tr>
                        <td>Max Score:${textcontent.maxScore}</td>
                        </tr>
                        <c:if test = "${userrole == 'Student' && num == 0}">
                        <tr>
                        <td><button type="submit" class="submitbtn">Assign</button></td>
                        </tr>
                        </c:if>
                   </form>
                  </table>
               </div>

           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
       <script src="myscript.js"></script>
</body>
</html>