package com.Varsha.varsha1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Varsha.varsha1.service.ProfilePhotoService;

@RestController
@RequestMapping("/profile-photo")
public class ProfilePhotoController {

    @Autowired
    private ProfilePhotoService profilePhotoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProfilePhoto(@RequestParam Long userId, @RequestParam MultipartFile file) {
        try {
            profilePhotoService.saveProfilePhoto(userId, file);
            return ResponseEntity.ok("Profile photo uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading profile photo: " + e.getMessage());
        }
    }

            @GetMapping("/view")
        public ResponseEntity<byte[]> viewProfilePhoto(@RequestParam Long userId) {
            return profilePhotoService.getProfilePhotoByUserId(userId)
                    .map(profilePhoto -> ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG)
                            .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                            .body(profilePhoto.getPhoto()))
                    .orElse(ResponseEntity.notFound().build());
        }

}
