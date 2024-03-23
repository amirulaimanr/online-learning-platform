<!DOCTYPE html>
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
                            <a href="/pages/CatalogPage.jsp">Explore All</a>
                        </div>
                        <div class="flex-column align-items-start side-filter" id="categoryContent">
                            <!-- Category content -->
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function fetchData(action, targetElementId) {
                $.ajax({
                    url: "/FetchCategoryCourseServlet?action=" + action,
                    dataType: "json",
                    success: function (data) {
                        var targetElement = $("#" + targetElementId);
                        targetElement.empty();
                        for (var key in data) {
                            var categoryUrl = "/pages/CatalogPage.jsp?category=" + encodeURIComponent(key);
                            targetElement.append("<a href='" + categoryUrl + "'>" + key + "</a>");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.error("Error fetching data:", textStatus, errorThrown);
                    }
                });
            }

            fetchData("course", "categoryContent");
        </script>
    </body>
</html>
