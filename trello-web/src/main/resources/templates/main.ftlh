<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <title>Trello</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="/js/talk-me.js"></script>
    <style>
        A:hover {
            text-decoration: underline;
            color: dodgerblue;
        }

        .trello-button, .trello-button:focus {
            background-color: dodgerblue;
            color: white;
            border-radius: 5px;
            border-width: 0;
            text-align: center;
        }
    </style>
</head>
<body style="background-color: lavender">
<header style="background-color: rgba(255, 255, 255, 0.5)">
    <div class="row" style="height: 70px">
        <div class="col s3"><img src="/img/logo.png" alt="Logo of Trello" style="margin: 15px; width: 125px">
        </div>
        <div class="col s3"></div>
        <div class="col s3"></div>
        <div class="col s3" style="margin-top: 15px;">
            <a href="/auth/signin" style="color: dodgerblue; font-size: 18px;margin-left: 50px">Войти</a>
            <a href="/signup"><button class="trello-button" style="height: 40px; width: 130px; margin-left: 30px; font-size: 18px">
                Регистрация
            </button></a>
        </div>
    </div>
</header>
<div class="row" style="height: 80px;"></div>
<div class="row" style="height: 70px;">
    <div class="col s6"><img src="/img/tasks.png" style="height: 340px; margin: 20px"></div>
    <div class="col s6">
        <h3><b>Trello</b><br> помогает командам</h3>
        <div style="border-top:3px dotted black;width:400px;"></div>
        <h6>эффективно решать рабочие задачи.</h6>
    </div>
</div>
</body>
</html>