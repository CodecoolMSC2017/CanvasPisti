<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link rel="stylesheet" href="index.css">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>jQuery UI Datepicker - Default functionality</title>
      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <link rel="stylesheet" href="/resources/demos/style.css">
      <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      <script>
      $( function() {
        $( "#datepicker" ).datepicker({ minDate:-60 , maxDate: "+0D" });
      } );
      </script>
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
                   <h2>Registered Users</h2>
                    <p1>${loginServlet.name}</p1>
                       <form action="check" method="post">
                   <table>
                       <p>Date: <input type="text" id="datepicker" name="datepicker"></p>
                       <tr>
                           <th>Name</th>
                           <th>Is Here</th>
                       </tr>
                       <c:forEach items="${allusers}" var="user">
                       <c:if test = "${user.role == 'Student'}">
                           <tr>
                               <td><c:out value="${user.name}" /></td>
                               <td><input type="checkbox" name="Was Here" value="${user.name}" <br> </td>
                           </tr>
                          </c:if>
                       </form>
                       </c:forEach>
                   </table>
                       <input type="submit" value="Save">
               </div>
           </div>
           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
</body>
</html>