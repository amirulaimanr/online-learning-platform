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
    <body class="body-course-bg">
        <div class="topbar-sticky">
            <%@ include file="/components/TopBarLoggedIn.jsp" %>
        </div>
        <div class="catalog-container">
            <div class="vertical-nav" id="sidebar">
                <%@include file="/tutor/components/FilterBar.jsp"%>
            </div>
            <div class="row" >
                <div class="layout-content">
                    <div class="container flex-grow-1 container-p-y">
                        <div class="row mt-4">
                            <div class="col">
                                <div class="course-details-container">
                                    <div class="course-title-box">
                                        <h5>${course.category_name}</h5>
                                        <h1>
                                            ${course.name}
                                            <h5 style="margin-top:-2em;">By <b>${course.username}</b></h5>
                                        </h1>
                                        <p>${course.description}</p>
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
                                
                                <c:forEach var="chapter" items="${listChapter}" >
                                    <div class="card mb-4 mt-4">
                                        <div class="card-header text-start p-3">
                                            <h4>${chapter.name} :</h4><h5>${chapter.title}</h5>
                                        </div>
                                        <div class="card-body">
                                            <video width="1260" height="500" controls>
                                                <source src="${chapter.videoPath}" type="video/mp4">
                                            </video>
                                            <div class="ui-bordered">
                                                <div class="p-4">
                                                    <h5>Description</h5>
                                                    <p>
                                                        ${chapter.description}
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <a href="javascript:void(0)" class="d-inline-block text-muted">
                                                <small class="align-middle">
                                                <strong>Level :</strong> ${chapter.level}</small>
                                            </a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
