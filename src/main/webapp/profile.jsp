<%@ page import="com.savin.bank.src.Client" %>
<%--<jsp:useBean id="client" scope="request" type="javax.xml.stream.util.StreamReaderDelegate"/>--%>
<%--<jsp:useBean id="client" scope="request" type=""/>--%>
<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 6/1/2015
  Time: 7:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <%

  %>
<h1>Welcome , <%=((Client)request.getServletContext().getAttribute("client")).getName()%>!</h1>

</body>
</html>
