package com.javohir.lastproject.repository;

import com.javohir.lastproject.entity.photo.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
