package keython.mandalart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "mandal_art")
public class MandalArt {

    //field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mandal_art_id")
    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime deadline;

    protected  MandalArt(){};

    public MandalArt(String title, String description, LocalDateTime createdDate, LocalDateTime deadline) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.deadline = deadline;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @Setter
    private User user;

    @OneToMany(mappedBy = "mandalArt", cascade = CascadeType.REMOVE)
    private List<Goal> goals = new ArrayList<>();


    //연관관계 편의 메서드
    public void addGoal(Goal goal){
        goals.add(goal);
        goal.setMandalArt(this);
    }
}
