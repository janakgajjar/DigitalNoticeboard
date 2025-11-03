// src/main/java/com/example/noticeboard/NoticeController.java

package com.digitalnoticeboard.digital_noticeboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // આ જણાવે છે કે આ કંટ્રોલર JSON ડેટા રિટર્ન કરશે
@RequestMapping("/api/notices") // બધી URLs "/api/notices" થી શરૂ થશે
@CrossOrigin(origins = "*") // આ ફ્રન્ટ-એન્ડને બેક-એન્ડ સાથે વાત કરવાની પરવાનગી આપે છે
public class NoticeController {

    @Autowired // આ Spring ને આપમેળે NoticeRepository ઓબ્જેક્ટ આપવા વિનંતી કરે છે
    private NoticeRepository noticeRepository;

    // 1. બધી નોટિસ મેળવવા માટે (GET રિક્વેસ્ટ)
    // URL: GET http://localhost:8080/api/notices
    @GetMapping
    public List<Notice> getAllNotices() {
        // ડેટાબેઝમાંથી બધી નોટિસ શોધો અને રિટર્ન કરો
        return noticeRepository.findAll();
    }

    // 2. નવી નોટિસ બનાવવા માટે (POST રિક્વેસ્ટ)
    // URL: POST http://localhost:8080/api/notices
    @PostMapping
    public Notice createNotice(@RequestBody Notice notice) {
        // ફ્રન્ટ-એન્ડમાંથી આવેલી નોટિસને ડેટાબેઝમાં સેવ કરો
        return noticeRepository.save(notice);
    }
    
    // 3. નોટિસ ડિલીટ કરવા માટે (Delete રિક્વેસ્ટ) - (આ એક વધારાનું ફંક્શન છે)
    // URL: DELETE http://localhost:8080/api/notices/1 (જ્યાં 1 એ નોટિસ ID છે)
    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable Long id) {
        noticeRepository.deleteById(id);
    }
}