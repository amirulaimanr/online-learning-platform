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
                      <li class="breadcrumb-item " aria-current="page"><a href="/CourseServlet?route=index">Course</a></li>
                       <li class="breadcrumb-item active" aria-current="page">Edit</li>
                    </ol>
                </nav>
                <div class="card mt-4">
                    <div class="card-header">
                        Add Courses
                    </div>
                    <form action="/CourseServlet?route=update" method="post" class="needs-validation" novalidate>
                        <div class="card-body">
                            <div class="row">
                                <input type="hidden" class="form-control" id="course_id" name="course_id" value="${course.id}" >
                                <input type="hidden" id="tutor_id" name="tutor_id" value="<%= user_id %>">
                                <div class="col-md-12 mb-3">
                                    <label for="course_name" class="form-label">Course Name</label>
                                    <input type="text" class="form-control" id="course_name" name="course_name" placeholder="Name" value="${course.name}" required>
                                </div>
                                <div class="col-md-12 mb-3">
      
                                    <label for="course_category" class="form-label">Course Category</label>
                                    <select class="form-select" id="course_category" name="course_category" required>
                                        <option selected disabled value="">Open this select menu</option>
                                        <c:forEach var="category" items="${listCategory}" >
                                            <option value="<c:out value='${category.id}' />"  <c:if test="${course.category_id == category.id }">selected</c:if> ><c:out value="${category.name}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="course_duration" class="form-label">Course Duration</label>
                                    <input type="number" class="form-control" id="course_duration" name="course_duration" placeholder="Name" value="${course.duration}" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="course_difficulties" class="form-label">Course Difficulties</label>
                                    <select class="form-select" id="course_difficulties" name="course_difficulties" required>
                                        <option selected disabled value="">Open this select menu</option>
                                        <option value="Easy" <c:if test="${course.difficulties.equals('Easy')}">selected</c:if> >Easy</option>
                                        <option value="Medium" <c:if test="${course.difficulties.equals('Medium')}">selected</c:if> >Medium</option>
                                        <option value="Hard" <c:if test="${course.difficulties.equals('Hard')}">selected</c:if> >Hard</option>
                                    </select>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <label for="course_status" class="form-label">Course Status</label>
                                    <select class="form-select" id="course_status" name="course_status" required=>
                                        <option selected disabled value="">Open this select menu</option>
                                        <option value="1" <c:if test="${course.status == 1}">selected</c:if> >Active</option>
                                        <option value="0" <c:if test="${course.status == 0}">selected</c:if> >Not Active</option>
                                    </select>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <label for="course_description" class="form-label">Course Description</label>
                                    <textarea class="form-control" id="course_description" name="course_description" rows="5">${course.description}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer text-end">
                            <button type="submit" class="btn btn-primary"><i class="fa-solid fa-pen-to-square"></i> Update</button>
                            <a href="/CourseServlet?route=index&tutor_id=<%= user_id %>" class="btn btn-danger"><i class="fa-solid fa-arrow-left"></i> Back</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<script>
    (function () {
        'use strict'

        var forms = document.querySelectorAll('.needs-validation')

        Array.prototype.slice.call(forms).forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
    })()
</script>