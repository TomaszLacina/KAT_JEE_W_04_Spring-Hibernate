<%@ taglib prefix="form"
    uri="http://www.springframework.org/tags/form" %>

<html>
    <body>
        <form:form method="POST" modelAttribute="book">
            <form:input path="title"/>
            <form:errors path="title"/>

            <form:input path="rating"/>
            <form:errors path="rating"/>

            <form:input path="description"/>
            <form:errors path="description"/>

            <form:select path="publisher" itemLabel="name" itemValue="id" items="${publishers}" />
            <form:errors path="publisher"/>

           <input type="submit">
        </form:form>
    </body>
</html>


