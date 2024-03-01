package com.thesis.iwantmylawyer.user.lawyer;

import java.util.List;

public record AddExpertiseFieldRequest(String id,
                                       List<String> expertiseFieldIdList) {
}
