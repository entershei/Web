<#import "common.ftl" as c>

<@c.page>
<div class="form-box">
    <div class="header">Article</div>
    <div class="body">
        <form method="post" action="/article">
            <input type="hidden" name="action" value="sendArticle">
            <div class="field">
                <div class="name">
                    <label for="title">Title</label>
                </div>
                <div class="value">
                    <input id="title" name="title" value="${title!}"/>
                </div>
            </div>
            <div class="field">
                <div class="name">
                    <label for="text">Your text</label>
                </div>
                <div class="value">
                    <input id="text" name="text" value="${text!}"/>
                </div>
            </div>
            <div class="button-field">
                <input type="submit" value="Send">
            </div>
            <#if error??>
            <div class="error">
                ${error}
            </div>
            </#if>
        </form>
    </div>
</div>
<div class="datatable">
    <div class="caption">All message</div>
    <table>
        <thead>
        <tr>
            <th>From</th>
            <th>To</th>
            <th>Test</th>
        </tr>
        </thead>
        <tbody>
        <#list talks as talk>
        <tr>
            <td>
                ${userService.find(talk.sourceUserId).getLogin()}
            </td>
            <td>
                ${userService.find(talk.targetUserId).getLogin()}
            </td>
            <td>
                ${talk.text}
            </td>
        </tr>
        </#list>
    </table>
</div>
</@c.page>
