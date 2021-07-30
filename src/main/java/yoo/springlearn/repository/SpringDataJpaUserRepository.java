package yoo.springlearn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import yoo.springlearn.domain.UserVO;

public interface SpringDataJpaUserRepository extends JpaRepository<UserVO, Long>, UserRepository{
    @Override
    Optional<UserVO> findById(String userId);

    @Override
    Optional<UserVO> findByName(String userName);

    @Override
    Optional<UserVO> findBySerialNo(long serialNo);
}
