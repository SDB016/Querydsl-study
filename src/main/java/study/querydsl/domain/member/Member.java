package study.querydsl.domain.member;

import lombok.*;
import study.querydsl.domain.team.Team;

import javax.persistence.*;

@Entity
@Getter @Setter
@ToString(exclude = "team")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String memberName;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String memberName) {
        this(memberName, 0, null);
    }

    public Member(String memberName, int age) {
        this(memberName, age, null);
    }

    public Member(String memberName, int age, Team team) {
        this.memberName = memberName;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        team.getMembers().add(this);
        this.setTeam(team);
    }
}
