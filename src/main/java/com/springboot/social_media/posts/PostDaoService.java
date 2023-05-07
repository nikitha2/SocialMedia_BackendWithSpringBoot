package com.springboot.social_media.posts;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springboot.social_media.user.UserDaoService;
import com.springboot.social_media.user.UserEntity;

@Component
public class PostDaoService {

	@Autowired
	PostRepository postRepository;
	@Autowired
	UserDaoService userDaoService;

	public PostEntity createPostForUserWithId(Long userId, String description) {
		Optional<UserEntity> user = userDaoService.getUserById(userId);
		return postRepository.save(new PostEntity(user.get(), description));
	}

	public Optional<PostEntity> getPostForPostId(Long id) {
		return postRepository.findById(id);
	}

	public PostEntity updatePostforUserWithId(Long userId, Long postId, String description) {
		Optional<UserEntity> user = userDaoService.getUserById(userId);
		Optional<PostEntity> post = getPostForPostId(postId);

		if (post.get().getUser().getId() == user.get().getId()) {
			return postRepository.save(new PostEntity(postId, description, user.get()));
		}
		throw new PostNotFoundException("No post_id: " + postId + " found for userId: " + userId);
	}

	public void deleteByUserAndId(Long userId, Long postId) {
		Optional<UserEntity> user = userDaoService.getUserById(userId);
		postRepository.deleteByUserAndId(user.get(), postId);

	}

	public List<PostEntity> getAllPosts() {
		return postRepository.findAll();

	}

	public List<PostEntity> getAllPostsForUserId(Long user_id) {
		return postRepository.findByUserId(user_id);
	}

	public void deletePosById(Long postId) {
		postRepository.deleteById(postId);
	}

	public PostEntity getAllPostsForUserIdAndPostId(Long user_id, Long post_id) {
		return postRepository.findByUserIdAndId(user_id, post_id);
	}

}
