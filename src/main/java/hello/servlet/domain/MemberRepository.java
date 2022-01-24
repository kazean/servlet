package hello.servlet.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberRepository {
    private static HashMap<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;
    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository(){
    }

    public static MemberRepository getInstance(){
        return instance;
    }
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
