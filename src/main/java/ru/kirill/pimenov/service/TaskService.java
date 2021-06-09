package ru.kirill.pimenov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirill.pimenov.mapper.TaskMapper;
import ru.kirill.pimenov.pojo.bo.TaskBO;
import ru.kirill.pimenov.pojo.bo.UserDetailsBO;
import ru.kirill.pimenov.pojo.dto.TaskDTO;
import ru.kirill.pimenov.pojo.entity.Task;
import ru.kirill.pimenov.pojo.entity.TaskMember;
import ru.kirill.pimenov.pojo.entity.TaskRole;
import ru.kirill.pimenov.pojo.entity.User;
import ru.kirill.pimenov.repository.TaskMemberRepository;
import ru.kirill.pimenov.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMemberRepository taskMemberRepository;

    private final UserService userService;

    public List<TaskBO> get(UUID userId) {
        List<Task> taskList = taskRepository.findAllByUserId(userId);

        return TaskMapper.INSTANCE.toTaskBO(taskList);
    }

    public List<TaskBO> add(UserDetailsBO userDetailsBO, TaskDTO taskDTO) {
        User user = userService.getById(userDetailsBO.getId());
        Task task = TaskMapper.INSTANCE.fromTaskDTO(taskDTO);
        Task savedTask = taskRepository.save(task);
        TaskMember taskMember = new TaskMember(TaskRole.AUTHOR, user, savedTask);
        taskMemberRepository.save(taskMember);
        return get(userDetailsBO.getId());
    }

}
