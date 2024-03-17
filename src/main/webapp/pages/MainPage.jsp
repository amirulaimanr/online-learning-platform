<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Main Page</title>
        <%@ include file="/styles/bootstrap.jsp" %>
        <%@ include file="/styles/compile.jsp" %>
    </head>
    <body>
        <%@ include file="/components/TopBar.jsp" %>

        <div class="container-l layout-size">
            <div class="flex-container">
                <div class="container">
                    <%@include file="/components/Carousel.jsp" %>
                </div>
                <div class="container">
                    <div class="flex-column">
                        <div class="signup-title">
                            Join the millions learning to code with SkillSpark for free
                        </div>
                        <%@ include file="/components/SignUpForm.jsp" %>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
