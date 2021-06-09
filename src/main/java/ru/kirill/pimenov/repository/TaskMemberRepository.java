package ru.kirill.pimenov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirill.pimenov.pojo.entity.TaskMember;

import java.util.UUID;

@Repository
public interface TaskMemberRepository extends JpaRepository<TaskMember, UUID> {
}
