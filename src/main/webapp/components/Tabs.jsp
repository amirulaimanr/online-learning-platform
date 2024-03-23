<<<<<<< HEAD
<!DOCTYPE html>
<html>
    <head>
        <script>
            $(document).ready(function() {
                fetchData();
            });

            function fetchData() {
                $.ajax({
                    url: "/FetchCategoryCourseServlet?action=category_course",
                    dataType: "json",
                    success: function(data) {
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
                    error: function(jqXHR, textStatus, errorThrown) {
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
                contentHtml += '<div class="album py-5">';
                contentHtml += '<div class="container">';
                contentHtml += '<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">';

                for (var i = 0; i < 3; i++) {
                    if (i < courses.length) {
                        var course = courses[i];
                        contentHtml += '<div class="col">';
                        contentHtml += '<div class="card shadow-sm card-box">';
                        contentHtml += '<div class="card-fix-size">';
                        contentHtml += '<div>';
                        contentHtml += '<div class="card-header"> Free course </div>';
                        contentHtml += '<div class="card-body flex-column d-flex justify-content-between">';
                        contentHtml += '<h5 class="card-title">' + course.name + '</h5>';
                        contentHtml += '<p class="card-text">' + course.description + '</p>';
                        contentHtml += '<div class="flex-row d-flex justify-content-between">';
                        contentHtml += '<div class="" style="font-weight: 400"><i class="fa-solid fa-signal me-2"></i>' + course.difficulties + '</div>';
                        contentHtml += '<div class="" style="font-weight: 400"><b>' + course.duration + '</b> hours</div>';
                        contentHtml += '</div>';
                        contentHtml += '</div>';
                        contentHtml += '</div>';
                        contentHtml += '</div>';
                        contentHtml += '</div>';
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
=======

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.skillspark.onlinelearningplatform.dao.DatabaseConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<html>

>>>>>>> 0537eda47e50268997db806c1045fc0a39680beb
    <body>
        <%
            ArrayList<Integer> itemList = new ArrayList();
            DatabaseConnection dbConnection = new DatabaseConnection();
            String sql = "SELECT * FROM categories ";

            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;
        %>
        <nav>
            <div class="nav nav-tabs justify-content-center" id="nav-tab" role="tablist">
<<<<<<< HEAD
                <!-- Tab buttons -->
=======
                <%
                    while (resultSet.next()) {
                        int id = Integer.parseInt(resultSet.getString("id"));
                        String name = resultSet.getString("name");
                        itemList.add(id);

                %>
                <button class="nav-link  <% if(count == 0){ %> show active <% } %>" id="nav-home-tab-<%= count%>" o data-bs-toggle="tab" data-bs-target="#nav-home-<%= count%>"
                        type="button" role="tab" aria-controls="nav-home" aria-selected="true"><%= name%>
                </button>
                <%
                        count++;
                    }

                %>
>>>>>>> 0537eda47e50268997db806c1045fc0a39680beb
            </div>
        </nav>

        <div class="tab-content" id="nav-tabContent">
<<<<<<< HEAD
            <!-- Tab contents -->
=======
            <%
                int count2 = 0;
                for (int ids : itemList) {
            %>

            <div class="tab-pane fade <% if(count2 == 0){ %> show active <% } %>" id="nav-home-<%= count2%>" role="tabpanel" aria-labelledby="nav-home-tab-<%= count2%>" tabindex="0">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3  g-3">
                    <%

                        String sql2 = "SELECT name,description,difficulties,durations FROM courses WHERE category_id=? LIMIT 3";

                        PreparedStatement statement2 = dbConnection.getConnection().prepareStatement(sql2);
                        statement2.setInt(1, ids);
                        ResultSet resultSet2 = statement2.executeQuery();

                        while (resultSet2.next()) {
                            String name = resultSet2.getString("name");
                            String description = resultSet2.getString("description");
                            String difficulties = resultSet2.getString("difficulties");
                            int duration = Integer.parseInt(resultSet2.getString("durations"));
                    %>
                    <div class="col mt-4">
                        <div class="card shadow-sm card-box card-fix-size ">
                            <div class="card-header"><%= name%></div>
                            <div class="card-body flex-column d-flex justify-content-between">
                                <h5 class="card-title"><%= name%> </h5>
                                <p class="card-text"><%= description%> </p>
                                <div class="flex-row d-flex justify-content-between">
                                    <div class="" style="font-weight: 400"><i class="fa-solid fa-signal"></i>
                                        <%= difficulties%>
                                    </div>
                                    <div class="" style="font-weight: 400"><b><%= duration%></b> hours</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>


            <%                    count2++;
                }
            %>
>>>>>>> 0537eda47e50268997db806c1045fc0a39680beb
        </div>


    </body>
</html>
