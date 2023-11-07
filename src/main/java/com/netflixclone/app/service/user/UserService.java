package com.netflixclone.app.service.user;

import com.netflixclone.app.exception.CustomException;
import com.netflixclone.app.payload.request.SignupRequest;
import jakarta.transaction.Transactional;

public interface UserService {

  void registerUser(SignupRequest signupRequest) throws CustomException;
}
