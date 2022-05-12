package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;

/*
    내가 글을 쓴다는 개념이면 Post 생성자 매개변수도 바꿔야 함
    public Post writePost(Sting title, String body) {
        return new Post(this.fullName, title, body);
    }
*/
public class User {
    private UserType type;
    private String firstName;
    private String lastName;
    private String fullName;

    public User(String firstName, String lastName, UserType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.fullName = String.format("%s %s", firstName, lastName);
    }

    public String getUserName() {
        return this.fullName;
    }

    public UserType getUserType() {
        return this.type;
    }
}
