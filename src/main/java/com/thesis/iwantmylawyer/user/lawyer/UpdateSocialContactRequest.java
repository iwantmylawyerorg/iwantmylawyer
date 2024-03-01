package com.thesis.iwantmylawyer.user.lawyer;

public record UpdateSocialContactRequest(
        String id,
        String contactEmail,
        String contactTelNo,
        String contactTwitterUrl,
        String contactInstagramUrl,
        String contactFaceBookUrl
) {
}
