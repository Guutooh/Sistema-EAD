package com.ead.authuser.service.impl;

import com.ead.authuser.service.UtilsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilsServiceImpl implements UtilsService {

    String REQUEST_URI = "http://localhost:8082";

    public String createUrl(UUID userId, Pageable page) {

        return REQUEST_URI + "/courses?userId=" + userId + "&page=" + page.getPageNumber() + "&size="
                + page.getPageSize() + "&sort=" + page.getSort().toString().replaceAll(": ", ",");

    }
}
