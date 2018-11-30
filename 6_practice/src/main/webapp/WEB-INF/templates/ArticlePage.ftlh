<#import "common.ftl" as c>

<@c.page>
<div class="article form-box">
    <div class="header">Article</div>
    <div class="body">
        <form method="post" action="/article">
            <input type="hidden" name="action" value="sendArticle">
            <div class="field">
                <input class="title-input" id="title"
                       placeholder="Your title"${title!}/>
            </div>
            <div class="field">
                <textarea class="text-area" id="text"
                          placeholder="Your article">${article!}</textarea>
            </div>
            <div class="button-field">
                <input type="submit" value="Send">
            </div>
            <div class="error"></div>
        </form>
    </div>
</div>
<script>
    $(function () {
        $(".article form").submit(function () {
            var title = $(this).find("input[id='title']").val();
            var text = $('textarea#text').val();
            var $error = $(this).find(".error").text("");
            $.post("", {action: "sendArticle", title: title, text: text}, function (response) {
                if (response["success"]) {
                    setMessage("You have sent article.");
                    document.location.href = "/";
                } else {
                    $error.text(response["error"]);
                }
            });
            return false;
        });
    })
</script>
</@c.page>
