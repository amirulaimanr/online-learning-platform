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
                      <li class="breadcrumb-item " aria-current="page"><a href="/CourseServlet?route=index">${course_name}</a></li>
                      <li class="breadcrumb-item" aria-current="page"><a href="/ChapterServlet?route=index&id=${course_id}&name=${course_name}">Chapter</a></li>
                       <li class="breadcrumb-item active" aria-current="page">Create</li>
                    </ol>
                </nav>
                <div class="card mt-4">
                    <div class="card-header">
                        Add Chapter
                    </div>
                    <form action="/ChapterServlet?route=store" method="post" class="needs-validation" novalidate  enctype="multipart/form-data">
                        <div class="card-body">
                            <div class="row">
                                <input type="hidden" class="form-control" id="course_id" name="course_id" value="${course_id}" />
                                <input type="hidden" class="form-control" id="course_name" name="course_name" value="${course_name}" />
                                <div class="col-md-12 mb-3">
                                    <label for="chapter_title" class="form-label">Chapter Title</label>
                                    <input type="text" class="form-control" id="chapter_title" name="chapter_title" placeholder="Title" required>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <label for="chapter_name" class="form-label">Chapter Name</label>
                                    <input type="text" class="form-control" id="chapter_name" name="chapter_name" placeholder="Name" required>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <label for="formFile" class="form-label">Chapter Video</label>
                                    <input class="form-control" type="file" id="video_path" name="video_path" accept="video/*" required/>
                                </div>
                                <div class="col-md-12 mb-3">
                                  
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="chapter_status" class="form-label">Chapter Status</label>
                                    <select class="form-select" id="chapter_status" name="chapter_status" required>
                                        <option selected disabled value="">Open this select menu</option>
                                        <option value="1">Active</option>
                                        <option value="0">Not Active</option>
                                    </select>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="chapter_level" class="form-label">Chapter Level</label>
                                    <select class="form-select" id="chapter_level" name="chapter_level" required>
                                        <option selected disabled value="">Open this select menu</option>
                                        <option value="Beginner">Beginner</option>
                                        <option value="Intermediate">Intermediate</option>
                                        <option value="Expert">Expert</option>
                                    </select>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <label for="chapter_description" class="form-label">Chapter Description</label>
                                    <textarea class="form-control" id="chapter_description" name="chapter_description" rows="5"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer text-end">
                            <button type="submit" class="btn btn-primary"><i class="fa-solid fa-plus"></i> Add</button>
                            <a href="/ChapterServlet?route=index&id=${course_id}&name=${course_name}" class="btn btn-danger"><i class="fa-solid fa-arrow-left"></i> Back</a>
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