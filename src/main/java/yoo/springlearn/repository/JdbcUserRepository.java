package yoo.springlearn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<UserVO> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<UserVO> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<UserVO> findBySerialNo(long serialNo) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public UserVO insertUser(UserVO vo) {
        String sql = "insert into user(userId, userName, userAge, userMail) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, vo.getUserId());
            pstmt.setString(2, vo.getUserName());
            pstmt.setInt(3, vo.getUserAge());
            pstmt.setString(4, vo.getUserMail());
            pstmt.executeUpdate();
            return vo;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt);
        }
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

    private void close(Connection conn, PreparedStatement pstmt){
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
