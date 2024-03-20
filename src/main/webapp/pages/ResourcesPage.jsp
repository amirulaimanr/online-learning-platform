<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/x-icon" href="/styles/img/favicon_io/favicon.ico"/>
        <title>Resources Page</title>
        <%@ include file="/styles/bootstrap.jsp" %>
        <%@ include file="/styles/compile.jsp" %>

    </head>
    <body>
        <div class="topbar-sticky">
            <%@ include file="/components/TopBarLoginSignUp.jsp" %>
        </div>
        <div class="catalog-container">
            <%@ include file="/pages/ResourcePageCatalog.jsp" %>
        </div>

        <%--        <%@ include file="/components/Footer.jsp" %>--%>
    </body>
</html>
