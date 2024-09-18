package org.vuetiful.DNS.domain.member.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vuetiful.DNS.domain.member.dto.MemberDTO;
import org.vuetiful.DNS.domain.member.entity.Member;
import org.vuetiful.DNS.domain.member.repository.MemberRepository;
import org.vuetiful.DNS.domain.member.service.MemberService;
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param memberDTO
     * @return
     */
    @Override
    public String signup(MemberDTO memberDTO) {
        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .password(memberDTO.getPassword())
                .nickname(memberDTO.getNickname())
                .profileImageUrl(memberDTO.getProfileImageUrl())
                .socialType(memberDTO.getSocialType())
                .build();
        memberRepository.save(member);
        return "successful";
    }

    /**
     * 로그인
     * @param memberDTO
     * @return
     */
    @Override
    public String login(MemberDTO memberDTO) {
        Member existingMember = memberRepository.findByEmail(memberDTO.getEmail());
        try {
            if(existingMember != null || !memberDTO.getPassword().equals(existingMember.getPassword())) {
                return null;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return "successful";
    }

    /**
     * 회원 정보 수정
     * @param memberId
     * @param memberDTO
     * @return
     */
    @Override
    public String updateMember(int memberId, MemberDTO memberDTO) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Member updateMember = member.toBuilder()
                .nickname(memberDTO.getNickname())
                .profileImageUrl(memberDTO.getProfileImageUrl())
                .build();
        memberRepository.save(updateMember);
        return "successful";
    }
}
