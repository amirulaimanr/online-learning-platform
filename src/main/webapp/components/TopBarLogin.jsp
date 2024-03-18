<html>
    <head>
        <title>TopBar</title>
    </head>
    <body>
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
<%--                        <li class="nav-item">--%>
<%--                            <a class="nav-link active" aria-current="page" href="#">Home</a>--%>
<%--                        </li>--%>
                        <li class="nav-item">
                            <a class="nav-link" href="/pages/CatalogPage.jsp">Catalog</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Resource</a>
                        </li>
                    </ul>
                    <form class="d-flex pe-2 login-btn" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit"><i class="fas fa-search"></i></button>
                    </form>
                    <a class="btn btn-outline-success" href="/pages/LoginPage.jsp">Login</a>
                </div>
            </div>
        </nav>
    </body>
</html>
