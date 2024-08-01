package br.com.ead.authuser.service.impl;

import br.com.ead.authuser.repositories.UserCourseRepository;
import br.com.ead.authuser.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCourseServiceImpl implements UserCourseService {


    @Autowired
    UserCourseRepository userCourseRepository;



}
