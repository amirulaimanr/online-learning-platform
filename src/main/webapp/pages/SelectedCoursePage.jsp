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
    <body>
        <div class="topbar-sticky">
            <%@ include file="/components/TopBarLogin.jsp" %>
        </div>


        <div class="container-l layout-size">
            <div class="course-flexbox flex-column pt-5">
                <div class="course-details-container">
                    <div class="course-title-box">
                        <h5><b>Free</b> Course</h5>
                        <h1>${course.name}</h1>
                        <p>${course.description}</p>
                        <a href="/enroll?course_id=${course.id}">
                            <div class="enroll-btn">Enroll Now
                            </div>
                        </a>
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
                                    <h6>Yes</h6>
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
                        <h5>Syllabus</h5>
                    </div>
                    <%@ include file="/components/Accordion.jsp" %>
                </div>
            </div>
        </div>

        <%@ include file="/components/Footer.jsp" %>

    </body>
</html>
