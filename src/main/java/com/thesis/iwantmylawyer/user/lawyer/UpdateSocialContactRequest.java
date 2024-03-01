package com.thesis.iwantmylawyer.user.lawyer;

import jakarta.validation.constraints.NotBlank;

public record UpdateSocialContactRequest(
        @NotBlank String id,
        String contactEmail,
        String contactTelNo,
        String contactTwitterUrl,
        String contactInstagramUrl,
        String contactFaceBookUrl
) {
}
