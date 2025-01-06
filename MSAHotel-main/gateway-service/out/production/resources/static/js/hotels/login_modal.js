import { createModal, closeModal } from "./modal.js"; // modal.js에서 함수 임포트

// 로그인 모달 토글 함수
function LoginToggle(classList) {
  classList.forEach(({ className, modalContent }) => {
    document.querySelectorAll(`.${className}`).forEach((button) => {
      button.addEventListener("click", () => {
        createModal(modalContent, className);
        eventListener(this);
      });
    });
  });

  const savedModal = localStorage.getItem("activeModal");
  if (savedModal) {
    const modalData = classList.find((item) => item.className === savedModal);
    if (modalData) createModal(modalData.modalContent, savedModal);
  }
}

function setLogin() {
  // 사용 예시
  LoginToggle([
    {
      className: "user_login_triger",
      modalContent: `
          <div class="modal_head">
              <div>
                  <p>EasyStay</p>
                  <h1>사용자 로그인</h1>
              </div>
              <button class="close_btn" onclick="closeModal()">
                  <span class="close_icon"></span>
              </button>
          </div>
          <form id="login-form">
              <div class="id_input">
                  <label for="user-id">아이디</label>
                  <div class="input_caption" id="id_caption">
                      <input type="text" id="user-id" name="user-id" required>
                  </div>
              </div>
              <div class="pass_input">
                  <label for="user-password">비밀번호</label>
                  <div class="input_caption" id="pass_caption">
                      <input type="password" id="user-password" name="user-password" required>
                  </div>
              </div>
              <div class="user_signup">
                  <p>아직도 계정이 없으신가요?</p>
                  <a class="line_ceter_text" href="./user/signup.html">회원가입</a>
              </div>
              <button class="login_btn" type="submit">로그인</button>
          </form>
      `,
    },
    {
      className: "admin_login_triger",
      modalContent: `
          <div class="modal_head">
              <div>
                  <p>EasyStay</p>
                  <h1>관리자 로그인</h1>
              </div>
              <button class="close_btn" onclick="closeModal()">
                  <span class="close_icon"></span>
              </button>
          </div>
          <form>
              <div class="id_input">
                  <label for="user-id">아이디</label>
                  <div class="input_caption" id="id_caption">
                      <input type="text" id="user-id" name="user-id" required>
                  </div>
              </div>
              <div class="pass_input">
                  <label for="user-password">비밀번호</label>
                  <div class="input_caption" id="pass_caption">
                      <input type="password" id="user-password" name="user-password" required>
                  </div>
              </div>
              <button class="login_btn" type="submit">로그인</button>
          </form>
      `,
    },
  ]);
}

function eventListener(that) {
  const form = document.querySelector("#login-form");
  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const email = document.querySelector("#login-form #user-id").value;
    const password = document.querySelector("#login-form #user-password").value;

    login(email, password)
      .then(({ data }) => {
        console.log(data);
        alert(`로그인 성공 : ${email}`);
        sessionStorage.setItem("token", data);
        sessionStorage.setItem("email", email);
        checkLogin();
        closeModal();
      })
      .catch((e) => {
        console.error(e);
        alert("로그인 실패");
      })
      .finally(() => {});
  });
}

/**
 * 로그인 체크
 * @param {*} email 이메일
 * @param {*} password 패스워드
 * @returns
 */
function login(email, password) {
  return axios.post(
    `http://localhost:7777/api/login?email=${email}&password=${password}`
  );
}

function checkLogin() {
  const loginButtons = document.querySelector("div.header ul.register");
  const email = sessionStorage.getItem("email");
  console.log(loginButtons);
  console.log(email);

  if (email !== undefined && email !== null && email !== "") {
    loginButtons.innerHTML = `
    <li></li>
     <li><a class="btn_rl" id="logout_btn">로그아웃</a></li>
      `;

    document
      .querySelector("#logout_btn")
      .addEventListener("click", function (event) {
        sessionStorage.setItem("email", "");
        sessionStorage.setItem("token", "");
        checkLogin();
        setLogin();
      });
  } else {
    loginButtons.innerHTML = `
    <li><a class="btn_rl user_login_triger">로그인</a></li>
	<li><a class="btn_rl" href="./user/signup.html">회원가입</a></li>
    `;
  }
}

(function () {
  checkLogin();
  setLogin();
})();
