package com.chain.jdbc.service;

import com.chain.jdbc.domain.Member;
import com.chain.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

/**
 * 트랜잭션 - @Transactional AOP
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_3 {

//    private final TransactionTemplate transactionTemplate;
    private final MemberRepositoryV3 memberRepository;

//    public MemberServiceV3_3(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) {
//        this.transactionTemplate = new TransactionTemplate(transactionManager);
//        this.memberRepository = memberRepository;
//    }

    @Transactional
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
//        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//
//        try {
//            bizLogic(fromId, toId, money);
//            transactionManager.commit(status);
//        } catch (Exception e) {
//            transactionManager.rollback(status);
//            throw new IllegalStateException(e);
//        }

//        // 트랜잭션 템플릿을 이용해 변하지 않고 반복되는 부분을 제거해보자
//        // 변하지 않는 부분인 템플릿(트랜잭션 시작, 커밋, 롤백)과 변하는 부분, 콜백(비즈니스 로직)을 분리함
//        // 참고로 매개변수가 콜백임
//        transactionTemplate.executeWithoutResult((status) -> {
//            try {
//                bizLogic(fromId, toId, money);
//            } catch (SQLException e) {
//                throw new IllegalStateException(e);
//            }
//        });

        bizLogic(fromId, toId, money);

    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
