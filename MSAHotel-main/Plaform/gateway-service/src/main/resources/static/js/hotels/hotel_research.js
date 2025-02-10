document.addEventListener('DOMContentLoaded', () => {
    toggleSections('hotel_research_name');
    const buttons = document.querySelectorAll('.hotel_research_type button');
    const ResearchTitle = document.getElementById('research_title');
    const researchAbout = document.getElementById('research_about');
    const InputHead = document.getElementsByClassName('input_head')[0];
    const ResearchTypeButtons = document.querySelectorAll('.hotel_research_type li button');
    const inputs = document.querySelectorAll('form input');

    const TitleList = ["호텔별 검색", "지역별 검색", "상황별 검색"];
    const ColorList = ["#001A6E", "#009990", "#F26B0F"];
    const AboutList = [
        "검색을 통해서 호텔을 검색 할 수 있습니다.",
        "지역명을 통해 호텔을 검색 할 수 있습니다.",
        "상황에 맞춰 호텔을 검색 할 수 있습니다.",
    ];

    ResearchTitle.textContent = TitleList[0];
    researchAbout.textContent = AboutList[0];
    InputHead.style.backgroundColor = ColorList[0];
    InputHead.style.border = 'none';  
    InputHead.style.outline = 'none'; 

    ResearchTypeButtons.forEach((btn, btnIndex) => {
        if (btnIndex === 0) {
            btn.style.backgroundColor = ColorList[0];
            btn.style.color = '#FFFFFF';
        } else {
            btn.style.backgroundColor = '';
            btn.style.color = '';
        }
    });

    buttons.forEach((button, index) => {
        button.addEventListener('click', () => {
            const targetId = button.getAttribute('data-target');
            toggleSections(targetId);

            switch (targetId) {
                case 'hotel_research_name':
                    ResearchTitle.textContent = TitleList[0];
                    researchAbout.textContent = AboutList[0];
                    InputHead.style.backgroundColor = ColorList[0];
                    break;
                case 'hotel_research_local':
                    ResearchTitle.textContent = TitleList[1];
                    researchAbout.textContent = AboutList[1];
                    InputHead.style.backgroundColor = ColorList[1];
                    break;
                case 'hotel_research_situation':
                    ResearchTitle.textContent = TitleList[2];
                    researchAbout.textContent = AboutList[2];
                    InputHead.style.backgroundColor = ColorList[2];
                    break;
            }

            buttons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');

            ResearchTypeButtons.forEach((btn, btnIndex) => {
                if (btnIndex === index) {
                    btn.style.backgroundColor = ColorList[index];
                    btn.style.color = '#FFFFFF';
                } else {
                    btn.style.backgroundColor = '';
                    btn.style.color = '';
                }
            });
        });
    });

    inputs.forEach(input => {
        input.addEventListener('focus', () => {
            input.style.border = `2px solid ${InputHead.style.backgroundColor}`;
            input.style.outline = 'none'; 
        });

        input.addEventListener('blur', () => {
            input.style.border = ''; 
        });
    });
});

const toggleSections = (selectedId) => {
    const sections = ['hotel_research_name', 'hotel_research_local', 'hotel_research_situation'];
    sections.forEach(id => {
        const element = document.getElementById(id);
        if (element) {
            element.style.display = id === selectedId ? 'grid' : 'none';
        }
    });
};
