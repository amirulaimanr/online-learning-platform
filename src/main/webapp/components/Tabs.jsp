
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.skillspark.onlinelearningplatform.dao.DatabaseConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<html>

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
            </div>
        </nav>

        <div class="tab-content" id="nav-tabContent">
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
        </div>


    </body>
</html>
