package ru.itmo.wm4.service;

import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import ru.itmo.wm4.domain.Notice;
import ru.itmo.wm4.domain.User;
import ru.itmo.wm4.form.NoticeCredentials;
import ru.itmo.wm4.form.UserCredentials;
import ru.itmo.wm4.repository.NoticeRepository;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public Notice addNotice(NoticeCredentials noticeForm) {
        Notice notice = new Notice();
        notice.setContent(noticeForm.getContent());
        noticeRepository.save(notice);
        return notice;
    }

    public Notice findById(Long noticeId) {
        return noticeId == null ? null : noticeRepository.findById(noticeId).orElse(null);
    }

    public List<Notice> findAll() {
        return noticeRepository.findAllByOrderByCreationTimeDesc();
    }
}
