package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.member.Member;
import study.querydsl.domain.member.QMember;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static study.querydsl.domain.member.QMember.member;

public class QuerydslBasicTest extends BasicTest{

    JPAQueryFactory queryFactory;

    @Test
    public void startQuerydsl() {

        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.memberName.eq("member1"))
                .fetchOne();

        assertEquals("member1", findMember.getMemberName());
    }

    @Test
    public void startJPQL() {
        //member1 찾기
        Member member = em.createQuery(
                "select m from Member m " +
                        "where m.memberName = :memberName", Member.class)
                .setParameter("memberName", "member1")
                .getSingleResult();

        assertEquals("member1", member.getMemberName());
    }
}
