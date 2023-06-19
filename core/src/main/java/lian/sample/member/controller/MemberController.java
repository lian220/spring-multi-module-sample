package lian.sample.member.controller;

import lian.sample.member.MemberRepository;
import lian.sample.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @PostMapping("/member")
    public ResponseEntity createMember(@RequestBody Member member) {
        memberRepository.save(member);
        return null;
    }
}
