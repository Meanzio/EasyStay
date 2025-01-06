
function validateForm() {
    const password = document.getElementById("pass").value;
    const confirmPassword = document.getElementById("confirm-pass").value;
    const passwordError = document.getElementById("password-error");

    if (password !== confirmPassword) {
        passwordError.textContent = "비밀번호가 일치하지 않습니다.";
        return false;
    }

    passwordError.textContent = "";
    return true;
}

function checkNickname() {
    const nickname = document.getElementById("nickname").value;
    const nicknameError = document.getElementById("nickname-error");

    if (!nickname) {
        nicknameError.textContent = "닉네임을 입력하세요.";
        return;
    }

    fetch(`/check-nickname?nickname=${nickname}`)
        .then(response => response.json())
        .then(data => {
            nicknameError.textContent = data.exists ? "이미 사용 중인 닉네임입니다." : "사용 가능한 닉네임입니다.";
        })
        .catch(() => {
            nicknameError.textContent = "닉네임 확인 중 오류가 발생했습니다.";
        });
}

function triggerFileInput() {
    document.getElementById('profileImg').click();
}

function updatePreviewImage(input) {
    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            document.getElementById('preview-image').src = e.target.result;
        };

        reader.readAsDataURL(input.files[0]);
    }
}