<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>grades</title>
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

           <div id="header">
               <h1>Canvas for Everybody</h1>
           </div>

               <div id="main">
                   <h2>Grade Solutions</h2>

                     <table align="center">
                     <thead>
                        <th>Student Submiter</th>
                        <th>Assignment Title</th>
                        <th>Actual Score</th>
                        <th>Max Score</th>
                     </thead>
                     <tbody>
                            <c:forEach items="${userassigns}" var="item">
                            <tr>
                            <td><c:out value="${item.email}" /></td>
                            <td><c:out value="${item.title}" /></td>
                            <td><c:out value ="${item.actScore}"/></td>
                            <td><c:out value ="${item.maxScore}"/></td>
                            </tr>
                            </c:forEach>
                     </tbody>
                    </table>
               </div>

           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
       <script src="myscript.js"></script>
</body>
</html>