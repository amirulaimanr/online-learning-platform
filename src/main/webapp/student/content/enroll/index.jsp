<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
          <%@ include file="/components/TopBarLoggedIn.jsp" %>
        </div>
        <div class="catalog-container">
            <div class="vertical-nav" id="sidebar">
                <%@include file="/student/components/FilterBar.jsp" %>
            </div>
            <div class="flex-column explore-catalog p-5 align-items-start" id="content">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="#">Home</a></li>
                      <li class="breadcrumb-item active" aria-current="page">Enroll</li>
                    </ol>
                 </nav>
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <c:forEach var="course" items="${listCourse}" >
                        <div class="col">
                            <div class="card shadow-sm card-box card-fix-size ">
                                <div class="card-header d-flex justify-content-between">
                                    ${course.category_name}
                                    <div>
                                        <a href="/EnrollServlet?route=view&id=${course.id}&student_id=<%= user_id %>" class="btn btn-info"><i class="fa-solid fa-circle-info"></i> View</a>
                                        <a href="/EnrollServlet?route=delete&id=${course.id}&student_id=<%= user_id %>" class="btn btn-danger"><i class="fa-solid fa-trash"></i></a>
                                    </div>
                                </div>
                                <div class="card-body flex-column d-flex justify-content-between">
                                    <h5 class="card-title">${course.name}</h5>
                                    <p class="card-text">${course.description}</p>
                                    <div class="flex-row d-flex justify-content-between">
                                        <div class="" style="font-weight: 400"><i class="fa-solid fa-signal"></i>
                                            ${course.difficulties}
                                        </div>
                                        <div class="" style="font-weight: 400"><b>${course.duration}</b> hours</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>           
                <div>
                    <%-- error message here if unsuccesful login --%>
                    <%
                        if (session.getAttribute("success") != null) {
                    %>
                    <script>
                        Swal.fire({
                            title: "Success!",
                            text: "  <%= session.getAttribute("success") %>",
                            icon: "success"
                        });
                    </script>
                    <%
                        }
                        session.removeAttribute("success");
                    %>
                </div>
            </div>
        </div>
    </body>
</html>

