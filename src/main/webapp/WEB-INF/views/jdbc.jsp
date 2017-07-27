<%--
  Created by IntelliJ IDEA.
  User: Antonella
  Date: 7/26/17
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>JDBC Demo2 </title>
</head>
<body>
<h1>Northwind DB Results</h1>
<%-- Using JSTL  forEach and out to loop through a list and display items in a table --%>
<table>
    <c:forEach items="${dbResult}" var="list">
        <tr>
            <td><c:out value="${list}"></c:out></td>
        </tr>
    </c:forEach>
</table>



</body>
</html>
