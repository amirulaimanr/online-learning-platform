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
                <div class="card mt-4">
                    <div class="card-header">
                        Edit Profile
                    </div>
                    <form action="/EditProfileServlet?route=update" method="post" class="needs-validation" novalidate>
                        <div class="card-body">
                            <input type="hidden" class="form-control" id="user_id" name="user_id" value="${user.id}" >
                            <input type="hidden" class="form-control" id="role_id" name="role_id" value="${user.role_id}" >
                            <div class="mb-3">
                                <label for="user_name" class="form-label">Name</label>
                                <input type="text" class="form-control" id="user_name" name="user_name" placeholder="Name" value="${user.name}" required>
                            </div>
                            <div class="mb-3">
                                <label for="user_mail" class="form-label">Email</label>
                                <input type="text" class="form-control" id="user_mail" name="user_mail" placeholder="Email" value="${user.email}" required>
                            </div>
                            <div class="mb-3">
                                <label for="user_password" class="form-label">Password</label>
                                <input type="text" class="form-control" id="user_password" name="user_password" placeholder="Password1" value="${user.password}" required>
                            </div>
                        </div>
                        <div class="card-footer text-end">
                            <button type="submit" class="btn btn-primary"><i class="fa-solid fa-pen-to-square"></i> Update</button>
                            <a href="/EditProfileServlet?route=indexx&role_id=${user.role_id}&id=${user.id}" class="btn btn-danger"><i class="fa-solid fa-arrow-left"></i> Back</a>
                        </div>
                    </form>
                </div>
            </div>
            <%@ include file="/components/notification.jsp" %>
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