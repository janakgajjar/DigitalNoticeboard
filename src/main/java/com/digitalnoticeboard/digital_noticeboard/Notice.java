// src/main/java/com/example/noticeboard/Notice.java

package com.digitalnoticeboard.digital_noticeboard;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

// Lombok annotations (જો તમે Lombok વાપરતા હોય તો)
import lombok.Data; 

@Data // આ @Getter, @Setter, @ToString વગેરે આપમેળે બનાવે છે
@Entity // આ Spring ને કહે છે કે આ એક ડેટાબેઝ ટેબલ છે
public class Notice {

    @Id // આ પ્રાઇમરી કી (Primary Key) છે
    @GeneratedValue(strategy = GenerationType.IDENTITY) // આ ID ને આપમેળે વધારશે (1, 2, 3...)
    private Long id;

    private String title; // નોટિસનું ટાઇટલ

    private String content; // નોટિસની વિગતો

    @CreationTimestamp // નોટિસ ક્યારે બની તે સમય આપમેળે ઉમેરશે
    private LocalDateTime createdAt;
    
    // જો તમે Lombok ન વાપરતા હોવ, તો તમારે id, title, content, createdAt
    // માટે જાતે જ getters અને setters બનાવવા પડશે.
}