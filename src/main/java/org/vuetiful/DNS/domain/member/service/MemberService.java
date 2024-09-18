package org.vuetiful.DNS.domain.member.service;

import org.vuetiful.DNS.domain.member.dto.MemberDTO;

public interface MemberService {
    public String signup(MemberDTO memberDTO);
    public String login(MemberDTO memberDTO);
    public String updateMember(int memberId, MemberDTO memberDTO);
}
