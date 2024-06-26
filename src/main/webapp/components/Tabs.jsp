<!DOCTYPE html>
<html>
    <head>
        <script>
            $(document).ready(function () {
                fetchData();
            });

            function fetchData() {
                $.ajax({
                    url: "/FetchCategoryCourseServlet?action=category_course",
                    dataType: "json",
                    success: function (data) {
                        $("#nav-tab").empty();
                        $("#nav-tabContent").empty();

                        var index = 0;
                        var categoryCount = 0;
                        for (var category in data) {
                            if (categoryCount >= 5) {
                                break;
                            }
                            var button = createTabButton(category, index);
                            var content = createTabContent(category, data[category], index);
                            $("#nav-tab").append(button);
                            $("#nav-tabContent").append(content);
                            index++;
                            categoryCount++;
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.error("Error fetching data:", textStatus, errorThrown);
                    }
                });
            }

            function createTabButton(category, index) {
                var buttonHtml = '<button class="nav-link';
                if (index === 0) {
                    buttonHtml += ' active';
                }
                buttonHtml += '" id="nav-' + category.toLowerCase().replace(" ", "-") + '-tab" data-bs-toggle="tab" data-bs-target="#nav-' + category.toLowerCase().replace(" ", "-") + '" type="button" role="tab" aria-controls="nav-' + category.toLowerCase().replace(" ", "-") + '" aria-selected="' + (index === 0) + '">' + category + '</button>';
                return buttonHtml;
            }

            function createTabContent(category, courses, index) {
                var contentHtml = '<div class="tab-pane fade';
                if (index === 0) {
                    contentHtml += ' show active';
                }
                contentHtml += '" id="nav-' + category.toLowerCase().replace(" ", "-") + '" role="tabpanel" aria-labelledby="nav-' + category.toLowerCase().replace(" ", "-") + '-tab">';
                contentHtml += '<div class="album album-width py-5">';
                contentHtml += '<div class="container">';
                contentHtml += '<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" id="catalog-content-' + category.toLowerCase().replace(" ", "-") + '">';

                for (var i = 0; i < 3; i++) {
                    if (i < courses.length) {
                        var course = courses[i];
                        contentHtml += '<div class="col">';
                        contentHtml += '<a href="/SelectedCourseServlet?course_id=' + course.id + '">';
                        contentHtml += '<div class="card shadow-sm card-box card-fix-size">';
                        contentHtml += '<div class="card-header"> Free course </div>';
                        contentHtml += '<div class="card-body  card-body-course flex-column d-flex justify-content-between">';
                        contentHtml += '<h5 class="card-title">' + course.name + '</h5>';
                        contentHtml += '<p class="card-text">' + course.description + '</p>';
                        contentHtml += '<div class="flex-row d-flex justify-content-between">';
                        contentHtml += '<div class="" style="font-weight: 400"><i class="fa-solid fa-signal me-2"></i>' + course.difficulties + '</div>';
                        contentHtml += '<div class="" style="font-weight: 400"><b>' + course.duration + '</b> hours</div>';
                        contentHtml += '</div>';
                        contentHtml += '</div>';
                        contentHtml += '</div>';
                        contentHtml += '</a>';
                        contentHtml += '</div>';
                    } else {
                        break;
                    }
                }
                contentHtml += '</div>';
                contentHtml += '</div>';
                contentHtml += '</div>';
                contentHtml += '</div>';
                return contentHtml;
            }
        </script>
    </head>
    <body>
        <nav>
            <div class="nav nav-tabs justify-content-center" id="nav-tab" role="tablist">
                <!-- Tab buttons -->
            </div>
        </nav>

        <div class="tab-content" id="nav-tabContent">
            <!-- Tab contents -->
        </div>
    </body>
</html>
