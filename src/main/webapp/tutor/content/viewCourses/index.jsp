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
                <%@include file="/tutor/components/FilterBar.jsp"%>
            </div>
            <div class="row" >
                <div class="layout-content">
                    <div class="container flex-grow-1 container-p-y">
                        <div class="row mt-4">
                            <div class="col">
                                <div class="card mb-4">
                                    <div class="card-header text-start p-3">
                                        <h4>${course.name}</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="row mb-2">
                                            <div class="col-md-3 text-muted">Tutor Name </div>
                                            <div class="col-md-9">
                                                   ${course.username}
                                            </div>
                                        </div>

                                        <div class="row mb-2">
                                            <div class="col-md-3 text-muted">Category </div>
                                            <div class="col-md-9">
                                                ${course.category_name}
                                            </div>
                                        </div>

                                        <div class="row mb-2">
                                            <div class="col-md-3 text-muted">Description </div>
                                            <div class="col-md-9">
                                                <p>
                                                    ${course.description}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer text-center p-0">
                                        <div class="row no-gutters row-bordered row-border-light">
                                            <a href="javascript:void(0)" class="d-flex col flex-column text-body py-3">
                                                <div class="font-weight-bold">${course.duration} hrs</div>
                                                <div class="text-muted small">video on demand</div>
                                            </a>

                                            <a href="javascript:void(0)" class="d-flex col flex-column text-body py-3">
                                                <div class="font-weight-bold">${course.difficulties}</div>
                                                <div class="text-muted small">Type of difficulties</div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <c:forEach var="chapter" items="${listChapter}" >
                                    <div class="card mb-4">
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
