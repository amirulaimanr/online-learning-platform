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
                <%@include file="/tutor/components/FilterBar.jsp"%>
            </div>
            <div class="flex-column explore-catalog p-5 align-items-start" id="content">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="#">Home</a></li>
                      <li class="breadcrumb-item active" aria-current="page">Category</li>
                    </ol>
                 </nav>
                <div class="col-md-12 text-end">
                    <a href="/CategoryServlet?route=create" class="btn btn-success">Add Category</a>
                </div>

                <div class="col-md-12 mt-2 mb-2">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach var="category" items="${listCategory}" >
                                <c:set var="count" value="${count + 1}" scope="page" />
                                <tr>
                                    <th scope="row"><c:out value="${count}" /></th>
                                    <td><c:out value="${category.name}" /></td>
                                    <td><c:out value="${category.description}" /></td>
                                    <td class="d-flex">
                                        <a href="/CategoryServlet?route=edit&id=<c:out value='${category.id}' />" class="btn btn-sm btn-primary me-2">Edit</a>
                                        <form action="/CategoryServlet?route=delete&id=<c:out value='${category.id}' />" method="post" id="delete-item-form-<c:out value='${count}' />"  >
                                            <button class="btn btn-sm btn-danger" type="button" onclick="deleteItem(<c:out value='${count}' />)" id="delete-btn-<c:out value='${count}' />" >Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
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

