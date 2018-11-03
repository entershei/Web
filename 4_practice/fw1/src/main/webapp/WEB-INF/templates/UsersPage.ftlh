<#-- @ftlvariable name="users" type="ru.itmo.webmail.model.domain.User[]" -->
<#import "common.ftl" as c>

<@c.page>
<div class="datatable">
    <div class="caption">Users</div>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Email</th>
            <th>Login</th>
            <th>id</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>
                    ${user_index + 1}
                </td>
                <td>
                    ${user.email!"No email, old user"}
                </td>
                <td>
                    ${user.login}
                </td>
                <td>
                    ${user.id}
                </td>
            </tr>
        </#list>
    </table>
</div>
</@c.page>
