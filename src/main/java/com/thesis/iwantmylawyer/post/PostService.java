package com.thesis.iwantmylawyer.post;

import com.thesis.iwantmylawyer.constant.Constant;
import com.thesis.iwantmylawyer.minio.MinioService;
import com.thesis.iwantmylawyer.user.lawyer.Lawyer;
import com.thesis.iwantmylawyer.user.lawyer.LawyerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final LawyerService lawyerService;
    private final MinioService minioService;

    private final PostConverter postConverter;

    public PostService(PostRepository postRepository, LawyerService lawyerService, MinioService minioService, PostConverter postConverter) {
        this.postRepository = postRepository;
        this.lawyerService = lawyerService;
        this.minioService = minioService;
        this.postConverter = postConverter;
    }

    @Cacheable(Constant.REDIS_POST_CLASS)
    public Page<PostResponse> getAllLawyers(Integer page, Integer size){
        System.out.println("Veri tabanından geldi");
        Pageable pageable = PageRequest.of(page, size);
        return postConverter.convert(postRepository.findAll(pageable));
    }

    @CacheEvict(value = Constant.REDIS_POST_CLASS, allEntries = true, condition = "#createPostRequest != null")
    public void createPost(CreatePostRequest createPostRequest){
        Lawyer lawyer = lawyerService.findById(createPostRequest.lawyerId());
        Post post = new Post(createPostRequest.text(), LocalDateTime.now(),
                lawyer);
        postRepository.save(post);
    }

    @CacheEvict(value = Constant.REDIS_POST_CLASS, allEntries = true, condition = "#id != null && #multipartFile != null")
    public void addPostPhoto(String id, MultipartFile multipartFile){
        Post post = findById(id);
        post.setPostPhoto(minioService.uploadImage(multipartFile));
        postRepository.save(post);
    }

    @CacheEvict(value = Constant.REDIS_POST_CLASS, allEntries = true, condition = "#postId != null")
    public void deletePost(String postId){
        postRepository.delete(findById(postId));
    }

    public Post findById(String id){
        return postRepository.findById(id).orElseThrow(() -> new PostDoesNotFoundException(Constant.POST_DOES_NOT_FOUND_EXCEPTION));
    }
}
