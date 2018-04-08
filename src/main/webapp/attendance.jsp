<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link rel="stylesheet" href="att.css">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>jQuery UI Datepicker - Default functionality</title>
      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <link rel="stylesheet" href="/resources/demos/style.css">

</head>
<body>
   <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Menu</span>
       <div id="mySidenav" class="sidenav">

           <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>

            <ul>
            <li><a class="selected" href="registeredList">Registered Users</a></li>
            <li><a class="selected" href="userprofile">User Profile</a></li>
            <li><a class="selected" href="curriculumAtt.jsp">Attendance List</a></li>
            <li><a class="selected" href="curriculum">Curriculum Page</a></li>
            <li><a class="selected" href="main.jsp">Main Page</a></li>
            <li><a class="selected" href="logout">Log out</a></li>
           </ul>
        </div>

           <div id="main">
              <div id="header">
                  <h1>Canvas for Everybody</h1>
              </div>
                   <h2>Registered Users</h2>
                    <p1>${loginServlet.name}</p1>
                       <form action="check" method="post">
                   <table align="center">
                       <p>Date: <input type="text" id="datepicker" name="datepicker"></p>
                       <thead>
                       <tr>
                           <th>Name</th>
                           <th>Is Here</th>
                       </tr>
                       </thead>
                       <c:forEach items="${allusers}" var="user">
                       <c:if test = "${user.role == 'Student'}">
                       <tbody>
                           <tr>
                               <td><c:out value="${user.name}" /></td>
                               <td><input type="checkbox" name="Was Here" value="${user.name}" <br> </td>
                           </tr>
                       </tbody>
                          </c:if>
                       </form>
                       </c:forEach>
                   </table>
                       <input type="submit" value="Save">
               </div>

           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
         <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
         <script>
         $( function() {
           $( "#datepicker" ).datepicker({ minDate:-60 , maxDate: "+0D" });
         } );
         </script>
       <script src="myscript.js"></script>
</body>
</html>