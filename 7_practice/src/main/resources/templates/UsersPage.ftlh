<#import "/spring.ftl" as spring>
<#import "./common.ftlh" as c>

<#macro error field>
    <@spring.bind field/>
    <#list spring.status.errorMessages as error>
        <div class="error">${error}</div>
        <#break/>
    </#list>
</#macro>

<@c.page>
<div class="datatable">
    <div class="caption">Users</div>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Created At</th>
            <th>Disable or enable</th>
        </tr>
        </thead>
        <tbody>
    <#list users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.creationTime}</td>
        <td>
            <#if user.disabled>
                    <form method="post" action="/changeDisable">
                        <input type="hidden" name="userId" value="${user.id}">
                        <input type="submit" value="Enable">
                    </form>
            <#else>
                    <form method="post" action="/changeDisable">
                        <input type="hidden" name="userId" value="${user.id}">
                        <input type="submit" value="Disable">
                    </form>
            </#if>
        </td>
    </tr>
    </#list>
        </tbody>
    </table>
</div>
</@c.page>
