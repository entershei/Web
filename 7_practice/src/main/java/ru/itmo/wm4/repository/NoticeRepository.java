package ru.itmo.wm4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.wm4.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value = "SELECT * FROM notice WHERE id=?1)", nativeQuery = true)
    Notice findById(long noticeId);
}
