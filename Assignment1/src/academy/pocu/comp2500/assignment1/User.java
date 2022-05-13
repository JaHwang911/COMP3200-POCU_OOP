package academy.pocu.comp2500.assignment1;

/*
    내가 글을 쓴다는 개념이면 Post 생성자 매개변수도 바꿔야 함
    public Post writePost(Sting title, String body) {
        return new Post(this.fullName, title, body);
    }
*/
public class User {
    private final UserType type;
    private final String name;

    public User(String name, UserType type) {
        this.type = type;
        this.name = name;
    }

    public String getUserName() {
        return this.name;
    }

    public UserType getUserType() {
        return this.type;
    }
}
