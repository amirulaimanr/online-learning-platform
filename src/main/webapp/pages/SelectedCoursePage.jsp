<%@page import="org.skillspark.onlinelearningplatform.model.Users" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/x-icon" href="/styles/img/favicon_io/favicon.ico"/>
        <title>Course Page</title>
        <%@ include file="/styles/bootstrap.jsp" %>
        <%@ include file="/styles/compile.jsp" %>
    </head>
    <body class="body-course-bg">
        <div class="topbar-sticky">
            <%@ include file="/components/TopBarLogin.jsp" %>
        </div>
        <%
            int user_id = 0;
            boolean check = false;
            Users userbar = (Users) session.getAttribute("user");

            if (session.getAttribute("user") != null) {
                check = true;
                user_id = userbar.getId();
            }
        %>
        <div

        <div class="container-l layout-size">
            <div class="course-flexbox flex-column pt-5">
                <div class="course-details-container">
                    <div class="course-title-box">
                        <h5><b>Free</b> Course</h5>
                        <h1>${course.name}</h1>
                        <p>${course.description}</p>
                        <%
                            if (check == true) {
                        %>
                        <a href="/EnrollServlet?route=store&student_id=<%= user_id %>&course_id=${course.id}">
                            <div class="enroll-btn">
                                Enroll Now
                            </div>
                        </a>
                        <%
                        } else {
                        %>
                        <a href="/pages/LoginPage.jsp">
                            <div class="enroll-btn">
                                Enroll Now
                            </div>
                        </a>
                        <%
                            }
                        %>
                    </div>

                    <div class="course-img">
                        <img src="/styles/img/person-learning-online1.png">
                    </div>
                </div>


                <div class="skill-duration-container">
                    <div class="course-details">
                        <div class="align-items-center">
                            <div class="flex-row">
                                <i class="fa-solid fa-signal icon" style="font-size: 35px"></i>
                                <div class="flex-column">
                                    <h5 style="font-weight: 600">SKILL LEVEL</h5>
                                    <h6>${course.difficulties}</h6>
                                </div>
                            </div>
                        </div>
                        <div class="align-items-center">
                            <div class="flex-row">
                                <i class="fa-solid fa-clock icon" style="font-size: 35px"></i>
                                <div class="flex-column">
                                    <h5 style="font-weight: 600">TIME TO COMPLETE</h5>
                                    <h6>${course.duration} hours</h6>
                                </div>
                            </div>

                        </div>
                        <div class="align-items-center">
                            <div class="flex-row">
                                <i class="fa-solid fa-certificate icon" style="font-size: 35px"></i>
                                <div class="flex-column">
                                    <h5 style="font-weight: 600">CERTIFICATE</h5>
                                    <h6>No</h6>
                                </div>
                            </div>
                        </div>
                        <div class="align-items-center">
                            <div class="flex-row">
                                <i class="fa-solid fa-check-double icon" style="font-size: 35px"></i>
                                <div class="flex-column">
                                    <h5 style="font-weight: 600">PREREQUISITES</h5>
                                    <h6>None</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="syllabus-container">
                    <div class="syllabus-title">
                        <h5 style="color: white">Syllabus</h5>
                    </div>
                    <div class="accordion accordian-space" id="accordionFlushExample">
                        <c:forEach items="${chapters}" var="chapter">
                            <div class="accordion-item accordion-item-space">
                                <h3 class="accordion-header accordion-header-space">
                                    <button class="accordion-button accordion-header-space collapsed" type="button"
                                            data-bs-toggle="collapse"
                                            data-bs-target="#flush-collapse${chapter.id}" aria-expanded="false"
                                            aria-controls="flush-collapse${chapter.id}">

                                        <h4><b>${chapter.name}</b></h4>
                                        <h5 style="margin-left: 20px"> ${chapter.title}</h5>
                                    </button>
                                </h3>
                                <div class="chapter-desciption-box">
                                    <p style="font-weight: 400">${chapter.description}</p>
                                </div>
                                <div id="flush-collapse${chapter.id}" class="accordion-collapse collapse"
                                     data-bs-parent="#accordionFlushExample">
                                    <div class="chapter-desciption-box">
                                        <div class="flex-row video-attachment-box">
                                            <p>${chapter.videopath != null ? '<div class="video-icon" style="margin-right: 10px; font-weight: 300"><i class="fa-solid fa-file-video"></i> Video </div>' : ''}</p>
                                            <p>${chapter.attachmentpath != null ? '<div class="attachment-icon" style="font-weight: 300"><i class="fa-solid fa-file-pdf"></i> Attachment</div>' : ''}</p>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>

        <%@ include file="/components/Footer.jsp" %>

    </body>
</html>
