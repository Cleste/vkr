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
        <#if task.memberBOs?? && (task.memberBOs?size > 0)>
            <div class="row">
                <div class="col mb-4">
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="text-primary fw-bold m-0">Участники задачи</h6>
                        </div>
                        <ul class="list-group list-group-flush">
                            <#list task.memberBOs as memberBO>
                                <li class="list-group-item">
                                    <div class="row align-items-center no-gutters">
                                        <div class="col me-2">
                                            <a href="/user/profile/${memberBO.id}"><strong>${memberBO.email}</strong></a>
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
    </div>
    <div style="margin-left: 24px;margin-right: -12px;">
        <button class="btn btn-primary" data-toggle="modal" data-target="#addMember" type="button"
                style="margin-right: 12px;">Добавить участника
        </button>
        <button class="btn btn-primary" data-toggle="modal" data-target="#addSubtask" type="button"
                style="margin-right: 12px;">Добавить подзадачу
        </button>
    </div>
    <div class="modal fade shadow" role="dialog" tabindex="-1" id="addMember">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"><strong>Добавить участника</strong></h4>
                    <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <div class="row">
                            <div class="col">
                                <div class="mb-3">
                                    <form action="/task/invite?taskId=${task.id}" method="post">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                        <label class="form-label" for="email"><strong>Электронная почта
                                                пользователя</strong></label><input class="form-control"
                                                                                    type="email" id="email"
                                                                                    name="email"
                                                                                    placeholder="user@example.com">
                                        <button class="btn btn-primary pull-right" type="submit"
                                                style="margin-top: 12px">
                                            <strong>Добавить</strong></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer"></div>
            </div>
        </div>
    </div>

    <div class="modal fade shadow" role="dialog" tabindex="-1" id="addSubtask">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"><strong>Добавить подзадачу</strong></h4>
                    <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/task/${task.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="row">
                            <div class="col">
                                <div class="mb-3"><label class="form-label"
                                                         for="last_name"><strong>Тема</strong></label><input
                                            class="form-control" type="text" id="theme" name="theme"></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="mb-3"><label class="form-label"
                                                         for="first_name"><strong>Описание</strong></label><input
                                            class="form-control" type="text" id="text" name="text"></div>
                            </div>
                        </div>
                        <div class="text-right">
                            <button class="btn btn-primary pull-right" type="submit"><strong>Добавить</strong></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<#--    <div class="container">&ndash;&gt;-->
<#--        <a class="btn btn-primary" data-toggle="collapse"-->
<#--           href="#collapseAddMember" role="button" aria-expanded="false"-->
<#--           aria-controls="collapseExample">Добавить участника-->
<#--        </a>-->
<#--        <a class="btn btn-primary" data-toggle="collapse"-->
<#--           href="#collapseAddSubTask" role="button" aria-expanded="false"-->
<#--           aria-controls="collapseExample">Добавить подзадачу-->
<#--        </a>-->
<#--        <div class="collapse" id="collapseAddMember">-->
<#--            <div class="form-group mt-3">-->
<#--                <form action="/task/invite?taskId=${task.id}" method="post">-->
<#--                    <div class="form-group">-->
<#--                        <input type="email" class="form-control ${(emailError??)?string('is-invalid', '')}"-->
<#--                               name="email" placeholder="Почта пользователя"/>-->
<#--                        <#if tagError??>-->
<#--                            <div class="invalid-feedback">-->
<#--                                ${emailError}-->
<#--                            </div>-->
<#--                        </#if>-->
<#--                    </div>-->
<#--                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--                    <div class="form-group">-->
<#--                        <button type="submit" class="btn btn-primary">Добавить</button>-->
<#--                    </div>-->
<#--                </form>-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="collapse" id="collapseAddSubTask">-->
<#--            <div class="form-group mt-3">-->
<#--                <form action="/task/${task.id}" method="post">-->
<#--                    <div class="form-group">-->
<#--                        <input type="text" class="form-control ${(tagError??)?string('is-invalid', '')}"-->
<#--                               value="<#if taskDTO??>${taskDTO.theme}</#if>" name="theme" placeholder="Тема"/>-->
<#--                        <#if tagError??>-->
<#--                            <div class="invalid-feedback">-->
<#--                                ${themeError}-->
<#--                            </div>-->
<#--                        </#if>-->
<#--                    </div>-->
<#--                    <div class="form-group">-->
<#--                        <input type="text" class="form-control ${(textError??)?string('is-invalid', '')} mt-3"-->
<#--                               value="<#if taskDTO??>${taskDTO.text}</#if>" name="text" placeholder="Текст задачи"/>-->
<#--                        <#if textError??>-->
<#--                            <div class="invalid-feedback">-->
<#--                                ${textError}-->
<#--                            </div>-->
<#--                        </#if>-->
<#--                    </div>-->
<#--                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--                    <div class="form-group">-->
<#--                        <button type="submit" class="btn btn-primary">Создать</button>-->
<#--                    </div>-->
<#--                </form>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
<#--    <div class="container">-->
<#--        <h2>Тема задачи - ${task.theme}</h2><br/>-->
<#--        <h5>Описание задачи - ${task.text}</h5><br/>-->
<#--        <strong>Автор - ${task.authorEmail}</strong>-->
<#--    </div>-->
<#--    <#if task.subTasks?? && (task.subTasks?size > 0)>-->
<#--        <div class="container">-->
<#--            <h2>Подзадачи</h2>-->
<#--            <ol>-->
<#--                <#list task.subTasks as subTasks>-->
<#--                    <li><a href="/task/${subTasks.id}"><strong>${subTasks.theme}</strong></a></li>-->
<#--                </#list>-->
<#--            </ol>-->
<#--        </div>-->
<#--    </#if>-->
<#--    <div class="container">-->
<#--        <a class="btn btn-primary" data-toggle="collapse"-->
<#--           href="#collapseAddMember" role="button" aria-expanded="false"-->
<#--           aria-controls="collapseExample">Добавить участника-->
<#--        </a>-->
<#--        <a class="btn btn-primary" data-toggle="collapse"-->
<#--           href="#collapseAddSubTask" role="button" aria-expanded="false"-->
<#--           aria-controls="collapseExample">Добавить подзадачу-->
<#--        </a>-->
<#--        <div class="collapse" id="collapseAddMember">-->
<#--            <div class="form-group mt-3">-->
<#--                <form action="/task/invite?taskId=${task.id}" method="post">-->
<#--                    <div class="form-group">-->
<#--                        <input type="email" class="form-control ${(emailError??)?string('is-invalid', '')}"-->
<#--                               name="email" placeholder="Почта пользователя"/>-->
<#--                        <#if tagError??>-->
<#--                            <div class="invalid-feedback">-->
<#--                                ${emailError}-->
<#--                            </div>-->
<#--                        </#if>-->
<#--                    </div>-->
<#--                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--                    <div class="form-group">-->
<#--                        <button type="submit" class="btn btn-primary">Добавить</button>-->
<#--                    </div>-->
<#--                </form>-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="collapse" id="collapseAddSubTask">-->
<#--            <div class="form-group mt-3">-->
<#--                <form action="/task/${task.id}" method="post">-->
<#--                    <div class="form-group">-->
<#--                        <input type="text" class="form-control ${(tagError??)?string('is-invalid', '')}"-->
<#--                               value="<#if taskDTO??>${taskDTO.theme}</#if>" name="theme" placeholder="Тема"/>-->
<#--                        <#if tagError??>-->
<#--                            <div class="invalid-feedback">-->
<#--                                ${themeError}-->
<#--                            </div>-->
<#--                        </#if>-->
<#--                    </div>-->
<#--                    <div class="form-group">-->
<#--                        <input type="text" class="form-control ${(textError??)?string('is-invalid', '')} mt-3"-->
<#--                               value="<#if taskDTO??>${taskDTO.text}</#if>" name="text" placeholder="Текст задачи"/>-->
<#--                        <#if textError??>-->
<#--                            <div class="invalid-feedback">-->
<#--                                ${textError}-->
<#--                            </div>-->
<#--                        </#if>-->
<#--                    </div>-->
<#--                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--                    <div class="form-group">-->
<#--                        <button type="submit" class="btn btn-primary">Создать</button>-->
<#--                    </div>-->
<#--                </form>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
</@c.page>