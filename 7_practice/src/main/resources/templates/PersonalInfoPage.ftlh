<#import "./common.ftlh" as c>

<@c.page>
    <#if !user_information??>
        No such user
    <#else>
<div class="datatable">
    <div class="caption">Personal Information about ${user_information.login}</div>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Created At</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${user_information.id}</td>
            <td>${user_information.login}</td>
            <td>${user_information.creationTime}</td>
        </tr>
        </tbody>
    </table>
</div>
    </#if>
</@c.page>
