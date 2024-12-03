package com.Varsha.varsha1.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Varsha.varsha1.model.ProfilePhoto;

public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Long> {
    Optional<ProfilePhoto> findByUserId(Long userId);
}

