<html>
    <head>
        <link rel="icon" type="image/x-icon" href="/styles/img/favicon_io/favicon.ico" />
        <title>SignUp Page</title>
        <%@ include file="/styles/bootstrap.jsp" %>
        <%@ include file="/styles/compile.jsp" %>
    </head>
    <body>
        <%@ include file="/components/TopBarLogin.jsp"%>

        <div class="layout-size layout-colour-gradient2">
            <div class="flex-box">
                <div class="flex-column-auto">
                    <div class="login-skillspark-logo">
                        <img src="/styles/img/logo/skillspark_logo1.png" style="width: 60px; height: 60px;">
                    </div>
                    <div class="login-title">
                        Get started for free
                    </div>
                    <div class="login-size">
                        <%@ include file="/components/SignUpForm.jsp"%>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/components/Footer.jsp"%>


    </body>
</html>
