package yoo.springlearn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MemberVO
 */
@Entity
@Table(name="user")
public class UserVO {

    // ID, 유저넘버, 이름, 연락처, 나이, 메일주소 
    @Column(name = "userId")
    private String userId;

    //IDENTITY는 시퀀스나 오토 인크리먼트 같은 DB가 ID를 자동으로 부여해주는것을
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialNo;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userAge")
    private int userAge;

    @Column(name = "userMail")
    private String userMail;
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Long getSerialNo() {
        return serialNo;
    }
    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getUserAge() {
        return userAge;
    }
    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
    public String getUserMail() {
        return userMail;
    }
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    
}