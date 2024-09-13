package org.vuetiful.DNS.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vuetiful.DNS.domain.member.dto.MemberDTO;
import org.vuetiful.DNS.domain.member.service.MemberService;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원 가입
     * @param memberDTO
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO memberDTO) {
        String result = memberService.signup(memberDTO);
        if(result.equals("successful")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 로그인
     * @param memberDTO
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberDTO memberDTO) {
        String result = memberService.login(memberDTO);
        if(result.equals("successful")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 회원 수정
     * @param memberId
     * @param memberDTO
     * @return
     */
    @PutMapping("/{memberId}")
    public ResponseEntity<String> updateMember(@PathVariable Long memberId, @RequestBody MemberDTO memberDTO) {
        String result = memberService.updateMember(memberId, memberDTO);
        return ResponseEntity.ok(result);

    }
}
