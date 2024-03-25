<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/x-icon" href="/styles/img/favicon_io/favicon.ico"/>
        <title>Catalog Page</title>
        <%@ include file="/styles/bootstrap.jsp" %>
        <%@ include file="/styles/compile.jsp" %>
    </head>
    <body>
        <div class="topbar-sticky">
            <%@ include file="/components/TopBarLoginSignUp.jsp" %>
        </div>
        <div class="vertical-nav" id="sidebar">
            <%@include file="/components/FilterBar.jsp" %>
        </div>
        <div class="catalog-container">
            <% String category = request.getParameter("category"); %>
            <% if (category != null) { %>
            <%@ include file="/pages/CategoryCatalog.jsp" %>
            <% } else { %>
            <%@ include file="/pages/ExploreAllCatalog.jsp" %>
            <% } %>
        </div>
        <%-- Include Footer --%>
        <%--        <%@ include file="/components/Footer.jsp" %>--%>

        <script>
            $(function () {
                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar, #content').toggleClass('active');
                });
            });
        </script>
    </body>
</html>
