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
import ru.kirill.pimenov.repository.TaskRepository;
import ru.kirill.pimenov.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public List<TaskBO> getTasks (UserDetailsBO user) {
        List<Task> taskList = taskRepository.findAllByUserId(user.getId());

        return TaskMapper.INSTANCE.toTaskBO(taskList);
    }

    public List<TaskBO> add(UserDetailsBO userDetailsBO, TaskDTO taskDTO) {
        User user = userRepository.findById(userDetailsBO.getId()).get();
        Task task = TaskMapper.INSTANCE.fromTaskDTO(taskDTO);
        TaskMember taskMember = new TaskMember(TaskRole.AUTHOR, user, task);
        task.getTaskMembers().add(taskMember);
        taskRepository.save(task);
        return getTasks(userDetailsBO);
    }
}
