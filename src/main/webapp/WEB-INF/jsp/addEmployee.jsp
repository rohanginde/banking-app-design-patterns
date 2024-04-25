<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add New Employee</title>
</head>
<body>
    <h1>Add New Employee</h1>
    <form action="${pageContext.request.contextPath}/employees/update" method="post">
        <div>
            <label>Name:</label>
            <input type="text" name="name" />
        </div>
        <div>
            <label>Department:</label>
            <input type="text" name="department" />
        </div>
        <div>
            <label>Salary:</label>
            <input type="text" name="salary" />
        </div>
        <div>
            <input type="submit" value="Add Employee" />
        </div>
    </form>
</body>
</html>
