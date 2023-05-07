package com.springboot.social_media.posts;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.social_media.user.UserEntity;

import jakarta.transaction.Transactional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

	public List<PostEntity> findByUserId(Long user_id);

	public PostEntity findByUserIdAndId(Long user_id, Long id);

	@Transactional
	@Modifying
	public void deleteByUserAndId(UserEntity user, Long id);

}
