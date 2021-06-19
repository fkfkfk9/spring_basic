package yoo.springlearn.domain;

/**
 * MemberVO
 */
public class UserVO {

    // ID, 유저넘버, 이름, 연락처, 나이, 메일주소
    private String userId;
    private Long serialNo;
    private String userName;
    private String userPhone;
    private int userAge;
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
    public String getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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