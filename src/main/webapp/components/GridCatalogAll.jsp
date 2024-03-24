<div class="album py-5">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" id="catalog-content">
            <!-- Course cards will be dynamically added here -->
        </div>
    </div>
</div>

<script>
    $(function () {
        $('#sidebarCollapse').on('click', function () {
            $('#sidebar, #content').toggleClass('active');
        });

        fetchCourses();
    });

    function fetchCourses() {
        $.ajax({
            url: "/FetchAllCoursesServlet",
            method: "GET",
            dataType: "json",
            success: function (data) {
                renderCourses(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error("Error fetching courses:", textStatus, errorThrown);
            }
        });
    }

    function renderCourses(courses) {
        var catalogContent = $("#catalog-content");
        catalogContent.empty();

        $.each(courses, function (index, course) {
            var courseHtml = '<div class="col">' +
                '<a href="/SelectedCourseServlet?course_id=' + course.id + '">' +
                '<div class="card shadow-sm card-box">' +
                '<div class="card-fix-size">' +
                '<div>' +
                '<div class="card-header">Free course</div>' +
                '<div class="card-body flex-column d-flex justify-content-between">' +
                '<h5 class="card-title">' + course.name + '</h5>' +
                '<p class="card-text">' + course.description + '</p>' +
                '<div class="flex-row d-flex justify-content-between">' +
                '<div class="" style="font-weight: 400"><i class="fa-solid fa-signal me-2"></i>' + course.difficulties + '</div>' +
                '<div class="" style="font-weight: 400"><b>' + course.duration + '</b> hours</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</a>' +
                '</div>';
            catalogContent.append(courseHtml);
        });
    }
</script>
