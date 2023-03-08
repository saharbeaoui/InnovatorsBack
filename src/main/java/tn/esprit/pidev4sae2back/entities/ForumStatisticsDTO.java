package tn.esprit.pidev4sae2back.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter

@Table(name = "ForumStatisticsDTO")
public class ForumStatisticsDTO {
    private Long totalThreads;
    private Long totalForums;
    private double averageThreadsPerForum;






}
