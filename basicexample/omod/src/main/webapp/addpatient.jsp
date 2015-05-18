<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

                <input type="submit" class="btn btn-md btn-success" value="<openmrs:message code="Save"/>" name="save">
            </form>

<label/>First Name: <input type="text" name="firstName"/><br/>
<label/>Middle Name: <input type="text" name="middleName"/><br/>
<label/>Last Name: <input type="text" name="lasttName"/><br/>
<label/>Address: <input type="text" name="address"/><br/>


<%@ include file="/WEB-INF/template/footer.jsp"%>