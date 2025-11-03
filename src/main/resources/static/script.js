// src/main/resources/static/script.js

// આ તે URL છે જ્યાં આપણું Spring Boot બેક-એન્ડ ચાલી રહ્યું છે
const API_URL = 'http://localhost:8080/api/notices';

// પેજ લોડ થાય કે તરત જ આ ફંક્શન ચાલશે
document.addEventListener('DOMContentLoaded', () => {
    
    // ફોર્મ અને કન્ટેનરને પકડો
    const noticeForm = document.getElementById('noticeForm');
    const noticeContainer = document.getElementById('notice-container');

    // 1. નોટિસ મેળવવા અને બતાવવા માટેનું ફંક્શન
    async function fetchNotices() {
        try {
            // બેક-એન્ડમાંથી ડેટા માટે GET રિક્વેસ્ટ મોકલો
            const response = await fetch(API_URL);
            const notices = await response.json();

            // કન્ટેનર ખાલી કરો
            noticeContainer.innerHTML = '';

            // જો કોઈ નોટિસ ન હોય
            if (notices.length === 0) {
                noticeContainer.innerHTML = '<p>હાલમાં કોઈ નોટિસ નથી.</p>';
                return;
            }

            // બધી નોટિસને લૂપમાં ફેરવીને HTML માં ઉમેરો
            notices.forEach(notice => {
                const noticeCard = document.createElement('div');
                noticeCard.className = 'notice-card';
                
                // તારીખને ફોર્મેટ કરો (વૈકલ્પિક)
                const formattedDate = new Date(notice.createdAt).toLocaleString('gu-IN');

                noticeCard.innerHTML = `
                    <h3>${notice.title}</h3>
                    <p>${notice.content}</p>
                    <small>પોસ્ટ કર્યાનો સમય: ${formattedDate}</small>
                `;
                noticeContainer.appendChild(noticeCard);
            });

        } catch (error) {
            console.error('નોટિસ લાવવામાં ભૂલ:', error);
            noticeContainer.innerHTML = '<p>નોટિસ લોડ કરવામાં નિષ્ફળતા મળી.</p>';
        }
    }

    // 2. નવી નોટિસ ઉમેરવા માટેનું ફંક્શન (જ્યારે ફોર્મ સબમિટ થાય)
    noticeForm.addEventListener('submit', async (event) => {
        // ફોર્મને આપમેળે રિફ્રેશ થતું અટકાવો
        event.preventDefault(); 

        // ફોર્મમાંથી ટાઇટલ અને કન્ટેન્ટ મેળવો
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;

        // આ ડેટાને આપણે બેક-એન્ડને મોકલીશું
        const newNotice = {
            title: title,
            content: content
        };

        try {
            // બેક-એન્ડને POST રિક્વેસ્ટ મોકલો
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newNotice)
            });

            if (response.ok) {
                // જો નોટિસ સફળતાપૂર્વક ઉમેરાઈ જાય
                
                // ફોર્મ ખાલી કરો
                document.getElementById('title').value = '';
                document.getElementById('content').value = '';
                
                // નોટિસ લિસ્ટ રિફ્રેશ કરો
                fetchNotices(); 
            } else {
                alert('નોટિસ ઉમેરવામાં ભૂલ થઈ.');
            }
        } catch (error) {
            console.error('નોટિસ ઉમેરવામાં ભૂલ:', error);
        }
    });

    // 3. પેજ લોડ થતાં જ પહેલી વાર બધી નોટિસ લોડ કરો
    fetchNotices();
});