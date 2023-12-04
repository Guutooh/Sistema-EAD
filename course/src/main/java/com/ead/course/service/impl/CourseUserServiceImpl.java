package com.ead.course.service.impl;

import com.ead.course.repository.CourseUserRespository;
import com.ead.course.service.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class CourseUserServiceImpl implements CourseUserService {

    final CourseUserRespository courseUserRespository;
}
