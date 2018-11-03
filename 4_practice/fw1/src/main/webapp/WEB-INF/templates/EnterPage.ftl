<#import "common.ftl" as c>

<@c.page>
<div class="form-box">
    <div class="header">Enter</div>
    <div class="body">
        <form method="post" action="/enter">
            <input type="hidden" name="action" value="enter">
            <div class="field">
                <div class="name">
                    <label for="login">Login</label>
                </div>
                <div class="value">
                    <input id="login" name="login" value="${login!}"/>
                </div>
            </div>
            <div class="field">
                <div class="name">
                    <label for="password">Password</label>
                </div>
                <div class="value">
                    <input id="password" type="password" name="password" value="${password!}"/>
                </div>
            </div>
            <div class="field">
                <div class="name">
                    <label for="passwordConfirmation">Password Confirmation</label>
                </div>
                <div class="value">
                    <input id="passwordConfirmation" type="password" name="passwordConfirmation" value="${passwordConfirmation!}"/>
                </div>
            </div>
            <div class="field">
                <div class="name">
                    <label for="email">Email</label>
                </div>
                <div class="value">
                    <input id="password" type="email" name="email" value="${email!}"/>
                </div>
            </div>
            <div class="button-field">
                <input type="submit" value="Register">
            </div>
            <#if error??>
            <div class="error">
                ${error}
            </div>
            </#if>
        </form>
    </div>
</div>
</@c.page>
