package br.com.ead.authuser.service;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UtilsService {
    String createUrlGetAllCoursesByUser(UUID userId, Pageable pageable);
}
