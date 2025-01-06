// 모달을 닫는 함수
export const closeModal = () => {
  const modalOverlay = document.getElementById("modal-overlay");
  if (modalOverlay) {
    modalOverlay.remove();
  }
  localStorage.removeItem("activeModal");
};

// 모달 생성 함수
export const createModal = (modalContent, className) => {
  localStorage.setItem("activeModal", className);
  loadCSS("/css/modal.css");

  const modalHTML = `
      <div id="modal-overlay">
          <div id="modal">
              <div id="modal_contents">${modalContent}</div>
          </div>
      </div>
    `;

  document.body.insertAdjacentHTML("beforeend", modalHTML);

  // 배경 클릭 시 모달 닫기
  const modalOverlay = document.getElementById("modal-overlay");
  modalOverlay.addEventListener("click", (event) => {
    if (event.target === modalOverlay) {
      closeModal();
    }
  });

  const closeButton = document.querySelector(".close_btn");
  closeButton.addEventListener("click", closeModal);
};

export function loadCSS(href) {
  if (!document.querySelector(`link[href="${href}"]`)) {
    const link = document.createElement("link");
    link.rel = "stylesheet";
    link.type = "text/css";
    link.href = href;
    document.head.appendChild(link);
  }
}
