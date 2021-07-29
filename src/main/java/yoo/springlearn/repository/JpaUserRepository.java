package yoo.springlearn.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import yoo.springlearn.domain.UserVO;

public class JpaUserRepository implements UserRepository{

    private final EntityManager em;

    //생성자가 하나이면 Autowired를 생략할 수 있다.
    public JpaUserRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public List<UserVO> findAll() {
        return em.createQuery("select u from UserVO u", UserVO.class).getResultList();
    }

    @Override
    public Optional<UserVO> findById(String id) {
        List<UserVO> list = em.createQuery("select u from UserVO u where u.userId = :id", UserVO.class).setParameter("id", id).getResultList();
        return list.stream().findAny();
    }

    @Override
    public Optional<UserVO> findByName(String name) {
        List<UserVO> list = em.createQuery("select u from UserVO u where u.userName = :name", UserVO.class).setParameter("name", name).getResultList();
        return list.stream().findAny();
    }

    @Override
    public Optional<UserVO> findBySerialNo(long serialNo) {
        UserVO vo = em.find(UserVO.class, serialNo);
        return Optional.ofNullable(vo);
    }
    
    @Override
    public UserVO insertUser(UserVO vo) {
        em.persist(vo);
        return vo;
    }
}
