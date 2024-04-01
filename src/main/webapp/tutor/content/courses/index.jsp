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
                        <li class="breadcrumb-item active" aria-current="page">Course</li>
                    </ol>
                </nav>
                <div class="col-md-12 text-end">
                    <a href="/CourseServlet?route=create" class="btn btn-success"><i class="fa-solid fa-plus"></i> Add Courses</a>
                </div>

                <div class="col-md-12 mt-2 mb-2">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Category</th>
                                <th scope="col" width="40%">Description</th>
                                <th scope="col">Duration</th>
                                <th scope="col">Difficulties</th>
                                <th scope="col" width="15%">Action</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:set var="count" value="0" scope="page" />
                            <c:forEach var="course" items="${listCourse}" >
                                <c:set var="count" value="${count + 1}" scope="page" />
                                <tr>
                                    <th scope="row"><c:out value="${count}" /></th>
                                    <td><c:out value="${course.name}" /></td>
                                    <td><c:out value="${course.category_name}" /></td>
                                    <td><c:out value="${course.description}" /></td>
                                    <td><c:out value="${course.duration}" /></td>
                                    <td><c:out value="${course.difficulties}" /></td>
                                    <td class="d-flex">
                                        <a href="/CourseServlet?route=edit&id=<c:out value='${course.id}' />" class="btn btn-sm btn-primary me-2"><i class="fa-solid fa-pen-to-square"></i> Edit</a>
                                        <a href="/ChapterServlet?route=index&id=<c:out value='${course.id}' />&name=<c:out value='${course.name}' />"  class="btn btn-sm btn-success me-2"><i class="fa-solid fa-list"></i> Chapter</a>
                                        <form action="/CourseServlet?route=delete&id=<c:out value='${course.id}' />&tutor_id=<%= user_id%>" method="post" id="delete-item-form-<c:out value='${count}' />"  >
                                            <button class="btn btn-sm btn-danger" type="button" onclick="deleteItem(<c:out value='${count}' />)" id="delete-btn-<c:out value='${count}' />" ><i class="fa-solid fa-trash"></i> Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 mt-2 mb-2 d-flex justify-content-end">
                    <nav  aria-label="Page navigation">
                        <ul class="pagination">
                            <li class="page-item ${currentPage == 1 ? "disabled" : "" }">
                                <a class="page-link" href="/CourseServlet?route=index&tutor_id=<%= user_id %>&page=${currentPage - 1}" tabindex="-1"><</a>
                            </li>
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="/CourseServlet?route=index&tutor_id=<%= user_id %>&page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item ${currentPage == totalPages ? "disabled" : ""}">
                                <a class="page-link" href="/CourseServlet?route=index&tutor_id=<%= user_id %>&page=${currentPage + 1}">></a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <%@ include file="/components/notification.jsp" %>
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
                $('#delete-item-form-' + index).submit();
            }
        });
    }
</script>

