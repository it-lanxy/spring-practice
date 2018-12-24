<%--
  Created by IntelliJ IDEA.
  User: lan
  Date: 2018/10/22
  Time: 下午7:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ page session="false"%>
<html>
<head>
    <title>Apache Tiles Practice</title>
    <%--<link rel="stylesheet"
        type="text/css"
        href="<s:url value="/resources/style.css">"/>--%>
</head>
<body>
    <div id="header">
        <t:insertAttribute name="header"/>
    </div>

    <div id="body">
        <t:insertAttribute name="body"/>
    </div>

    <div id="footer">
        <t:insertAttribute name="footer"/>
    </div>
</body>
</html>
