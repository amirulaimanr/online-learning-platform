<%@page import="org.skillspark.onlinelearningplatform.model.Users"%>
<html>
    <body>
        <%
            Users userbar = (Users) session.getAttribute("user");
            int user_id = 0;
            try{
                user_id = userbar.getId();
            }catch (NullPointerException e){
                 response.sendRedirect("/pages/LoginPage.jsp");
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
                            <a href="/StudentMainPageServlet?route=index&student_id=<%= user_id %>">Dashboard</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="header2">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#item2" aria-expanded="true" aria-controls="item2">
                        Enroll
                    </button>
                </h2>
                <div id="item2" class="accordion-collapse collapse" aria-labelledby="header2">
                    <div class="accordion-body">
                        <div class="flex-column align-items-start side-filter">
                            <a href="#">Enroll Course</a>
                        </div>
                    </div>
                </div>
            </div>
          
        </div>
    </body>
</html>
