package ru.kirill.pimenov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirill.pimenov.pojo.entity.Invite;

import java.util.List;
import java.util.UUID;

@Repository
public interface InviteRepository extends JpaRepository<Invite, UUID> {
    List<Invite> findAllByEmail(String email);
}
