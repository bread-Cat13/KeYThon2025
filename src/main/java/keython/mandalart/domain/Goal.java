package keython.mandalart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "goal")
public class Goal {

    //field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GoalLevel level;

    @Enumerated(EnumType.STRING)
    private GoalStatus status;

    private LocalDateTime finishedDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    @Setter
    private Goal parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> children = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mandal_art_id")
    @Setter
    private MandalArt mandalArt;

    protected Goal(){};

    public Goal(String name, GoalLevel level, GoalStatus status, LocalDateTime finishedDate) {
        this.name = name;
        this.level = level;
        this.status = status;
        this.finishedDate = finishedDate;
    }

    //연관관계 편의 메서드
    public void addChild(Goal child){
        children.add(child);
        child.setParent(this);
    }

    //일반 메서드

    //INCOMPLETE -> COMPLETED
    public void finish(){
        this.status = GoalStatus.COMPLETED;
    }
}
