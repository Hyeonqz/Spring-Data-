package org.example.shop.service;

import java.util.List;

import org.example.shop.domain.Member;
import org.example.shop.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {
	private final MemberRepository memberRepository;

	// 회원 가입
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> byName = memberRepository.findByName(member.getName());
		if (!byName.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	// 회원 전체 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Member findOne(Long id) {
		return memberRepository.findOne(id);
	}

}
