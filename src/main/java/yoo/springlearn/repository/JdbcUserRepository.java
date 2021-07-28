package yoo.springlearn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import yoo.springlearn.domain.UserVO;

public class JdbcUserRepository implements UserRepository{

    private final DataSource dataSource;

    public JdbcUserRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<UserVO> findAll() {
        String sql = "select * from user";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<UserVO> userList = new ArrayList<UserVO>();
            while(rs.next()) {
                UserVO vo = new UserVO();
                vo.setSerialNo(rs.getLong("serialNo"));
                vo.setUserName(rs.getString("userName"));
                vo.setUserId(rs.getString("userId"));
                vo.setUserMail(rs.getString("userMail"));
                vo.setUserAge(rs.getInt("userAge"));   
                userList.add(vo);
            }
            return userList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<UserVO> findById(String id) {
        String sql = "select * from user where userId = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                UserVO vo = new UserVO();
                vo.setSerialNo(rs.getLong("serialNo"));
                vo.setUserName(rs.getString("userName"));
                vo.setUserId(rs.getString("userId"));
                vo.setUserMail(rs.getString("userMail"));
                vo.setUserAge(rs.getInt("userAge"));                
                return Optional.of(vo);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<UserVO> findByName(String name) {
        String sql = "select * from user where userName = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                UserVO vo = new UserVO();
                vo.setSerialNo(rs.getLong("serialNo"));
                vo.setUserName(rs.getString("userName"));
                vo.setUserId(rs.getString("userId"));
                vo.setUserMail(rs.getString("userMail"));
                vo.setUserAge(rs.getInt("userAge"));                
                return Optional.of(vo);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<UserVO> findBySerialNo(long serialNo) {
        String sql = "select * from user where serialNo = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, serialNo);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                UserVO vo = new UserVO();
                vo.setSerialNo(rs.getLong("serialNo"));
                vo.setUserName(rs.getString("userName"));
                vo.setUserId(rs.getString("userId"));
                vo.setUserMail(rs.getString("userMail"));
                vo.setUserAge(rs.getInt("userAge"));                
                return Optional.of(vo);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    
    @Override
    public UserVO insertUser(UserVO vo) {
        String sql = "insert into user(userId, userName, userAge, userMail) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, vo.getUserId());
            pstmt.setString(2, vo.getUserName());
            pstmt.setInt(3, vo.getUserAge());
            pstmt.setString(4, vo.getUserMail());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                vo.setSerialNo(rs.getLong(1));
            }else{
                throw new SQLException("id 조회 실패");
            }
            return vo;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        //DataSourceUtils을 사용해주어야 트랜잭션같은 곳에서 문제가 생기지 않는다.
        //아무래도 매번 데이터소스를 생성해주는게 아닌 하나의 데이터소스를 관리해주어야 여러 연결에서도 안전한듯 싶다.
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
