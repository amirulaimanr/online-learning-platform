<%@page import="org.skillspark.onlinelearningplatform.model.Users"%>
<html>
    <body>
        <div class="accordion accordion-flush" id="accordionPanelsStayOpenExample">
            <div class="accordion-item">
                <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
                            aria-controls="panelsStayOpen-collapseOne">
                        Categories
                    </button>
                </h2>
                <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show"
                     aria-labelledby="panelsStayOpen-headingOne">
                    <div class="accordion-body">
                        <div class="flex-column align-items-start side-filter">
                            <a href="/CategoryServlet?route=index">List of Categories</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item ">
                <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false"
                            aria-controls="panelsStayOpen-collapseTwo">
                        Courses
                    </button>
                </h2>
                <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse"
                     aria-labelledby="panelsStayOpen-headingTwo">
                    <div class="accordion-body">
                        <div class="flex-column align-items-start side-filter">
                               <%
                                    Users userbar = (Users) session.getAttribute("user");
                              %>
                            <a href="/CourseServlet?route=index&tutor_id=<%= userbar.getId() %>">List of Courses</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false"
                            aria-controls="panelsStayOpen-collapseThree">
                        Enroll
                    </button>
                </h2>
                <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse"
                     aria-labelledby="panelsStayOpen-headingThree">
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
