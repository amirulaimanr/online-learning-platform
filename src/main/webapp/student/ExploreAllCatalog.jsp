<%@page import="org.skillspark.onlinelearningplatform.model.Users"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <div class="vertical-nav" id="sidebar">
            <%@include file="/student/components/FilterBar.jsp"%>
        </div>

        <div class="flex-column explore-catalog p-5 align-items-start" id="content">
            <div class="catalog-menu-btn">
                <button id="sidebarCollapse" type="button"
                        class="btn btn-dark bg-dark rounded-pill shadow-sm px-4 mb-4">
                    <small class="text-uppercase font-weight-bold">Catalog Menu</small>
                </button>
            </div>
            <%
                Users user = (Users) session.getAttribute("user");
                String userusername = "";
                int useruser_id = 0;
                try{
                    userusername = user.getName();
                    useruser_id = user.getId();
                } catch (NullPointerException e) {
                    response.sendRedirect("/pages/LoginPage.jsp");
                }
            %>
            <h2 style="font-weight: 600">Hi,<%= userusername %> </h2>
            <h4 class="my-4" style="font-weight: 500; margin-top: 30px">Courses that you interested </h4>

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <c:forEach var="course" items="${listCourse}" >
                    <div class="col">
                        <div class="card shadow-sm card-box card-fix-size ">
                            <div class="card-header d-flex justify-content-between">
                                ${course.category_name}
                                <a href="/StudentMainPageServlet?route=view&id=${course.id}&student_id=<%= useruser_id %>" class="btn btn-info"><i class="fa-solid fa-circle-info"></i> View</a>
                            </div>
                            <div class="card-body flex-column d-flex justify-content-between">
                                <h5 class="card-title">${course.name}</h5>
                                <p class="card-text">${course.description}</p>
                                <div class="flex-row d-flex justify-content-between">
                                    <div class="" style="font-weight: 400"><i class="fa-solid fa-signal"></i>
                                        ${course.difficulties}
                                    </div>
                                    <div class="" style="font-weight: 400"><b>${course.duration}</b> hours</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <script>
            $(function () {
                // Sidebar toggle behavior
                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar, #content').toggleClass('active');
                });
            });
        </script>
    </body>

</html>
