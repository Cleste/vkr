<#import "parts/common.ftl" as c>

<@c.page>
    <div class="card-columns" id="message-list">
        <#list invites as invite>
            <div class="card my-3" data-id="${invite.id}">
                <div class="m-2">
                    <a href="/task/invite?inviteId=${invite.id}"><strong>${invite.task.theme}</strong></a><br/>
                    <i>${invite.task.text}</i><br/>
                    <strong>${invite.task.author.email}</strong>
                </div>
            </div>
        <#else>
           У вас еще нет приглашений
        </#list>
    </div>
</@c.page>