package com.springboot.social_media.user;

import org.springframework.data.jpa.repository.JpaRepository;

//SpringDataJPA repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

}
