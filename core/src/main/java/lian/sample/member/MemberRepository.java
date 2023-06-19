package lian.sample.member;

import lian.sample.member.entity.Member;
import org.springframework.data.repository.Repository;

public interface MemberRepository extends Repository<Member, Long> {
    void save(Member member);
}
