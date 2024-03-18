package com.thesis.iwantmylawyer.mail;

public record SendMailRequest(String to, String text, String subject) {
}
