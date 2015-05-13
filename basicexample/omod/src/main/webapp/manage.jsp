<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<p>Hello ${user.systemId}!</p>

<c:forEach var="patient" items="${patients}">
    <tr>
    <td><a href="manage.jsp?patientId=
    ${patient.patientId}">${patient.givenName}</a></td>
        <td>${patient.age</td>
        <td>${patient.gender}</td>
        <td>${patient.identifiers}</td>

    </tr>
</c:forEach>

<%@ include file="/WEB-INF/template/footer.jsp"%>