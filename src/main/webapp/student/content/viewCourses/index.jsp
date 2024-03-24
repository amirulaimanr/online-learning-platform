<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <%@include file="/student/components/FilterBar.jsp"%>
            </div>
            <div class="row" >
                <div class="container-l layout-size">
                    <div class="course-flexbox flex-column pt-5">
                        <div class="course-details-container">
                            <div class="course-title-box">
                                <h5>
                                    ${course.category_name}
                                </h5>
                                <h1>
                                    ${course.name}
                                    <h5 style="margin-top:-2em;">By <b>${course.username}</b></h5>
                                </h1>
                                <p>${course.description}</p>
                                <%
                                    boolean isEnroll = (Boolean) request.getAttribute("isEnroll");
                                    if(isEnroll == false){
                                %>
                                <a href="/EnrollServlet?route=store&student_id=<%= user_id %>&course_id=${course.id}">
                                    <div class="enroll-btn"><i class="fa-solid fa-plus"></i> Enroll Now
                                    </div>
                                </a>
                                <%
                                    }else{
                                %>
                                <a href="/EnrollServlet?route=view&id=${course.id}&student_id=<%= user_id %>">
                                    <div class="enroll-btn"><i class="fa-solid fa-circle-info"></i> View Course Now
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
                            <body class="body-course-bg">
                                <div class="accordion" id="accordionFlushExample">
                                    <c:set var="count" value="0" scope="page" />
                                    <c:forEach var="chapter" items="${listChapter}" >
                                        <div class="accordion-item">
                                            <h2 class="accordion-header"">
                                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse-<c:out value='${count}' />" aria-expanded="false" aria-controls="flush-collapseOne">
                                                    <h4>${chapter.name} :</h4><h5>${chapter.title}</h5>
                                                </button>
                                            </h3>
                                            <div class="chapter-desciption-box">
                                                <p style="font-weight: 400">${chapter.description}</p>
                                            </div>
                                            <div id="flush-collapse-<c:out value='${count}' />" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                                                <div class="chapter-desciption-box mb-2">
                                                    <strong>Level :</strong> ${chapter.level}</small>
                                                </div>
                                            </div>
                                        </div>
                                        <c:set var="count" value="${count + 1}" scope="page" />
                                    </c:forEach>
                                </div>
                            </body>
                        </div>
                    </div>
                </div>                                
            </div>
        </div>
    </body>
</html>
