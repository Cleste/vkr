package ru.kirill.pimenov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.kirill.pimenov.pojo.bo.TaskBO;
import ru.kirill.pimenov.pojo.dto.TaskDTO;
import ru.kirill.pimenov.pojo.entity.Task;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    List<TaskBO> toTaskBO(List<Task> taskList);

    Task fromTaskDTO(TaskDTO taskDTO);

}
