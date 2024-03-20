<html>
    <body>
        <div class="vertical-nav" id="sidebar">
            <%@include file="/components/FilterBarResources.jsp" %>
        </div>

        <div class="flex-column explore-catalog p-5 align-items-start" id="content2">
            <div class="catalog-menu-btn">
                <button id="sidebarCollapse" type="button"
                        class="btn btn-dark bg-dark rounded-pill shadow-sm px-4 mb-4">
                    <small class="text-uppercase font-weight-bold">Resources Menu</small>
                </button>
            </div>
            <h2 style="font-weight: 600">Resources Catalog</h2>
            <h4 style="font-weight: 500; margin-top: 30px">Docs</h4>
            <%@ include file="/components/GridCatalog3.jsp" %>
            <h4 style="font-weight: 500; margin-top: 30px">CheatSheet</h4>
            <%@ include file="/components/GridCatalog3.jsp" %>
            <h4 style="font-weight: 500; margin-top: 30px">Articles</h4>
            <%@ include file="/components/GridCatalog3.jsp" %>
        </div>

        <script>
            $(function () {
                // Sidebar toggle behavior
                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar, #content2').toggleClass('active');
                });
            });
        </script>
    </body>
</html>
