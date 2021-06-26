package yoo.springlearn.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import yoo.springlearn.domain.UserVO;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private static Map<Long, UserVO> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public List<UserVO> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<UserVO> findById(String id) {
        return store.values().stream().filter(UserVO -> UserVO. getUserId().equals(id)).findAny();
    }
    @Override
    public Optional<UserVO> findBySerialNo(long serialNo) {
        return Optional.ofNullable(store.get(serialNo));
    }
    @Override
    public Optional<UserVO> findByName(String name) {
        return store.values().stream().filter(UserVO -> UserVO.getUserName().equals(name)).findAny();
    }
    @Override
    public UserVO insertUser(UserVO vo) {
        vo.setSerialNo(++sequence);
        store.put(vo.getSerialNo(), vo);
        return vo;
    }

    public void clearStore(){
        store.clear();
    }
}
