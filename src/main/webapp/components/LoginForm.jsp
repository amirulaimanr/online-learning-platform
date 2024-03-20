<html>
    <body>
        <div class="screen">
            <div class="screen__content">
                <form action="/LoginServlet" method="post" class="login">
                    <div class="login__field">
                        <i class="login__icon fas fa-user"></i>
                        <input type="text" class="login__input" placeholder="Email" name="email">
                    </div>
                    <div class="login__field">
                        <i class="login__icon fas fa-lock"></i>
                        <input type="password" class="login__input" placeholder="Password" name="password">
                    </div>

                    <%-- error message here if unsuccesful login --%>
                    <%
                        if (request.getAttribute("errorMessage") != null) {
                    %>
                    <p class="error-message" style="color: #d50000; font-size: 14px">
                        <%= request.getAttribute("errorMessage") %>
                    </p>
                    <%
                        }
                    %>

                    <button class="button login__submit">
                        <span class="button__text">Log In Now</span>
                        <i class="button__icon fas fa-chevron-right"></i>
                    </button>
                </form>
                <div class="social-login">
                    <h3 class="pe-2 fs-5">log in via</h3>
                    <div class="social-icons">
                        <a href="#" class="social-login__icon fab fa-instagram"></a>
                        <a href="#" class="social-login__icon fab fa-facebook"></a>
                        <a href="#" class="social-login__icon fab fa-twitter"></a>
                    </div>
                </div>
            </div>
            <div class="screen__background">
                <span class="screen__background__shape screen__background__shape4"></span>
                <span class="screen__background__shape screen__background__shape3"></span>
                <span class="screen__background__shape screen__background__shape2"></span>
                <span class="screen__background__shape screen__background__shape1"></span>
            </div>
        </div>
    </body>
</html>
