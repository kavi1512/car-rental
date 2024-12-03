package com.Varsha.varsha1.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Varsha.varsha1.model.ProfilePhoto;
import com.Varsha.varsha1.repository.ProfilePhotoRepository;

@Service
public class ProfilePhotoService {

    @Autowired
    private ProfilePhotoRepository profilePhotoRepository;

    public ProfilePhoto saveProfilePhoto(Long userId, MultipartFile file) throws IOException {
        ProfilePhoto profilePhoto = profilePhotoRepository.findByUserId(userId).orElse(new ProfilePhoto());
        profilePhoto.setUserId(userId);
        profilePhoto.setPhoto(file.getBytes());
        return profilePhotoRepository.save(profilePhoto);
    }

    public Optional<ProfilePhoto> getProfilePhotoByUserId(Long userId) {
        return profilePhotoRepository.findByUserId(userId);
    }
}

