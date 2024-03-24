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
                      <li class="breadcrumb-item active" aria-current="page">Enroll Student</li>
                    </ol>
                </nav>
                <div class="col-md-12 mt-2 mb-2">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Category</th>
                                <th scope="col">Duration</th>
                                <th scope="col">Difficulties</th>
                                <th scope="col" width="10%">Action</th>
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
                                    <td><c:out value="${course.duration}" /></td>
                                    <td><c:out value="${course.difficulties}" /></td>
                                    <td class="d-flex">
                                        <a href="/CourseServlet?route=edit&id=<c:out value='${course.id}' />" class="btn btn-sm btn-primary me-2"><i class="fa-solid fa-list"></i> List of Student</a>  
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>           
            </div>
        </div>
    </body>
</html>

