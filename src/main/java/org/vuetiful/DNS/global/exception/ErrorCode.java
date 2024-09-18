package org.vuetiful.DNS.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    DM_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 DM 방이 존재하지 않거나, 권한이 없습니다."),
    MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 방에 속한 메시지가 없거나, 존재하지 않습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시물입니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."),
    PARENT_COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "대댓글의 부모 댓글을 찾을 수 없습니다."),

    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    OUT_OF_RANGE(HttpStatus.BAD_REQUEST, "더 이상 페이지가 없습니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청 파라미터에 오류가 있습니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
