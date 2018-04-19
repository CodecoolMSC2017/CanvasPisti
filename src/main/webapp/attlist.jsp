<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>attlist</title>
    <link rel="stylesheet" href="att.css">

</head>
<body>
   <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Menu</span>
      <div id="mySidenav" class="sidenav">

          <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>

           <ul>
           <li><a class="selected" href="registeredList">Registered Users</a></li>
           <li><a class="selected" href="userprofile">User Profile</a></li>
           <li><a class="selected" href="check">Attendance List</a></li>
           <li><a class="selected" href="curriculum">Curriculum Page</a></li>
           <li><a class="selected" href="main.jsp">Main Page</a></li>
           <li><a class="selected" href="logout">Log out</a></li>
          </ul>
       </div>

             <div id="main">

           <div id="header">
               <h1>Canvas for Everybody</h1>
           </div>

               <h2>Attendance list</h2>
               <table align="center">
               <thead>
               <tr>
                  <th>Time</th>
               </tr>
               </thead>

               <c:forEach items="${dates}" var="date">
               <tbody>
               <tr>
                <td><a href="modify?date=<c:out value='${date}' />" >${date}</a></td>
               </tr>
               </tbody>
               </c:forEach>
               </table>
               <a href="main.jsp"><input class="MyButton" type="button" style="background-color:green"  value="Go Back"></button></a>
           </div>

           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
       <script src="myscript.js"></script>
</body>
</html>