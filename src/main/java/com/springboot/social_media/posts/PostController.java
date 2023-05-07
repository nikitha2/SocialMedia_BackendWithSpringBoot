package com.springboot.social_media.posts;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class PostController {

	@Autowired
	PostDaoService postDaoService;

//	Create user -> POST /users/{id}/posts -> This should create a post for user {id}
	@PostMapping("/users/{user_id}/posts")
	public ResponseEntity<PostEntity> createPostForUserWithId(@PathVariable Long user_id,
			@Valid @RequestBody PostEntity post) {
		PostEntity postId = postDaoService.createPostForUserWithId(user_id, post.getDescription());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(postId.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@GetMapping("/posts/{post_id}")
	public EntityModel<Optional<PostEntity>> getPostForPostId(@PathVariable Long post_id) {

		Optional<PostEntity> post = postDaoService.getPostForPostId(post_id);

		if (post.isEmpty()) {
			throw new PostNotFoundException("post_id: " + post_id);
		}

		EntityModel<Optional<PostEntity>> entityModel = EntityModel.of(post); // wraps user with entityModel
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllPosts());

		entityModel.add(link.withRel("all-users"));
		return entityModel;

	}

//	Read user -> GET /users/{id}/posts/{postId} -> returns back a post {postID} for user {id}
	@GetMapping("/users/{user_id}/posts/{post_id}")
	public PostEntity getAllPostsForUserId(@PathVariable Long user_id,@PathVariable Long post_id) {
		return postDaoService.getAllPostsForUserIdAndPostId(user_id,post_id);
	}
	
//	Read user -> GET /users/{id}/posts -> returns back all posts for user {id} 
	@GetMapping("/users/{user_id}/posts")
	public List<PostEntity> getAllPostsForUserId(@PathVariable Long user_id) {
		return postDaoService.getAllPostsForUserId(user_id);
	}

//  GET -> Get all the posts in the table POSTS
	@GetMapping("/users/posts")
	public List<PostEntity> getAllPosts() {
		return postDaoService.getAllPosts();
	}

//	Update -> UPDATE /users/{id}/posts/{postId} -> update post {postId} for user {id}
	@PostMapping("/users/{userId}/posts/{postId}")
	public PostEntity updatePostforUserWithId(@PathVariable Long userId, @PathVariable Long postId,
			@Valid @RequestBody PostEntity post) {
		return postDaoService.updatePostforUserWithId(userId, postId, post.getDescription());
	}

//	Delete -> DELETE /users/{id}/posts/{postId} -> delete post {postId} for user {id} DELETE /users/{id}/posts -> delete all posts for user {id}
	@DeleteMapping("/users/{id}/posts/{postId}")
	public void deletePostforUserWithId(@PathVariable Long id, @PathVariable Long postId) {
		postDaoService.deleteByUserAndId(id, postId);
	}

	@DeleteMapping("/posts/{postId}")
	public void deletePosById(@PathVariable Long postId) {
		postDaoService.deletePosById(postId);
	}

}
