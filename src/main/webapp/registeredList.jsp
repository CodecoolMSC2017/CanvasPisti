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
                   </ul>
               </div>
               <div id="main">
                   <h2>Registered Users</h2>
                    <p1>${loginServlet.name}</p1>
                   <table>
                       <tr>
                           <th>Name</th>
                           <th>Email</th>
                           <th>Role</th>
                       </tr>
                       <c:forEach items="${allusers}" var="user">
                           <tr>
                               <td><c:out value="${user.name}" /></td>
                               <td><c:out value="${user.email}" /></td>
                               <td><c:out value="${user.role}" /></td>
                           </tr>
                       </c:forEach>
                   </table>
               </div>
           </div>
           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
</body>
</html>