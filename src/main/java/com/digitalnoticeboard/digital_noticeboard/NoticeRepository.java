// src/main/java/com/example/noticeboard/NoticeRepository.java

package com.digitalnoticeboard.digital_noticeboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    // બસ! આટલું જ. 
    // findAll(), findById(), save(), deleteById() જેવા બધા ફંક્શન આપમેળે બની જશે.
}