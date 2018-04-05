<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Curriculum Page</title>
    <link rel="stylesheet" href="att.css">

</head>
<header>
<p>${msg}</p>
</header>
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
            <c:if test = "${userrole == 'Mentor'}">
                <hr>
                <li><a class="selected" href="text.jsp">Create Text</a></li>
                <li><a class="selected" href="assignment.jsp">Create Assignment</a></li>
                <li><a class="selected" href="solutionGrade">Grade Solutions</a></li>
                <li><a class="selected" href="curriculumAtt.jsp">Attendance list</a></li>

            </c:if>
           </ul>
        </div>

           <div id="header">
               <h1>Canvas for Everybody</h1>
           </div>
                <div id="main">
                   <h2>Curriculum Page</h2>
                   <c:choose>
                   <c:when test = "${userrole == 'Student'}">
                        <table align="center">
                        <th>Student Curriculum</th>
                        <th>Type</th>
                        <th>Done</th>
                        <c:forEach items="${allpages}" var="page">
                        <c:if test = "${page.published == true}">
                        <c:choose>
                          <c:when test = "${page.getClass().name == 'com.codecool.web.model.AssignmentPage'}">
                              <tr>
                                   <td><a href="question?title=<c:out value='${page.title}' />" > <c:out value="${page.title}" /></a></td>
                                   <td>Assignment</td>
                                   <td>No</td>
                             </tr>
                          </c:when>
                          <c:otherwise>
                              <tr>
                                   <td><a href="content?title=<c:out value='${page.title}' />" > <c:out value="${page.title}" /></a></td>
                                   <td>Lesson</td>
                                   <td>No Need</td>
                              </tr>
                          </c:otherwise>
                      </c:choose>
                      </c:if>
                   </c:forEach>
                   </table>
                   </c:when>
                   <c:otherwise>
                        <form action="publish" method="post">
                        <table align="center">
                        <th>Mentor Curriculum</th>
                        <th>Type</th>
                        <th>Status</th>
                        <c:forEach items="${allpages}" var="page">
                          <c:choose>
                              <c:when test = "${page.getClass().name == 'com.codecool.web.model.AssignmentPage'}">
                                  <tr>
                                     <c:choose>
                                       <c:when test = "${page.published == false}">
                                       <td><a href="question?title=<c:out value='${page.title}' />" > <c:out value="${page.title}" /></a></td>
                                       <td>Assignment</td>
                                       <td>Unpublished</td>
                                       <td><input type ="checkbox" name="name"value='${page.title}' <td>
                                       </c:when>
                                       <c:otherwise>
                                       <td><a href="question?title=<c:out value='${page.title}' />" > <c:out value="${page.title}" /></a></td>
                                       <td>Assignment</td>
                                       <td>Published</td>
                                       <td><input type ="checkbox" name="name"value='${page.title}' <td>
                                       </c:otherwise>
                                       </c:choose>
                                 </tr>
                              </c:when>
                              <c:otherwise>
                                  <tr>
                                  <c:choose>
                                     <c:when test = "${page.published == false}">
                                       <td><a href="content?title=<c:out value='${page.title}' />" > <c:out value="${page.title}" /></a></td>
                                       <td>Lesson</td>
                                       <td>Unpublished</td>
                                       <td><input type ="checkbox" name="name"value='${page.title}' <td>
                                       </c:when>
                                       <c:otherwise>
                                       <td><a href="content?title=<c:out value='${page.title}' />" > <c:out value="${page.title}" /></a></td>
                                       <td>Lesson</td>
                                       <td>Published</td>
                                       <td><input type ="checkbox" name="name"value='${page.title}' <td>
                                       </c:otherwise>
                                       </c:choose>
                                  </tr>
                              </c:otherwise>
                          </c:choose>
                   </c:forEach>
                   </table>
                    <c:choose>
                    <c:when test = "${empty allpages}">
                    <p1>Nothing to submit yet!</p1>
                    </c:when>
                    <c:otherwise>
                   <input type="submit" value="Publish/UnPublish">
                   </c:otherwise>
                   </c:choose>
                   </c:otherwise>
                   </c:choose>
               </div>
           </div>
           <div id="footer">
               Copyright &copy; 2018 CanvasPisti
           </div>
       </div>
       <script src="myscript.js"></script>
</body>
</html>