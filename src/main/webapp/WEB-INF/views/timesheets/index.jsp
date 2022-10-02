<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actTim" value="${ForwardConst.ACT_TIM.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>勤怠　一覧</h2>
        <table id="timesheet_list">
        <tbody>
                <tr>
                    <th class="timesheet_name">氏名</th>
                    <th class="timesheet_date">日付</th>
                    <th class="timesheet_attendance">出勤時間</th>
                    <th class="timesheet_leaving">退勤時間</th>
                </tr>
                <c:forEach var="timesheet" items="${timesheets}" varStatus="status">
                    <fmt:parseDate value="${timesheet.attendance_Date}" pattern="yyyy-MM-dd" var="timesheetDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="timesheet_name"><c:out value="${timesheet.employee.name}" /></td>
                        <td class="timesheet_date"><fmt:formatDate value='${timesheetDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="timesheet_attendance">${timesheet.attendance_time}</td>
                        <td class="timesheet_leaving">${timesheet.leaving_time}</td>
                    </tr>
                </c:forEach>
                 </tbody>
        </table>

        <div id="pagination">
            （全 ${timesheets_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((timesheets_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actTim}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
                </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actTim}&command=${commNew}' />">勤怠の登録</a></p>

    </c:param>
</c:import>