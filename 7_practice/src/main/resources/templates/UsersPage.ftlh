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
            <div class="field">
                <div type="hidden" class="name">
                    <label for="changeDisableForm">changeDisableForm</label>
                </div>
                <div class="value"
                    <input type="hidden" name="changeDisableForm.userId" value="${user.id!}">
                </div>
            <@error 'changeDisableForm.userId'/>
            </div>
            <#if user.disabled??>
                    <form method="post" action="/changeDisable">
                        <div class="button-field">
                            <input type="submit" value="Enable">
                        </div>
                    </form>
            <#else>
                    <form method="post" action="/changeDisable">
                        <div class="button-field">
                            <input type="submit" value="Disable">
                        </div>
                    </form>
            </#if>
        </td>
    </tr>
    </#list>
        </tbody>
    </table>
</div>
</@c.page>
