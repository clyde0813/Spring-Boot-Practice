package com.example.demo.member;

import java.util.Map;
import java.util.HashMap;

public class MemoryMemberRepository implements MemberRepository {
    // 동시성 이슈 있음
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memeberId) {
        return store.get(memeberId);
    }
}