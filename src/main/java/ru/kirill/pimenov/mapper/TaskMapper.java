package ru.kirill.pimenov.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.kirill.pimenov.pojo.bo.InviteBO;
import ru.kirill.pimenov.pojo.bo.MemberBO;
import ru.kirill.pimenov.pojo.bo.TaskBO;
import ru.kirill.pimenov.pojo.dto.TaskDTO;
import ru.kirill.pimenov.pojo.entity.Invite;
import ru.kirill.pimenov.pojo.entity.Task;
import ru.kirill.pimenov.pojo.entity.TaskMember;
import ru.kirill.pimenov.pojo.entity.TaskRole;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class TaskMapper {

    public static TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    public abstract List<TaskBO> toTaskBO(List<Task> taskList);

    public abstract Task fromTaskDTO(TaskDTO taskDTO);

    public abstract TaskBO toTaskBO(Task task);

    @AfterMapping
    protected void addTaskMembers(Task task, @MappingTarget TaskBO taskBO) {
        List<TaskMember> taskMembers = task.getTaskMembers();

        List<MemberBO> members = taskMembers.stream().filter(tM -> !tM.getTaskRole().equals(TaskRole.AUTHOR))
                .map(taskMember -> new MemberBO(taskMember.getUser().getEmail(), taskMember.getUser().getId()))
                .collect(Collectors.toList());
        taskBO.getMemberBOs().addAll(members);

        TaskMember taskMember = taskMembers.stream().filter(tM -> tM.getTaskRole().equals(TaskRole.AUTHOR)).findFirst().orElse(null);

        if (taskMember == null) {
            return;
        }

        taskBO.setAuthor(new MemberBO(taskMember.getUser().getEmail(), taskMember.getUser().getId()));
    }

    public abstract List<InviteBO> toInviteBO(List<Invite> invites);
}
