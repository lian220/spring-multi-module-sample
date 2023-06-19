package lian.sample.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Setter
public class Member {
    private String memberId;
    @Id
    @Column(name = "login_id")
    private String loginId;
    private String password;
    private String name;
    private Long age;

    public Member() {
        setMemberId(UUID.randomUUID().toString());
    }
}
