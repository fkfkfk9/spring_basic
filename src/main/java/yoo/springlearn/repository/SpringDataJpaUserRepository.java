package yoo.springlearn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import yoo.springlearn.domain.UserVO;

//인터페이스에서 인터페이스를 받을떄는 extends를 사용한다.
public interface SpringDataJpaUserRepository extends JpaRepository<UserVO, Long>, UserRepository{
    @Override
    Optional<UserVO> findById(String userId);

    @Override
    Optional<UserVO> findByName(String userName);

    @Override
    Optional<UserVO> findBySerialNo(long serialNo);
}
