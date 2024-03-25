<%@page import="org.skillspark.onlinelearningplatform.model.Users" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <div class="vertical-nav" id="sidebar">
            <%@include file="/tutor/components/FilterBar.jsp" %>
        </div>

        <div class="row p-5 align-items-start" id="content">
            <div class="catalog-menu-btn">
                <button id="sidebarCollapse" type="button"
                        class="btn btn-dark bg-dark rounded-pill shadow-sm px-4 mb-4">
                    <small class="text-uppercase font-weight-bold">Catalog Menu</small>
                </button>
            </div>
            <%
                Users user = (Users) session.getAttribute("user");
                String userusername = "";
                try {
                    userusername = user.getName();
                } catch (NullPointerException e) {
                    response.sendRedirect("/pages/LoginPage.jsp");
                }
            %>
            <h2 style="font-weight: 600">Hi,<%= userusername %></h2>
            <h4 class="my-4" style="font-weight: 500; margin-top: 30px">Courses that you make</h4>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <c:forEach var="course" items="${listCourse}" >
                    <div class="col">
                        <a href="/TutorMainPageServlet?route=view&id=${course.id}">
                            <div class="card shadow-sm card-box card-fix-size ">
                                <div class="card-header">
                                    ${course.category_name} 
                                </div>
                                <div class="card-body card-body-course flex-column d-flex justify-content-between">
                                    <h5 class="card-title">${course.name}</h5>
                                    <p class="card-text">${course.description}</p>
                                    <div class="flex-row flex-row-card d-flex justify-content-between">
                                        <div class="" style="font-weight: 400"><i class="fa-solid fa-signal"></i>
                                            ${course.difficulties}
                                        </div>
                                        <div class="" style="font-weight: 400"><b>${course.duration}</b> hours</div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
                    </div>
                </div>
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
