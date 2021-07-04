<%-- 
    Document   : LifeCycleDemo
    Created on : Jun 4, 2021, 11:25:04 AM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Life Cycle Demo</title>
    </head>
    <body>
        <h1>JSP Life Cycle Demo</h1>
        <%!
            int num;
            public void jspInit() {
                System.out.println("jspInit is invoked!!!");
                num = 10;
            }

            public void jspDestroy() {
                System.out.println("jspDestroy is invoked!!!");
                num = 0;
            }

            public int add (int n) {
                System.out.println("add is called!!!");
                num += n;
                return num;
            }
        %>
        Init number <%= num%><br/>
        <%= "Result of add is " + add(5)%>
    </body>
</html>
