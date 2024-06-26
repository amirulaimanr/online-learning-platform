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
                      <li class="breadcrumb-item " aria-current="page"><a href="/TutorEnrollServlet?route=index&tutor_id=<%= user_id %>">${course_name}</a></li>
                      <li class="breadcrumb-item active" aria-current="page">List Student</li>
                    </ol>
                </nav>
                <div class="col-md-12 mt-2 mb-2">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Student Name</th>
                                <th scope="col">Date Join</th>
                                <th scope="col" width="10%">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach var="student" items="${listStudent}" >
                                <c:set var="count" value="${count + 1}" scope="page" />
                                <tr>
                                    <th scope="row"><c:out value="${count}" /></th>
                                    <td><c:out value="${student.student_name}" /></td>
                                    <td><c:out value="${student.date_enroll}" /></td>
                                    <td class="d-flex">
                                        <form action="/TutorEnrollServlet?route=delete&id=<c:out value='${student.course_id}' />&student_id=<c:out value='${student.student_id}' />&course_name=${course_name} " method="post" id="delete-item-form-<c:out value='${count}' />"  >
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
                                <a class="page-link" href="/TutorEnrollServlet?route=view&id=${course_id}&course_name=${course_name}&page=${currentPage - 1}" tabindex="-1"><</a>
                            </li>
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="/TutorEnrollServlet?route=view&id=${course_id}&course_name=${course_name}&page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item ${currentPage == totalPages ? "disabled" : ""}">
                                <a class="page-link" href="/TutorEnrollServlet?route=view&id=${course_id}&course_name=${course_name}&page=${currentPage + 1}">></a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col-md-12 mt-2 mb-2 text-end">
                    <a href="/TutorEnrollServlet?route=index&tutor_id=<%= user_id %>" class="btn btn-danger" ><i class="fa-solid fa-arrow-left"></i> Back</a>
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
