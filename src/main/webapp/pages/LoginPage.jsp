<html>
    <head>
        <title>Login Page</title>
        <%@ include file="/styles/bootstrap.jsp" %>
        <%@ include file="/styles/compile.jsp" %>
    </head>
    <body>
        <%@ include file="/components/TopBarSignUp.jsp"%>

        <div class="layout-size">
            <div class="flex-box">
                <div class="flex-column-auto">
                    <div class="login-skillspark-logo">
                        <img src="/styles/img/logo/skillspark_logo1.png" style="width: 60px; height: 60px;">
                    </div>
                    <div class="login-title">
                        Login to SkillSpark
                    </div>
                    <div class="login-size">
                        <%@ include file="/components/LoginForm.jsp"%>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/components/Footer.jsp"%>


    </body>
</html>