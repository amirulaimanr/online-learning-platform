<!DOCTYPE html>
<html>
    <head>
        <title>My Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                fetchData();
                console.log(fetchData());
            });

            function fetchData() {
                $.ajax({
                    url: "/FetchCategoryServlet",
                    dataType: "json",
                    success: function(data) {
                        // Process the fetched data and populate tab buttons
                        $("#nav-tab").empty(); // Clear existing tab buttons
                        for (var i = 0; i < data.length; i++) {
                            var button = createTabButton(data[i], i); // Pass the index 'i'
                            $("#nav-tab").append(button);
                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.error("Error fetching data:", textStatus, errorThrown);
                    }
                });
            }

            function createTabButton(category, i) {
                // Construct the tab button HTML using the category name
                var buttonHtml = '<button class="nav-link';
                if (i === 0) {
                    buttonHtml += ' active'; // Set the first tab as active
                }
                buttonHtml += '" id="nav-' + category.toLowerCase() + '-tab" data-bs-toggle="tab" data-bs-target="#nav-' + category.toLowerCase() + '" type="button" role="tab" aria-controls="nav-' + category.toLowerCase() + '" aria-selected="' + (i === 0) + '">' + category + '</button>';
                return buttonHtml;
            }
        </script>
    </head>
    <body>
        <nav>
            <div class="nav nav-tabs justify-content-center" id="nav-tab" role="tablist">
                <!-- Tab buttons will be appended here -->
            </div>
        </nav>
        <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab" tabindex="0">
                <%@ include file="/components/GridCatalog3.jsp" %>
            </div>
            <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab" tabindex="0">
                <%@ include file="/components/GridCatalog3.jsp" %>
            </div>
            <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab" tabindex="0">
                <%@ include file="/components/GridCatalog3.jsp" %>
            </div>
            <div class="tab-pane fade" id="nav-disabled" role="tabpanel" aria-labelledby="nav-disabled-tab" tabindex="0">
                <%@ include file="/components/GridCatalog3.jsp" %>
            </div>
        </div>
    </body>
</html>
