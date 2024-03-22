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
                       <li class="breadcrumb-item active" aria-current="page">Create</li>
                    </ol>
                </nav>
                <div class="card mt-4">
                    <div class="card-header">
                        Add Courses
                    </div>
                    <form action="/CourseServlet?route=store" method="post" class="needs-validation" novalidate>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 mb-3">
                                    <label for="course_name" class="form-label">Course Name</label>
                                    <input type="text" class="form-control" id="course_name" name="course_name" placeholder="Name" required>
                                </div>
                                <div class="col-md-12 mb-3">
      
                                    <label for="course_category" class="form-label">Course Category</label>
                                    <select class="form-select" id="course_category" name="course_category" required>
                                        <option selected disabled value="">Open this select menu</option>
                                        <c:forEach var="category" items="${listCategory}" >
                                            <option value="<c:out value='${category.id}' />"><c:out value="${category.name}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="course_duration" class="form-label">Course Duration</label>
                                    <input type="number" class="form-control" id="course_duration" name="course_duration" placeholder="Duration" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="course_difficulties" class="form-label">Course Difficulties</label>
                                    <select class="form-select" id="course_difficulties" name="course_difficulties" required>
                                        <option selected disabled value="">Open this select menu</option>
                                        <option value="Easy">Easy</option>
                                        <option value="Medium">Medium</option>
                                        <option value="Hard">Hard</option>
                                    </select>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <label for="course_status" class="form-label">Course Status</label>
                                    <select class="form-select" id="course_status" name="course_status" required=>
                                        <option selected disabled value="">Open this select menu</option>
                                        <option value="1">Active</option>
                                        <option value="0">Not Active</option>
                                    </select>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <label for="course_description" class="form-label">Course Description</label>
                                    <textarea class="form-control" id="course_description" name="course_description" rows="5"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer text-end">
                            <button type="submit" class="btn btn-primary">Add</button>
                            <a href="/CourseServlet?route=index" class="btn btn-danger">Back</a>
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