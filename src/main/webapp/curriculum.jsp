<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Curriculum Page</title>
    <link rel="stylesheet" href="index.css">
</head>
<header>
<p>${msg}</p>
</header>
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
                        <c:if test = "${userrole == 'Mentor'}">
                            <hr>
                            <li><a class="selected" href="text.jsp">Create Text</a></li>
                            <li><a class="selected" href="assignment.jsp">Create Assignment</a></li>
                        </c:if>
                   </ul>
               </div>
               <div id="main">
                   <h2>Curriculum Page</h2>
                   <c:choose>
                   <c:when test = "${userrole == 'Student'}">
                        Student Curriculum
                        <c:forEach items="${allpages}" var="page">
                        <c:if test = "${page.published == true}">
                        <c:choose>
                          <c:when test = "${page.getClass().name == 'com.codecool.web.model.AssignmentPage'}">
                              <tr>
                                   <td><a href="question?title=<c:out value='${page.title}' />" > <c:out value="${page.title}" /></a></td>
                             </tr>
                          </c:when>
                          <c:otherwise>
                              <tr>
                                   <td><a href="content?title=<c:out value='${page.title}' />" > <c:out value="${page.title}" /></a></td>
                              </tr>
                          </c:otherwise>
                      </c:choose>
                      </c:if>
                   </c:forEach>
                   </c:when>
                   <c:otherwise>
                        <form action="check" method="post">
                        Mentor Curriculum
                        <table>
                        <c:forEach items="${allpages}" var="page">
                          <c:choose>
                              <c:when test = "${page.getClass().name == 'com.codecool.web.model.AssignmentPage'}">
                                  <tr>
                                       <td><a href="question?title=<c:out value='${page.title}' />" > <c:out value="${page.title}" /></a></td>
                                       <td><input type ="checkbox" name="isPublished"<td>
                                 </tr>
                              </c:when>
                              <c:otherwise>
                                  <tr>
                                       <td><a href="content?title=<c:out value='${page.title}' />" > <c:out value="${page.title}" /></a></td>
                                       <td><input type ="checkbox" name="isPublished"<td>
                                  </tr>
                              </c:otherwise>
                          </c:choose>
                   </c:forEach>
                   </table>
                   <input type="submit" value="Publish">
                   </c:otherwise>
                   </c:choose>
               </div>
           </div>
           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
</body>
</html>