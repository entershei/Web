<#-- @ftlvariable name="users" type="ru.itmo.webmail.model.domain.User[]" -->
<#-- @ftlvariable name="talks" type="ru.itmo.webmail.model.domain.Talk[]" -->

<#import "common.ftl" as c>

<@c.page>
<div class="form-box">
    <div class="header">Talks</div>
    <div class="body">
        <form method="post" action="/talks">
            <input type="hidden" name="action" value="sendMessage">
            <div class="field">
                <div class="name">
                    <label for="text">Your message</label>
                </div>
                <div class="value">
                    <input id="text" name="text" value="${text!}"/>
                </div>
            </div>
            <div class="field">
                <div class="name">
                    <label for="loginTargetUser">Login target user</label>
                </div>
                <div class="value">
                    <input id="loginTargetUser" name="loginTargetUser" value="${loginTargetUser!}"/>
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
