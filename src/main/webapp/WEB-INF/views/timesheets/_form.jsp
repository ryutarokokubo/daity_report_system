<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<fmt:parseDate value="${timesheet.attendance_Date}" pattern="yyyy-MM-dd" var="timesheetDay" type="date" />
<label for="${AttributeConst.TIM_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.TIM_DATE.getValue()}" id="${AttributeConst.TIM_DATE.getValue()}" value="<fmt:formatDate value='${timesheetDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label>氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="${AttributeConst.TIM_ATTENDANCE.getValue()}">出勤時間</label><br />
<input type="time" name="${AttributeConst.TIM_ATTENDANCE.getValue()}" id="${AttributeConst.TIM_ATTENDANCE.getValue()}" value="${timesheet.attendance_time}" />
<br /><br />
<label for="${AttributeConst.REP_CONTENT.getValue()}">退勤時間</label><br />
<input type="time" name="${AttributeConst.TIM_LEAVING.getValue()}" id="${AttributeConst.TIM_LEAVING.getValue()}" value="${timesheet.leaving_time}" />
<br /><br />
<input type="hidden" name="${AttributeConst.TIM_ID.getValue()}" value="${timesheet.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">登録</button>