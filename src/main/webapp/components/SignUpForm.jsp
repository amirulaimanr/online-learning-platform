<html>
    <body>
        <div class="screen">
            <div class="screen__content">
                <form action="/SignUpServlet" method="post" class="login">
                    <select class="form-select form-size" aria-label="Role selected" name="roleId" required>
                        <option value="" disabled selected>Sign up as</option>
                        <option value="1">Student</option>
                        <option value="2">Tutor</option>
                    </select>
                    <div class="login__field">
                        <i class="login__icon fas fa-user"></i>
                        <input type="text" class="login__input" placeholder="Email" name="email" required>
                    </div>
                    <div class="login__field">
                        <i class="login__icon fas fa-lock"></i>
                        <input type="password" class="login__input" placeholder="Password" name="password" required
                        >
                    </div>
                    <button class="button login__submit">
                        <span class="button__text">Sign Up Now</span>
                        <i class="button__icon fas fa-chevron-right"></i>
                    </button>
                </form>
                <div class="social-login">
                    <h3 class="pe-2 fs-5">sign up via</h3>
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

        <script>
            function validateForm() {
                var selectElement = document.querySelector('select[name="roleId"]');
                var selectedValue = selectElement.value;

                if (selectedValue === "") {
                    alert("Please select a role.");
                    return false; // Prevent form submission
                }
                return true; // Allow form submission
            }
        </script>
    </body>
</html>
