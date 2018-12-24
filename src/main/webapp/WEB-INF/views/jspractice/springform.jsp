<%--
  Created by IntelliJ IDEA.
  User: lan
  Date: 2018/10/22
  Time: 下午3:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<style >
    div.errors {
        background-color: #ffcccc;
        border: 2px solid red;
    }
</style>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<!-- sf:form 会渲染一个HTML<form> 标签，但它也会通过commandName属性构建针对某个模型对象的上下文信息 -->
<!-- 我们将commandName设置为girlFriend。在模型中必须要有一个key为girlFriend的对象，否则的话，不能正常渲染 -->
<!-- https://stackoverflow.com/questions/46989072/org-apache-jasper-jasperexception-web-inf-views-home-jsp-line-25-column/48364974 -->
<!--After version 5, commandName was removed, you should use modelAttribute, instead.
I found it here, https://jira.spring.io/browse/SPR-16037
I simply changed the commandName with modelAttribute.-->
    <sf:form method="post" modelAttribute="user" action="/view/register">
        前女友索引 : <sf:input path="gf.index"/><br/>
        前女友姓名 : <sf:input path="gf.name"/><br/>
        id : <sf:input path="id"/>    <sf:errors path="id" element="div" cssClass="errors"/><br/><br/>
        name : <sf:input path="name" accept-charset="UTF-8"/>      <sf:errors path="name" element="div" cssClass="errors"/><br/>
        email : <sf:input path="email" type="email"/>   <sf:errors path="email" element="div" cssClass="errors"/><br/>
        age : <sf:input path="age"/><br/>
        password : <sf:input path="password"/><br/>
        <input type="submit" value="record"/>
    </sf:form>
</body>
</html>
