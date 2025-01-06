import { hotelApi } from "./api.js";

document.addEventListener("DOMContentLoaded", () => {
  const hotel = document.getElementById("hotels");
  console.log(hotelApi);

  hotelApi
      .then(({ data }) => {
        console.log(data);
        if (data && data.length > 0) {
          data.forEach(({ contact, location, manager, name, price, tags }) => {
            const hotelCard = `
            <div class="wrap">
              <div class="hotel_list_area">
                <div class="hotel_card" 
                     data-name="${name}" 
                     data-location="${location}" 
                     data-price="${price}" 
                     data-contact="${contact}" 
                     data-manager="${manager}" 
                     data-tags="${tags.join(',')}">
                  <div class="wrap">
                    <div class="img_wrap">
                      <p class="availability">예약가능</p>
                      <img src="./img/background_img.jpg" alt="호텔 이미지">
                    </div>
                    <div class="local">
                      <p>위치</p>
                      <p>${location}</p>
                    </div>
                    <div class="hotel_tit">
                      <h1>${name}</h1>
                      <span>4.5</span>
                    </div>
                    <div class="hotel_info">
                      <div class="hotel_price">
                        <h1>총합</h1>
                        <div>
                          <p class="total">${price}</p>
                          <p><span class="pice">5,000</span>원 / 1일 숙박 시</p>
                        </div>
                      </div>
                      <div class="hotel_call">
                        <h1>연락처</h1>
                        <p>${contact}</p>
                      </div>
                      <div class="hotel_man">
                        <h1>담당자</h1>
                        <p><span>${manager}</span></p>
                      </div>
                    </div>
                    <ul class="tag_group">
                      ${tags.map((text) => `<li>${text}</li>`).join("")}
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          `;
            hotel.insertAdjacentHTML("beforeend", hotelCard);
          });
        } else {
          hotel.innerHTML = "<p>호텔 정보가 없습니다.</p>";
        }
      })
      .catch((error) => {
        console.error("호텔 데이터를 가져오는 데 오류 발생:", error);
        hotel.innerHTML = "<p>호텔 데이터를 불러오는 데 문제가 발생했습니다.</p>";
      });
});
