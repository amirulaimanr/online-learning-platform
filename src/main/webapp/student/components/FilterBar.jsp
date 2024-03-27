<%@page import="org.skillspark.onlinelearningplatform.model.Users"%>
<html>
    <body>
        <%
            Users userbar = (Users) session.getAttribute("user");
            int user_id = 0;
            try {
                user_id = userbar.getId();
            } catch (NullPointerException e) {
                response.sendRedirect("/pages/LoginPage.jsp");
            }
        %>
        <div class="accordion accordion-flush" id="accordionPanelsStayOpenExample">
            <div class="accordion-item">
                <h2 class="accordion-header" id="header1">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#item1" aria-expanded="true" aria-controls="item1">
                        <i class="fa-solid fa-house me-2"></i> Main Page
                    </button>
                </h2>
                <div id="item1" class="accordion-collapse collapse"aria-labelledby="header1">
                    <div class="accordion-body">
                        <div class="flex-column align-items-start side-filter">
                            <a href="/StudentMainPageServlet?route=index&student_id=<%= user_id%>">Dashboard</a>
                        </div>
                        <div class="flex-column align-items-start side-filter mt-2" id="categoryContent">
                            <!-- Category content -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="header2">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#item2" aria-expanded="true" aria-controls="item2">
                        <i class="fa-solid fa-book me-2"></i> Enroll
                    </button>
                </h2>
                <div id="item2" class="accordion-collapse collapse" aria-labelledby="header2">
                    <div class="accordion-body">
                        <div class="flex-column align-items-start side-filter">
                            <a href="/EnrollServlet?route=index&student_id=<%= user_id%>">Enroll Course</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function fetchData(action, targetElementId) {
                $.ajax({
                    url: "/FetchCategoryCourseServlet?action=" + action,
                    dataType: "json",
                    success: function (data) {
                        var targetElement = $("#" + targetElementId);
                        targetElement.empty();
                        for (var key in data) {
                            var categoryUrl = "/student/CatalogPage.jsp?category=" + encodeURIComponent(key);
                            targetElement.append("<a class='mb-2' href='" + categoryUrl + "'>" + key + "</a>");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.error("Error fetching data:", textStatus, errorThrown);
                    }
                });
            }

            fetchData("course", "categoryContent");
        </script>
    </body>
</html>
