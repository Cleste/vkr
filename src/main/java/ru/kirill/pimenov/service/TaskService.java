package ru.kirill.pimenov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirill.pimenov.mapper.TaskMapper;
import ru.kirill.pimenov.pojo.bo.InviteBO;
import ru.kirill.pimenov.pojo.bo.TaskBO;
import ru.kirill.pimenov.pojo.bo.UserDetailsBO;
import ru.kirill.pimenov.pojo.dto.TaskDTO;
import ru.kirill.pimenov.pojo.entity.Invite;
import ru.kirill.pimenov.pojo.entity.Task;
import ru.kirill.pimenov.pojo.entity.TaskMember;
import ru.kirill.pimenov.pojo.entity.TaskRole;
import ru.kirill.pimenov.pojo.entity.User;
import ru.kirill.pimenov.repository.InviteRepository;
import ru.kirill.pimenov.repository.TaskMemberRepository;
import ru.kirill.pimenov.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMemberRepository taskMemberRepository;

    private final InviteRepository inviteRepository;

    private final UserService userService;

    private final MailSenderService mailSenderService;

    public List<TaskBO> getAll(UUID userId) {
        List<Task> taskList = taskRepository.findAllByUserId(userId);

        return TaskMapper.INSTANCE.toTaskBO(taskList);
    }

    public TaskBO get(UUID taskId) {
        Task task = taskRepository.findById(taskId).get();

        return TaskMapper.INSTANCE.toTaskBO(task);
    }

    public TaskBO getByInvite(UUID inviteId) {
        Invite invite = inviteRepository.findById(inviteId).get();
        Task task = invite.getTask();
        return TaskMapper.INSTANCE.toTaskBO(task);
    }

    public List<TaskBO> add(UserDetailsBO userDetailsBO, TaskDTO taskDTO) {
        User user = userService.getById(userDetailsBO.getId());
        Task task = TaskMapper.INSTANCE.fromTaskDTO(taskDTO);
        Task savedTask = taskRepository.save(task);
        TaskMember taskMember = new TaskMember(TaskRole.AUTHOR, user, savedTask);
        taskMemberRepository.save(taskMember);
        return getAll(userDetailsBO.getId());
    }

    public void sendInvite(UUID taskId, String email, UserDetailsBO userDetailsBO) {
        User user = userService.getById(userDetailsBO.getId());
        Task task = taskRepository.findById(taskId).get();
        Invite newInvite = new Invite(email, task);
        Invite invite = inviteRepository.save(newInvite);
        mailSenderService.sendInvite(invite.getId(), email, user);
    }

    public void addSubTask(UUID taskId, UserDetailsBO userDetailsBO, TaskDTO taskDTO) {
        User user = userService.getById(userDetailsBO.getId());
        Task task = taskRepository.findById(taskId).get();

        Task newTask = TaskMapper.INSTANCE.fromTaskDTO(taskDTO);
        newTask.setMaster(task);
        Task savedTask = taskRepository.save(newTask);
        TaskMember taskMember = new TaskMember(TaskRole.AUTHOR, user, savedTask);
        taskMemberRepository.save(taskMember);
    }

    public void reactInvite(UUID inviteId, boolean reaction) {
        Invite invite = inviteRepository.findById(inviteId).get();
        Task task = invite.getTask();
        User user = userService.findByEmail(invite.getEmail());
        TaskMember taskMember = new TaskMember(TaskRole.MEMBER, user, task);
        taskMemberRepository.save(taskMember);
        inviteRepository.delete(invite);
    }

    public List<InviteBO> getInvites(String email) {
        List<Invite> invites = inviteRepository.findAllByEmail(email);
        return TaskMapper.INSTANCE.toInviteBO(invites);
    }
}
