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
                      <li class="breadcrumb-item " aria-current="page"><a href="/CategoryServlet?route=index">Category</a></li>
                       <li class="breadcrumb-item active" aria-current="page">Edit</li>
                    </ol>
                </nav>
                <div class="card mt-4">
                    <div class="card-header">
                        Add Categories
                    </div>
                    <form action="/CategoryServlet?route=update" method="post" class="needs-validation" novalidate>
                        <div class="card-body">
                            <input type="hidden" class="form-control" id="cat_id" name="cat_id" value="${category.id}" >
                            <div class="mb-3">
                                <label for="cat_name" class="form-label">Name</label>
                                <input type="text" class="form-control" id="cat_name" name="cat_name" placeholder="Name" value="${category.name}" required>
                            </div>
                            <div class="mb-3">
                                <label for="cat_description" class="form-label">Description</label>
                                <textarea class="form-control" id="cat_description" name="cat_description" rows="3">${category.description}</textarea>
                            </div>
                        </div>
                        <div class="card-footer text-end">
                            <button type="submit" class="btn btn-primary"><i class="fa-solid fa-pen-to-square"></i> Update</button>
                            <a href="/CategoryServlet?route=index" class="btn btn-danger"><i class="fa-solid fa-arrow-left"></i> Back</a>
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