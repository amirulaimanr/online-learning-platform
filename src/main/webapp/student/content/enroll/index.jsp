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
                    <c:set var="count" value="0" scope="page" />
                    <c:forEach var="course" items="${listCourse}" >
                        <c:set var="count" value="${count + 1}" scope="page" />
                        <div class="col">
                            <a href="/EnrollServlet?route=view&id=${course.id}&student_id=<%= user_id %>" >
                                <div class="card shadow-sm card-box card-fix-size ">
                                    <div class="card-header d-flex justify-content-between">
                                        ${course.category_name}
                                        <div class="d-flex ">

    <!--                                        <form action="/EnrollServlet?route=delete&id=${course.id}&student_id=<%= user_id %>" method="post" id="delete-item-form-<c:out value='${count}' />"  >
                                                <button class="btn btn-danger ms-2" type="button" onclick="deleteItem(<c:out value='${count}' />)" id="delete-btn-<c:out value='${count}' />" ><i class="fa-solid fa-trash"></i> Delete</button>
                                            </form>-->
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
                            </a>
                        </div>
                    </c:forEach>
                </div>           
                <div>
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
<script>
    function deleteItem(index)
    {
        Swal.fire({
            title: 'Are you sure?',
            text: "This action cannot be revert!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes,Delete!'
            }).then((result) => {
            if (result.isConfirmed) {
                $('#delete-item-form-'+index).submit();
            }
        });
    }
</script>