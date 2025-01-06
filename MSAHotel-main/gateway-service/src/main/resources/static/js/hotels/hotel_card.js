// hotel_card.js
import { createModal, closeModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
  const hotelElement = document.getElementById("hotel1234");
  if (hotelElement) {
    hotelElement.addEventListener("click", (event) => {
      event.preventDefault();
      createModal(
        `
                <div class="modal_head">
                    <div>
                        <h1>호텔 정보</h1>
                    </div>
                    <button class="close_btn" onclick="closeModal()">
                        <span class="close_icon"></span>
                    </button>
                </div>
                <div id="hotel-info-modal" class="hotel-info-modal">
                    <div class="modal-content">
                        <h2 id="modal-hotel-name">금나와라 뚝딱 호텔</h2>
                        <p id="modal-hotel-location">서울시</p>
                        <p id="modal-hotel-price">10,000 원</p>
                        <p id="modal-hotel-contact">010-8990-7442</p>
                        <p id="modal-hotel-manager">김국희 전무</p>
                        <ul id="modal-hotel-tags">
                            <li>파티룸</li>
                            <li>유아동반</li>
                            <li>편의시설</li>
                        </ul>
                    </div>
                </div>
                <form>
                    <button type="button" onclick="reserveHotel()">예약하기</button>
                </form>
            `,
        "hotel1234"
      );
    });
  }
});
