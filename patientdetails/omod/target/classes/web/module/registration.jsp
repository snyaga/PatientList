<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<form method="POST" action="<c:url value='/module/patientdetails/registration.form' />"
<label>First Name</label>
<input type="text" name="firstName"/><br/>
Middle Name:
<input type="text" name="middleName"/><br/>
Last Name:
<input type="text" name="lastName"/><br/>
Date of Birth:
<input type="date" name="dateOfBirth"/><br/>
Gender:
<input type="text" name="gender"/><br/>
Address:
<input type="text" name="address"/><br/>
National ID:
<input type="text" name="nationalId"/><br/>

<input type="submit" value="Add"/>
</form>



<%@ include file="/WEB-INF/template/footer.jsp"%>