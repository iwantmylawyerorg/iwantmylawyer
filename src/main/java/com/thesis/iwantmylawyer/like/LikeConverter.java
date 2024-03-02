package com.thesis.iwantmylawyer.like;

import com.thesis.iwantmylawyer.user.UserConverter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LikeConverter {
    private final UserConverter userConverter;

    public LikeConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public List<LikeResponse> convert(List<Like> fromList){
        return fromList.stream().map(like ->
                new LikeResponse(
                        like.getId(),
                        like.getLocalDateTime(),
                        userConverter.convert(like.getUser())
                        )).toList();
    }
}
