<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Welcome on inscription page</h1>
    <form:form id="form" method="POST" action="/firstSpring/hello/userInscription/sendInscription" modelAttribute="currentUser">

        <form:label path="name">Name</form:label>
        <form:input path="name"/>
        <form:errors path="name"/>

        <form:label path="age">Age</form:label>
        <form:input path="age"/>
        <form:errors path="age"/>

        <form:radiobutton path="male" value='true' label="Boy"/>
        <form:radiobutton path="male" value='false' label="Girl"/>

        <form:select path="hobby">
            <form:options items="${hobbies}" itemValue="name" itemLabel="name"/>
        </form:select>

        <form:button>Submit</form:button>
    </form:form>
</body>
</html>
