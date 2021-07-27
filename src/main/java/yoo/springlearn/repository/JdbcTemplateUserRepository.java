package yoo.springlearn.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import yoo.springlearn.domain.UserVO;

public class JdbcTemplateUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    //생성자가 하나이면 Autowired를 생략할 수 있다.
    public JdbcTemplateUserRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<UserVO> findAll() {
        return jdbcTemplate.query("select * from user", userRowMapper());
    }

    @Override
    public Optional<UserVO> findById(String id) {
        List<UserVO> result = jdbcTemplate.query("select * from user where userId = ?", userRowMapper());
        return result.stream().findAny();
    }

    @Override
    public Optional<UserVO> findByName(String name) {
        List<UserVO> result = jdbcTemplate.query("select * from user where userName = ?", userRowMapper());
        return result.stream().findAny();
    }

    @Override
    public Optional<UserVO> findBySerialNo(long serialNo) {
        List<UserVO> result = jdbcTemplate.query("select * from user where serialNo = ?", userRowMapper());
        return result.stream().findAny();
    }
    
    @Override
    public UserVO insertUser(UserVO vo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("serialNo");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", vo.getUserId());
        parameters.put("userName", vo.getUserName());
        parameters.put("userAge", vo.getUserAge());
        parameters.put("userMail", vo.getUserMail());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        vo.setSerialNo(key.longValue());
        return vo;
    }

    private RowMapper<UserVO> userRowMapper() {
        return (rs, rowNum) -> {
            UserVO vo = new UserVO();
            vo.setSerialNo(rs.getLong("serialNo"));
            vo.setUserName(rs.getString("userName"));
            vo.setUserId(rs.getString("userId"));
            vo.setUserMail(rs.getString("userMail"));
            vo.setUserAge(rs.getInt("userAge"));   
            return vo;
        };        
    }
}
