package keython.mandalart.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
public class User {

    //field
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<MandalArt> mandalArts = new ArrayList<>();


    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //연관관계 편의 메서드
    public void addMandalArtByUser(MandalArt mandalArt){
        mandalArts.add(mandalArt);
        mandalArt.setUser(this);
    }
}
