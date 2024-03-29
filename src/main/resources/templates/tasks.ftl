<#import "parts/common.ftl" as c>

<@c.page>
    <a class="btn btn-primary" data-toggle="collapse"
       href="#collapseAddTask" role="button" aria-expanded="false"
       aria-controls="collapseExample">Создать задачу
    </a>
    <div class="collapse" id="collapseAddTask">
        <div class="form-group mt-3">
            <form action="/task" method="post">
                <div class="form-group">
                    <input type="text" class="form-control ${(tagError??)?string('is-invalid', '')}"
                           value="<#if taskDTO??>${taskDTO.theme}</#if>" name="theme" placeholder="Тема"/>
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${themeError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')} mt-3"
                           value="<#if taskDTO??>${taskDTO.text}</#if>" name="text" placeholder="Текст задачи"/>
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Создать</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns" id="message-list">
        <#list tasks as task>
            <div class="card my-3" data-id="${task.id}">
                <div class="m-2">
                    <a href="/task/${task.id}"><strong>${task.theme}</strong></a><br/>
                    <i>${task.text}</i><br/>
                    <strong>${task.author.email}</strong>
                </div>
            </div>
        <#else>
           У вас еще нет задач
        </#list>
    </div>
</@c.page>