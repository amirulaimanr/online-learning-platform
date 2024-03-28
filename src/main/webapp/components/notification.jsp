<div>
    <%
        if (session.getAttribute("success") != null) {
    %>
    <script>
        Swal.fire({
            title: "Success!",
            text: "  <%= session.getAttribute("success")%>",
            icon: "success"
        });
    </script>
    <%
        }
        session.removeAttribute("success");
    %>
    <%
        if (session.getAttribute("failed") != null) {
    %>
    <script>
        Swal.fire({
            title: "Error!",
            text: "  <%= session.getAttribute("failed")%>",
            icon: "error"
        });
    </script>
    <%
        }
        session.removeAttribute("failed");
    %>
</div>
