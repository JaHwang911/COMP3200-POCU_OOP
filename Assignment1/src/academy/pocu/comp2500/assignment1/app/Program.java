package academy.pocu.comp2500.assignment1.app;

import academy.pocu.comp2500.assignment1.*;
import academy.pocu.comp2500.assignment1.registry.Registry;

import java.util.ArrayList;

/*
 * 과제1 실수 모음
 * 매개변수 이름을 정해진 것으로 하지 않음
 * registry에 클래스 이름 혹은 매서드 이름을 올바르게 적지 않음
 * 포스트 클래스에서 생성자로 받은 인자들의 getter로 언제나 같은 자료형을 리턴해야함
 * 추천 비추천을 취소하는 기능도 있어야함
 * 만들다 보면 서로 연관되어 있는 부분이 많습니다. 그런 부분들의 통일을 잘 생각 해보시면 수월 하실 겁니다.
 */

public class Program {
    public static void main(String[] args) {
//        testFilter();

        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        System.out.println("No prob");
    }

    private static void testFilter() {
        Blog blog = new Blog();
        Post post1 = new Post("a1", "p1", "body");
        Post post2 = new Post("a1", "p2", "body");
        Post post3 = new Post("a2", "p3", "body");
        Post post4 = new Post("a2", "p4", "body");

        post1.addTag("a1", "t1");
        post1.addTag("a1", "Computer");
        post2.addTag("a1", "t2");
        post3.addTag("a2", "t1");
        post4.addTag("a2", "t2");

        blog.addPost(post1);
        blog.addPost(post2);
        blog.addPost(post3);
        blog.addPost(post4);

        ArrayList<String> tags = new ArrayList<>(2);
        tags.add("t1");

        blog.setFilterOnOffByTags(tags);
        blog.setOrderType(OrderType.CREATED_DESC);

        var filteredTag = blog.getPosts();

        assert filteredTag.size() == 2;
        assert filteredTag.get(0).getTitle().equals("p1");
        assert filteredTag.get(1).getTitle().equals("p3");

        tags.clear();
        blog.setFilterOnOffByTags(tags);
        blog.setFilterOnOffByAuthor("a1");

        var filteredUser = blog.getPosts();

        assert filteredUser.size() == 2;
        assert filteredUser.get(0).getTitle().equals("p1");
        assert filteredUser.get(1).getTitle().equals("p2");

        tags.add("t1");
        blog.setFilterOnOffByAuthor(null);
        blog.setFilterOnOffByTags(tags);
        blog.setFilterOnOffByAuthor("a2");

        var filteredCombo = blog.getPosts();

        assert filteredCombo.size() == 1;
        assert filteredCombo.get(0).getTitle().equals("p3");

        tags.clear();
        blog.setFilterOnOffByTags(tags);
        blog.setFilterOnOffByAuthor(null);

        var notSetFilterPosts = blog.getPosts();

        assert notSetFilterPosts.size() == 4;

        tags.clear();
        tags.add("t1");
        tags.add("Computer");

        blog.setFilterOnOffByTags(tags);
        var multiSetTags = blog.getPosts();

        assert multiSetTags.size() == 1;

        /*
            내가 실수 했던 테스트
                * 이미 태그 필터 or 콤보 필터가 설정돼 있을 때 다시 태그 필터를 설정하는 테스트
                    -> 기존의 태그를 비우지 않았음
                * 이미 콤보 필터가 설정돼 있을 때 작성자 필터를 설정 하는 테스트
                    -> switch 문의 case combo를 등록하지 않아 이미 콤보 필터가 설정돼 있었다면 작동하지 않음
         */

    }
}
