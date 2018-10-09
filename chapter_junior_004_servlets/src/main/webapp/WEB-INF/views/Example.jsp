<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 09.10.2018
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <script>

      function postFromServlet() {
          $.ajax({
              url : "./example",
              method : 'post',
              complete : function (data) {
                  var res = <%=request.getAttribute("method")%>
              },
              async:false
          })
      }


    </script>

</head>
<body>

<button type="button" onclick="postFromServlet()">Click</button>


</body>
</html>
