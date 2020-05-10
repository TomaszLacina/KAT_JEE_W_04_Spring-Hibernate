<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>
       <table>
           <tbody>
               <c:forEach items="${books}" var="book">
                    <tr>
                        <td><c:out value = "${book.title    }"/></td>
                        <td><c:out value = "${book.rating}"/></td>
                        <td><c:out value = "${book.description}"/></td>
                        <td><c:out value = "${book.publisher.name}"/></td>
                        <td><c:out value = "${book.authors}"/></td>
                    </tr>
               </c:forEach>
           </tbody>
       </table>
    </body>
</html>