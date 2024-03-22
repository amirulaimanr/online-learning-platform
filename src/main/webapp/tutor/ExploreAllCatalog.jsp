
<%@page import="org.skillspark.onlinelearningplatform.model.Users"%>
<html>
    <body>
        <div class="vertical-nav" id="sidebar">
            <%@include file="/tutor/components/FilterBar.jsp"%>
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
            %>
            <h2 style="font-weight: 600">Hi,<%= user.getName() %> </h2>
            <h4 style="font-weight: 500; margin-top: 30px">Courses</h4>
            <%@ include file="/tutor/components/GridCatalog.jsp" %>
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
