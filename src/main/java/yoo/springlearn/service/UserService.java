package yoo.springlearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yoo.springlearn.domain.UserVO;
import yoo.springlearn.repository.UserRepository;

/**
 * UserService
 */
//@Service 어노테이션을 사용하여 자동으로 의존관계를 설정한다.
public class UserService {

    // 바로 주입하는 방법이 일반적으로 많이 쓰이지만 중간에 바꾸기 힘들다는 단점이 있다.
    // @Autowired
    // private UserRepository userRepository;

    // Setter 방식이 있다. 중간에 누군가 사용할 수 있다는 단점이 있다.
    // @Autowired
    // public void setUserRepository(UserRepository userRepository){
    //     this.userRepository = userRepository;
    // }

    private UserRepository userRepository;
    //가장 추천하는 방식으로 생성자로 처음 생성시 셋팅해주고 다른곳에서 호출할 수 없다.
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

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