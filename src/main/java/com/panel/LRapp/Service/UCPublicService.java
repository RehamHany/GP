package com.panel.LRapp.Service;

import com.panel.LRapp.Entity.UserChallengePrivate;
import com.panel.LRapp.Entity.UserChallengePublic;
import com.panel.LRapp.response.UCPrivateResponse;
import com.panel.LRapp.response.UCPublicResponse;

import java.util.List;

public interface UCPublicService {
    UCPublicResponse save(UserChallengePublic userChallengePublic);
    void delete(int id);
    UCPublicResponse update(UserChallengePublic userChallengePublic);
    UCPublicResponse findById(int id);

    List<UserChallengePublic> findByName(String name);

    List<UserChallengePublic> findAll();
}
