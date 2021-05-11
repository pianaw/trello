<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="/css/common.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js" charset="utf-8"></script>
    <script src="/js/talk-me.js"></script>
    <style>
        input[type=text]:not(.browser-default) {
            width: 300px; !important;
        }
        b {
            color: red;
        }
    </style>
</head>
<body style="background-color: lavender">
<#import "spring.ftl" as spring/>
<@spring.bind "userForm"/>
<header style="background-color: rgba(255, 255, 255, 0.5)">
    <div class="row" style="height: 70px">
        <div class="col s3"><a href="/"><img src="/img/logo.png" alt="Logo of Trello"
                                             style="margin: 15px; width: 125px"></a>
        </div>
        <div class="col s3"></div>
        <div class="col s3"></div>
        <div class="col s3" style="margin-top: 15px;">
            <a href="/auth/signin" style="color: dodgerblue; font-size: 18px;margin-left: 50px">Войти</a>
            <button class="trello-button" style="height: 40px; width: 130px; margin-left: 30px; font-size: 18px">
                Регистрация
            </button>
        </div>
    </div>
</header>
<div class="row">
    <div class="col s4"></div>
    <div class="col s4" style="margin-top: 5%; border-radius: 5px; background-color: rgba(255, 255, 255, 0.5)">
        <div class="center">
            <form action="/signup" method="POST" id="user">
                <h4>Регистрация</h4>
                <@spring.formInput "userForm.name" "placeholder = 'Name'" /><br>
                <@spring.showErrors "<br>"/><br>
                <@spring.formInput "userForm.email" "placeholder = 'Email'" /><br>
                <@spring.showErrors "<br>"/><br>
                <@spring.formInput "userForm.password" "placeholder = 'Password'"/><br>
                <@spring.showErrors "<br>"/><br>
                <button type="submit" class="trello-button"
                        style="height: 40px; width: 150px; margin-bottom: 20px; margin-top: 20px;font-size: 14px">
                    Зарегистрироваться
                </button>
            </form>
        </div>
    </div>
    <div class="col s4"></div>
</div>
</body>
</html>