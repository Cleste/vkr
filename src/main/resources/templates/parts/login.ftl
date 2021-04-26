<#include "security.ftl">
<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Электронная почта:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="some@some.com"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        <#else>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Электронная почта:</label>
            <div class="col-sm-6">
                <input type="email" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="some@some.com"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        </#if>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm>
            <a href="/registration">
                Зарегистрироваться
            </a>
        </#if>
        <button class="btn btn-primary" type="submit">
            <#if isRegisterForm>
                Зарегистрироваться
            <#else>
                Войти
            </#if>
        </button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit"><#if user??>Выйти<#else>Войти</#if></button>
    </form>
</#macro>