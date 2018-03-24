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
                   <h2>Grade Solutions</h2>
                        <table>
                            <th>Student Submiter</th>
                            <th>Assignment Title</th>
                            <th>Answer</th>
                            <th>Score</th>
                            <c:forEach items="${allsubs}" var="entry">
                                <c:forEach items="${entry.value}" var="item">
                                <tr>
                                <td><c:out value="${entry.key.name}"/></td>
                                <td><c:out value="${item.title}" /></td>
                                <td><c:out value="${item.answer}" /></td>
                                <td> score</td>
                                </tr>
                                </c:forEach>
                            </c:forEach>
                        <table>
               </div>
           </div>
           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
</body>
</html>