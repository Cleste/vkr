package ru.kirill.pimenov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kirill.pimenov.pojo.entity.Task;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT t FROM Task AS t JOIN t.taskMembers as tm WHERE tm.user.id = :userId")
    List<Task> findAllByUserId(UUID userId);

}
