package yoo.springlearn.service;

import java.util.List;
import java.util.Optional;

import yoo.springlearn.domain.UserVO;
import yoo.springlearn.repository.UserRepository;
import yoo.springlearn.repository.UserRepositoryImpl;

/**
 * UserService
 */
public class UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();

    private void validateDuplicateUser(UserVO vo){
        //ifPresent는 null이 아닌 값이 있으면 동작한다.
        //orElseGet도 많이 사용된다. 값이 있으면 꺼내고 없으면 실행시킨다.
        userRepository.findByName(vo.getUserName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존제하는 회원입니다.");
        });
    }

    /*
        회원가입
    */
    public Long join(UserVO vo){
        //중복회원 이름으로 체크
        validateDuplicateUser(vo);
        userRepository.insertUser(vo);
        return vo.getSerialNo();    
    }   

    // 전체 회원 조회
    public List<UserVO> findMembers() {
        return userRepository.findAll();        
    }

    public Optional<UserVO> findOne(Long serialNo){
        return userRepository.findBySerialNo(serialNo);
    }
}