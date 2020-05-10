<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>
       <table>
           <tbody>
               <c:forEach items="${authors}" var="author">
                    <tr>
                        <td><c:out value = "${author.name    }"/></td>
                        <td><c:out value = "${author.bookList}"/></td>
                    </tr>
               </c:forEach>
           </tbody>
       </table>
    </body>
</html>