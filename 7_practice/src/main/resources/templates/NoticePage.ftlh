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
<div class="notice form-box">
    <div class="header">Notice</div>
    <div class="body">
        <form method="post" action="/notice">
            <div class="field">
                <textarea class="text-area" name="content" id="content" cols="49" rows="17"
                          placeholder="Your notice">${noticeForm.content!}</textarea>
                <@error 'noticeForm.content'/>
            </div>
            <div class="button-field">
                <input type="submit" value="Add notice">
            </div>
        </form>
    </div>
</div>
</@c.page>
