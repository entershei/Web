<#import "common.ftl" as c>

<@c.page>
    <form method="post" action="/addNews" id="submit-news-form">
        <input type="hidden" name="action" value="addNews">
        <label for="text">News Text</label>
        <textarea name="text" form="submit-news-form" title="News Text"></textarea>
        <input type="submit" value="Submit">
    </form>
</@c.page>
