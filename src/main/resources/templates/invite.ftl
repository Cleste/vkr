<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container-fluid">
    <div class="d-sm-flex justify-content-between align-items-center mb-4">
        <h3 class="text-dark mb-0"><strong>${task.theme}</strong></h3>

        <#if task.author??>
            <a class="btn btn-primary btn-sm d-none d-sm-inline-block" role="button"
               href="/user/profile/${task.author.id}"><strong>Автор: ${task.author.email}</strong></a>
        </#if>
    </div>
    <div class="row">
        <div class="col">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="text-primary m-0 fw-bold">Описание задачи</h6>
                </div>
                <div class="card-body">
                    <p class="m-0">${task.text}</p>
                </div>
            </div>
        </div>
    </div>
    <#if task.subTasks?? && (task.subTasks?size > 0)>
        <div class="row">
            <div class="col mb-4">
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="text-primary fw-bold m-0">Подзадачи</h6>
                    </div>
                    <ul class="list-group list-group-flush">
                        <#list task.subTasks as subTasks>
                            <li class="list-group-item">
                                <div class="row align-items-center no-gutters">
                                    <div class="col me-2">
                                        <a href="/task/${subTasks.id}"><strong>${subTasks.theme}</strong></a>
                                    </div>
                                </div>
                            </li>
                        </#list>
                    </ul>
                    <div></div>
                </div>
            </div>
        </div>
    </#if>
    <div class="container">
        <div class="container">
            <form action="/task/invite/react" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="inviteId" value="${inviteId}"/>
                <input type="hidden" name="reaction" value="true"/>
                <button class="btn btn-primary" type="submit">Принять</button>
            </form>
        </div>
        <div class="container">
            <form action="/task/invite/react" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="inviteId" value="${inviteId}"/>
                <input type="hidden" name="reaction" value="false"/>
                <button class="btn btn-primary" type="submit">Отказаться</button>
            </form>
        </div>
    </div>
</@c.page>