<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Main Page</title>
        <%@ include file="/styles/bootstrap.jsp" %>
        <%@ include file="/styles/compile.jsp" %>
    </head>
    <body>
        <%@ include file="/components/TopBarLogin.jsp" %>

        <div class="container-l layout-background-blue layout-size">
            <div class="flex-container-carousel">
                <div class="container">
                    <%@include file="/components/Carousel.jsp" %>
                </div>
                <div class="container">
                    <div class="flex-column">
                        <div class="signup-title">
                            Join the millions learning to code with SkillSpark for free
                        </div>
                        <%@ include file="/components/SignUpForm.jsp" %>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-l">
            <div class="flex-container">
                <div class="flex-column">
                    <div class="popular-course-title">
                        Start Learning
                    </div>
                    <div class="popular-course-subtitle">
                        Popular Course
                    </div>
                    <div class="container pt-5" id="contentContainer">
                        <%-- Add courses component here --%>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-l layout-colour-gradient layout-size">
            <div class="flex-column-auto pt-5">
                <div class="community-stories-title">
                    Community Stories
                </div>
                <div class="community-stories-subtitle">
                    Read inspiring stories from the SkillSpark community.
                </div>
            </div>
            <div class="flex-container flex-size">
                <div class="card img-card mb-5" style="width: 15rem;">
                    <img src="https://images.ctfassets.net/go6kr6r0ykrq/Sk3Knek82fDxSX5eZqdzX/bac59c63ff86d8d52ef9e1559e8eb381/De-Shaun-Broadnax.jpeg"
                         class="card-img-top" alt="..." style="height: 220px">
                    <div class="card-body">
                        <p class="card-text">"Highly recommend this course for aspiring data scientists. You'll become a
                            visualization tools expert, even with no prior experience." - Phillip W
                        </p>
                    </div>
                </div>

                <div class="card img-card mb-5" style="width: 15rem;">
                    <img src="https://images.ctfassets.net/go6kr6r0ykrq/IEAxMFzasjkSr11XsNGso/ef9c1fcd18c5abf06e8064dc5352060c/Julia-Jubileu.jpeg"
                         class="card-img-top" alt="..." style="height: 220px">
                    <div class="card-body">
                        <p class="card-text">"This course helped me freshen up on my product manager skills and land
                            a job at Facebook! Thanks guys :)" - Julia
                        </p>
                    </div>
                </div>

                <div class="card img-card mb-5" style="width: 15rem;">
                    <img src="https://images.ctfassets.net/go6kr6r0ykrq/2ao4ZIQinCIgAt4UuGNR3z/b5b0608e9ae47b42ba6aa5e18e97411e/SerenaIsone1.jpeg"
                         class="card-img-top" alt="..." style="height: 220px">
                    <div class="card-body">
                        <p class="card-text">"After completing this course, I passed my exam and became an AWS Certified
                            Cloud Practitioner! The content matched the exam perfectly." - Serena
                        </p>
                    </div>
                </div>
                <div class="card img-card mb-5" style="width: 15rem;">
                    <img src="https://images.ctfassets.net/go6kr6r0ykrq/6zlCvqSouWnN7NGDTvidvT/3d4f8caef82cdaef8017edfd7e57f94f/Cristian-Tera__n.jpeg"
                         class="card-img-top" alt="..." style="height: 220px">
                    <div class="card-body">
                        <p class="card-text">"Exceptional experience! The courses were easy to follow.Can't wait to
                            explore more!" - CTara
                        </p>
                    </div>
                </div>

            </div>
        </div>

        <div class="container-l layout-colour-dark-blue layout-size">
            <div class="flex-container">
                <div class="container">
                    <img src="styles/img/human_learning2.png" style="width: 100%">
                </div>
                <div class="container container d-flex align-items-center">
                    <div class="flex-column-auto">
                        <div class="start-title">
                            Start For Free
                        </div>
                        <div class="start-subtitle mb-5">
                            If you've made it this far, you must be at least a little curious. Sign up and take the
                            first step toward your goals.
                        </div>
                        <a class="btn btn-outline-success mt-1" href="pages/SignUpPage.jsp"><b>Sign Up</b></a>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/components/Footer.jsp" %>
    </body>
</html>
