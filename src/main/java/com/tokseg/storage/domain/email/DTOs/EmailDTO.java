package com.tokseg.storage.domain.email.DTOs;

public record EmailDTO(String to, String subject, String body, boolean isHtml) {

}
