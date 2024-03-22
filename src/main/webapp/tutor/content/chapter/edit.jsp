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
                       <li class="breadcrumb-item active" aria-current="page">Edit</li>
                    </ol>
                </nav>
                <div class="card mt-4">
                    <div class="card-header">
                        Add Chapter
                    </div>
                    <form action="/ChapterServlet?route=update" method="post" class="needs-validation" novalidate  enctype="multipart/form-data">
                        <div class="card-body">
                            <div class="row">
                                <input type="hidden" class="form-control" id="chapter_id" name="chapter_id" value="${chapter.id}" />
                                <input type="hidden" class="form-control" id="course_id" name="course_id" value="${course_id}" />
                                <input type="hidden" class="form-control" id="course_name" name="course_name" value="${course_name}" />
                                <input type="hidden" class="form-control" id="tempt_video" name="tempt_video" value="${chapter.videoPath}" />
                                
                                <div class="col-md-12 mb-3">
                                    <label for="chapter_title" class="form-label">Chapter Title</label>
                                    <input type="text" class="form-control" id="chapter_title" name="chapter_title" value="${chapter.title}" placeholder="Title" required>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <label for="chapter_name" class="form-label">Chapter Name</label>
                                    <input type="text" class="form-control" id="chapter_name" name="chapter_name" placeholder="Name" value="${chapter.name}" required>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <label for="formFile" class="form-label">Chapter Video</label>
                                    <input class="form-control" type="file" id="video_path" name="video_path" />
                                </div>
                                <div class="col-md-12 mb-3 text-center">
                                    <video controls>
                                        <source src="${chapter.videoPath}" type="video/mp4">
                                    </video>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <a href="${chapter.videoPath}" target="_blank" class="">${chapter.videoPath}</a>
                                </div>
 
                                <div class="col-md-6 mb-3">
                                    <label for="chapter_status" class="form-label">Chapter Status</label>
                                    <select class="form-select" id="chapter_status" name="chapter_status" required>
                                        <option selected disabled value="">Open this select menu</option>
                                        <option value="1" <c:if test="${chapter.status == 1}">selected</c:if> >Active</option>
                                        <option value="0" <c:if test="${chapter.status == 0}">selected</c:if> >Not Active</option>
                                    </select>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="chapter_level" class="form-label">Chapter Level</label>
                                    <select class="form-select" id="chapter_level" name="chapter_level" required>
                                        <option selected disabled value="">Open this select menu</option>
                                        <option value="Beginner" <c:if test="${chapter.level.equals('Beginner')}">selected</c:if> >Beginner</option>
                                        <option value="Intermediate" <c:if test="${chapter.level.equals('Intermediate')}">selected</c:if> >Intermediate</option>
                                        <option value="Expert" <c:if test="${chapter.level.equals('Expert')}">selected</c:if> >Expert</option>
                                    </select>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <label for="chapter_description" class="form-label">Chapter Description</label>
                                    <textarea class="form-control" id="chapter_description" name="chapter_description" rows="5">${chapter.description}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer text-end">
                            <button type="submit" class="btn btn-primary">Update</button>
                            <a href="/ChapterServlet?route=index&id=${course_id}&name=${course_name}" class="btn btn-danger">Back</a>
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