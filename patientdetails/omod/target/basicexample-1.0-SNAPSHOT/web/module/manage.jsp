
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<p>Hello ${user.systemId}!</p>

<c:forEach var="patient" items="${patientList}">
    <tr>
        <td>${patient.age}</td>
        <td>${patient.gender}</td>
        <td>${patient.identifiers}</td>


    </tr>
</c:forEach>

<%@ include file="/WEB-INF/template/footer.jsp"%>