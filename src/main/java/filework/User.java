package filework;

import java.util.Objects;

public class User {
    private String name;
    private String nickname;
    private String role;
    private int age;
    private int id;

    public User(String name, String nickname, String role, int age, int id) {
        this.name = name;
        this.nickname = nickname;
        this.role = role;
        this.age = age;
        this.id = id;
    }

    public User(String[] valuesArray) {
        this(valuesArray[0], valuesArray[1], valuesArray[2],
                Integer.parseInt(valuesArray[3]), Integer.parseInt(valuesArray[4]));
    }

    @Override
    public String toString() {
        return name + ',' + nickname + ',' + role + ',' + age + ',' + id;
    }

    @Override
    public boolean equals(Object otherUser) {
        if (this == otherUser) return true;
        if (!(otherUser instanceof User)) return false;
        User user = (User) otherUser;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    //region getters/setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }
    //endregion
}
