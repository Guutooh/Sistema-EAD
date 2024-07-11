package br.com.ead.course.services.impl;

import br.com.ead.course.repositories.CourseUserRespository;
import br.com.ead.course.services.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class CourseUserServiceImpl implements CourseUserService {

    final CourseUserRespository courseUserRespository;
}
