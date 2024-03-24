<html>
    <body>
        <div class="flex-column explore-catalog p-5 align-items-start" id="content">
            <div class="catalog-menu-btn">
                <button id="sidebarCollapse" type="button"
                        class="btn btn-dark bg-dark rounded-pill shadow-sm px-4 mb-4">
                    <small class="text-uppercase font-weight-bold">Catalog Menu</small>
                </button>
            </div>
            <h2 style="font-weight: 600">Data Science Catalog</h2>
            <h4 style="font-weight: 500; margin-top: 30px">Courses</h4>
            <%@ include file="/components/GridCatalog.jsp"%>
            <div class="catalog-content" id="catalog-content">
                <!-- card content -->
            </div>
        </div>

        <script>
            $(function () {
                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar, #content').toggleClass('active');
                });
            });
        </script>
    </body>
</html>
