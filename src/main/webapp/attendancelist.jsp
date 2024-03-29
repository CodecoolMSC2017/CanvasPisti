<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>attendance list</title>
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

               <h2>Attandance List page</h2>
               <c:if test = "${role == 'Mentor'}">
               <form action="modify" method="post">
               <table align="center">
               <thead>
                   <th>Name</th>
                   <th>Modify</th>
               <thead>

               <c:forEach items="${users}" var="user">
               <tbody>
               <tr>
               <td><c:out value="${user.name}"/></td>
               <td><input type="checkbox" name="email" value="${user.email}"</td>
               </tr>
                </tbody>
               </c:forEach>
               </table>
               <input type="submit" value="change">
               </form>

               </c:if>
               <c:if test = "${role == 'Student'}">

                 <table align="center">
                  <th>Name</th>
                  <c:forEach items="${users}" var="user">
                  <tr>
                  <td><c:out value="${user.name}"/></td>
                  </tr>
                  </c:forEach>
                  </table>

                  <a href="main.jsp"><input class="MyButton" button type="button" style="background-color:green"  value="Go Back"></button></a>
                  </c:if>
           </div>

           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
       <script src="myscript.js"></script>
</body>
</html>