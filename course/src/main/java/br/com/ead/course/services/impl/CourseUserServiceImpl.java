package br.com.ead.course.services.impl;

import br.com.ead.course.repositories.CourseUserRespository;
import br.com.ead.course.services.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseUserServiceImpl implements CourseUserService {

    @Autowired
    CourseUserRespository courseUserRespository;
}
