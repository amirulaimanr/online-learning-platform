
<%@page import="org.skillspark.onlinelearningplatform.model.Users"%>
<html>
    <head>
        <title>TopBar</title>
    </head>
    <body>
        <%
            Users usersearch = (Users) session.getAttribute("user");
            int search_user_id = 0;
            try {
                search_user_id = usersearch.getId();
            } catch (NullPointerException e) {
                response.sendRedirect("/pages/LoginPage.jsp");
            }
        %>
        <nav class="navbar navbar-expand-lg bg-body-tertiary topbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="/" >
                    <img src="/styles/img/logo/skillspark_logo.png" class="img-fluid" height="120" width="140">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                    </ul>
                   <form class="d-flex pe-2 login-btn" id="searchForm" action="/SearchServlet?id=<%= search_user_id %>" method="post">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search" >
                        <button class="btn btn-outline-success" type="submit"><i class="fas fa-search"></i></button>
                    </form>
                    <form id="logoutForm" action="/LogoutServlet" method="post">
                        <button type="submit" class="btn btn-outline-success">Logout</button>
                    </form>

                </div>
            </div>
        </nav>
    </body>
</html>