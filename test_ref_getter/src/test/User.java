package test;

public class User {
    private String userName;

    public User(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setName(String name){
        this.userName = name;
    }
}
