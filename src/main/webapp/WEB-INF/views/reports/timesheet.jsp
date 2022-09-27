<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actTim" value="${ForwardConst.ACT_TIM.getValue()}" />
<c:set var="commReg" value="${ForwardConst.CMD_REGISTER.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>勤怠入力ページ</h2>
        <form method="POST" action="<c:url value='?action=${actTim}&command=${commReg}' />">
            <c:import url="timesheet_form.jsp" />
        </form>

        <p>
            <a href="<c:url value='?action=Report&command=index' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>