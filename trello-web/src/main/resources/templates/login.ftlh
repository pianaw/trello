<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="google-signin-client_id" content="35631149409-53hij26v5acl7aumj6iqd31b27m80u4i.apps.googleusercontent.com">
    <title>Вход</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="/css/common.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="/js/talk-me.js"></script>
    <script>
        function onSignIn(googleUser) {
            var id_token = googleUser.getAuthResponse().id_token;
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/api/auth/signin');
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onload = function() {
                console.log('Signed in as: ' + xhr.responseText);
            };
            xhr.send('idtoken=' + id_token);
        }
    </script>
</head>
<body style="background-color: lavender">
<header style="background-color: rgba(255, 255, 255, 0.5)">
    <div class="row" style="height: 70px">
        <div class="col s3"><a href="/"><img src="/img/logo.png" alt="Logo of Trello"
                                                     style="margin: 15px; width: 125px"></a>
        </div>
        <div class="col s3"></div>
        <div class="col s3"></div>
        <div class="col s3" style="margin-top: 15px;">
            <a href="/signin" style="color: dodgerblue; font-size: 18px;margin-left: 50px">Войти</a>
            <a href="/signup"><button  class="trello-button" style="height: 40px; width: 130px; margin-left: 30px; font-size: 18px">
                Регистрация
                </button></a>
        </div>
    </div>
</header>
<div class="row">
    <div class="col s4"></div>
    <div class="col s4" style="margin-top: 5%; border-radius: 5px; background-color: rgba(255, 255, 255, 0.5)">
        <div class="center">
            <form action="signin" method="post">
            <h4>Вход</h4>
            <input placeholder="Email" style="width: 300px" id="email" name="email">
            <input type="password" placeholder="Password" style="width: 300px" id="password" name="password">
            <button type="submit" class="trello-button"
                    style="height: 35px; width: 140px; margin-bottom: 20px; margin-top: 20px;font-size: 18px">Войти
            </button>
            </form>
        </div>
        <div class="container">
            With Google: <a href="/oauth2/authorization/google">click here</a>
        </div>
    </div>
    <div class="col s4"></div>
</div>
</body>
</html>