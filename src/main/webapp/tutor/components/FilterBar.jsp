<%@page import="org.skillspark.onlinelearningplatform.model.Users"%>
<html>
    <body>
        <%
            Users userbar = (Users) session.getAttribute("user");
            int user_id = 0;
            try{
                user_id = userbar.getId();
            }catch (NullPointerException e){
//                 response.sendRedirect("/pages/LoginPage.jsp");
            }
        %>
        <div class="accordion accordion-flush" id="accordionPanelsStayOpenExample">
            <div class="accordion-item">
                <h2 class="accordion-header" id="header1">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#item1" aria-expanded="true" aria-controls="item1">
                        Main Page
                    </button>
                </h2>
                <div id="item1" class="accordion-collapse collapse"aria-labelledby="header1">
                    <div class="accordion-body">
                        <div class="flex-column align-items-start side-filter">
                            <a href="/TutorMainPageServlet?route=index&tutor_id=<%= user_id %>">Dashboard</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="header2">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#item2" aria-expanded="true" aria-controls="item2">
                        Categories
                    </button>
                </h2>
                <div id="item2" class="accordion-collapse collapse" aria-labelledby="header2">
                    <div class="accordion-body">
                        <div class="flex-column align-items-start side-filter">
                            <a href="/CategoryServlet?route=index">List of Categories</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item ">
                <h2 class="accordion-header" id="header3">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#item3" aria-expanded="false" aria-controls="item3">
                        Courses
                    </button>
                </h2>
                <div id="item3" class="accordion-collapse collapse" aria-labelledby="header2">
                    <div class="accordion-body">
                        <div class="flex-column align-items-start side-filter">
                            <a href="/CourseServlet?route=index&tutor_id=<%= user_id %>">List of Courses</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="header4">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#item4" aria-expanded="false" aria-controls="item4">
                        Enroll
                    </button>
                </h2>
                <div id="item4" class="accordion-collapse collapse" aria-labelledby="header4">
                    <div class="accordion-body">
                        <div class="flex-column align-items-start side-filter">
                            <a href="SignUpPage.jsp">List of Enroll Student</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
