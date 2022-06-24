package filework.entities;

import java.util.Objects;

public class User implements Writable {
    private final String name;
    private final String nickname;
    private final String role;
    private final int age;
    private final int id;

    public User(String name, String nickname, String role, int age, int id) {
        this.name = name;
        this.nickname = nickname;
        this.role = role;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return name + ',' + nickname + ',' + role + ',' + age + ',' + id;
    }

    @Override
    public boolean equals(Object otherUser) {
        if (this == otherUser) return true;
        if (!(otherUser instanceof User user)) return false;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String getFilename() {
        return "userId" + id;
    }

    public int getId() {
        return id;
    }
}
