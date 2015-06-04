<%@ page import="com.savin.bank.src.Client" %>
<%@ page import="com.savin.bank.src.Account" %>
<%@ page import="java.util.Map" %>
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
<p>Client : ${client.name}</p>
<%Client client = (Client)request.getSession().getAttribute("client");
                 Map<String, Account> map =  client.getAccountsList();
response.getWriter().write("<h1>Welcome ,"+client.getName()+"!</h1>\n" +

"<table border=\"1\">\n" +
  " <tr>\n" +
    " <th>Account number</th>\n" +
    " <th>Sum</th>\n" +
    " <th>Currency</th>\n" +
    " <th>Type</th>\n" +
    " </tr>");
  for (Map.Entry<String, Account> entry : map.entrySet()) {
    String key = entry.getKey();
    Account value = entry.getValue();
    response.getWriter().write("<tr>\n" +
            "<td>"+key+"</td>\n" +
            "<td>"+value.getBalance()+"</td>\n" +
            "<td>"+value.getCurrency().getType()+"</td>\n" +
            "<td>"+value.getType()+"</td>\n" +
            "</tr>");
  }
  %>
</table>
<form action="logout" method="post">
  <div>
    <input type="submit" value="logout"/>
  </div>
</form>
</body>
</html>
