<#-- @ftlvariable name="listNews" type="ru.itmo.webmail.model.domain.NewsFront[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.webmail.model.domain.User" -->

<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Codeforces</title>
    <link rel="stylesheet" type="text/css" href="/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/form.css">
    <link rel="stylesheet" type="text/css" href="/css/datatable.css">
</head>
<body>
<header>
    <a href="/"><img src="/img/logo.png" alt="Codeforces" title="Codeforces"/></a>
    <div class="languages">
        <a href="#"><img src="/img/gb.png" alt="In English" title="In English"/></a>
        <a href="#"><img src="/img/ru.png" alt="In Russian" title="In Russian"/></a>
    </div>
    <#if user??>
    <div class="authorized-box">
        ${user.login}
        |
        <a href="/addNews">Add News</a>
        |
        <a href="/logout">Logout</a>
    </div>
    <#else>
    <div class="enter-or-register-box">
        <a href="/enter">Enter</a>
        |
        <a href="/register">Register</a>
    </div>
    </#if>
    <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/top">Top</a></li>
            <li><a href="/contests">Contests</a></li>
            <li><a href="/gym">Gym</a></li>
            <li><a href="/problemset">Problemset</a></li>
            <li><a href="/groups">Groups</a></li>
            <li><a href="/rating">Rating</a></li>
        </ul>
    </nav>
</header>
<div class="middle">
    <aside>
        <section>
            <div class="header">
                Pay attention
            </div>
            <div class="body">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ducimus enim facere impedit nobis,
                nulla placeat quam suscipit unde voluptatibus.
            </div>
            <div class="footer">
                <div class="user-count">
                    ${userCount} users
                </div>
                <a href="/users">View all</a>
            </div>
        </section>
        <section>
            <div class="header">
                News
            </div>
            <div class="body">
                <#list listNews as news>
                    <div class="news">
                        ${news.login} →
                        ${news.text}
                    </div>
                </#list>
            </div>
        </section>

    </aside>
    <main>
        <#nested/>
    </main>
</div>
<footer>
    <a href="#">Codeforces</a> &copy; 2010-2018 by Mike Mirzayanov ${text!}
</footer>
</body>
</html>
</#macro>
