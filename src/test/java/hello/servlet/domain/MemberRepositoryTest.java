package hello.servlet.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();

    }

    @Test
    public void save() throws Exception{
        //given
        Member member = new Member("memberA",20);
        //when
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId());

        //then
        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    public void findAll () throws Exception{
        //given
        Member memberA = new Member("memberA", 20);
        Member memberB = new Member("memberB", 21);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }

}