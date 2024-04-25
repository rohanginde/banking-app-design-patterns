<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
    <h1>Edit Employee</h1>
    <form action="${pageContext.request.contextPath}/employees/update" method="post">
        <input type="hidden" name="id" value="${employee.id}" />
        <div>
            <label>Name:</label>
            <input type="text" name="name" value="${employee.name}" />
        </div>
        <div>
            <label>Department:</label>
            <input type="text" name="department" value="${employee.department}" />
        </div>
        <div>
            <label>Salary:</label>
            <input type="text" name="salary" value="${employee.salary}" />
        </div>
        <div>
            <input type="submit" value="Update Employee" />
        </div>
    </form>
</body>
</html>
