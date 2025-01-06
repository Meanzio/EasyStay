// hotel_card.js
import { createModal, closeModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
  const hotelCards = document.querySelectorAll(".hotel_card"); // 모든 호텔 카드 선택

  hotelCards.forEach((hotelCard, index) => {
    hotelCard.addEventListener("click", (event) => {
      event.preventDefault();
      const hotelData = event.currentTarget.dataset; // 데이터 속성 사용

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
                  <h2 id="modal-hotel-name">${hotelData.name}</h2>
                  <p id="modal-hotel-location">${hotelData.location}</p>
                  <p id="modal-hotel-price">${hotelData.price} 원</p>
                  <p id="modal-hotel-contact">${hotelData.contact}</p>
                  <p id="modal-hotel-manager">${hotelData.manager}</p>
                  <ul id="modal-hotel-tags">
                      ${hotelData.tags.split(",").map((tag) => `<li>${tag}</li>`).join("")}
                  </ul>
              </div>
          </div>
          <form>
              <button type="button" onclick="reserveHotel()">예약하기</button>
          </form>
        `,
          `hotel-modal-${index}` // 각 호텔에 고유 ID 부여
      );
    });
  });
});
