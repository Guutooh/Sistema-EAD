package com.ead.authuser.service.impl;

import com.ead.authuser.model.UserCourseModel;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.repository.UserCourseRepository;
import com.ead.authuser.service.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserCourseServiceImpl implements UserCourseService {


     final UserCourseRepository userCourseRepository;

     @Override
     public boolean existsByUserAndCourseId(UserModel userModel, UUID courseId) {
          return userCourseRepository.existsByUserAndCourseId(userModel, courseId);
     }

     @Override
     public UserCourseModel save(UserCourseModel userCourseModel) {
          return userCourseRepository.save(userCourseModel);
     }
}
