<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="label" required="true" type="java.lang.String" %>
<%@ attribute name="name" required="true" type="java.lang.String" %>
<%@ attribute name="errorMap" required="true" type="java.util.Map" %>

<tr>
  <td>
    ${label}:
  </td>
  <td>
    <label>
      <input name="${name}"/>
    </label>
    <c:if test="${not empty errorMap[name]}">
      <span class="errorMess">
          ${errorMap[name]}
      </span>
    </c:if>
  </td>
</tr>