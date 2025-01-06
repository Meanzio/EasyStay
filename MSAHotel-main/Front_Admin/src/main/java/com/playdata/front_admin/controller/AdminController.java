package com.playdata.front_admin.controller;

import com.playdata.front_admin.dto.TableDTO;
import com.playdata.front_admin.entity.User;
import com.playdata.front_admin.service.ProfileImg;
import com.playdata.front_admin.service.TableService;
import com.playdata.front_admin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final TableService tableService;
    private final ProfileImg profileImg;

    @Autowired
    public AdminController(UserService userService, TableService tableService, ProfileImg profileImg) {
        this.userService = userService;
        this.tableService = tableService;
        this.profileImg = profileImg;
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "/user/login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "/join/register";
    }

    @PostMapping("/join/complete")
    public String userRegister(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "nickname") String nickname,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "pass") String pass,
            @RequestParam(name = "addr") String addr,
            @RequestParam(name = "sex") String sex,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "birthDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
            @RequestParam(name = "image", value = "image", required = false) MultipartFile image, // Allow image upload
            @RequestParam String confirmPass,
            RedirectAttributes redirectAttributes) {
        // 비밀번호 확인
        if (!pass.equals(confirmPass)) {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/admin/join";
        }

        // 생년월일 확인
        if (birthDate == null) {
            redirectAttributes.addFlashAttribute("error", "생년월일을 올바르게 입력해주세요.");
            return "redirect:/admin/join";
        }

        String profileImgPath = "default-profile.png";

        if (image != null && !image.isEmpty()) {
            try {
                profileImgPath = profileImg.saveFile(image);
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("error", "이미지 업로드 중 오류가 발생했습니다.");
                return "redirect:/admin/join";
            }
        }

        User newUser = User.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(pass)
                .birthDate(birthDate)
                .profileImg(profileImgPath)
                .sex(sex)
                .phone(phone)
                .addr(addr)
                .build();

        try {
            userService.save(newUser);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "사용자 등록 중 문제가 발생했습니다.");
            return "redirect:/admin/join";
        }

        return "redirect:/admin/list";
    }

    //
    // @GetMapping("/wellcome")
    // public String welcomePage(@PathVariable Long id, Model model) {
    // Optional<User> user = userService.findById(id);
    // user.ifPresent(value -> model.addAttribute("user", value));
    // return "/join/wellcome";
    // }

    // 회원 목록
    @GetMapping("/list")
    public String listUsers(@RequestParam(defaultValue = "0") String pageNo, Model model) {
        int page = Integer.parseInt(pageNo);
        int pageSize = 10;
        Pageable pageable = tableService.getPageable(page, pageSize);
        Page<User> userPage = userService.userPage(pageable);
        TableDTO tableDTO = tableService.getPageInfo(userPage);
        model.addAttribute("userList", userPage.getContent());
        model.addAttribute("currentPage", tableDTO.getCurrentPage());
        model.addAttribute("totalPages", tableDTO.getTotalPages());
        model.addAttribute("prevPage", tableDTO.getPrevPage());
        model.addAttribute("nextPage", tableDTO.getNextPage());
        model.addAttribute("startIndex", page * pageSize);
        return "/user/list";
    }

    // 회원 상세
    @GetMapping("/mypage/{id}")
    public String userDetailPage(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "/user/mypage";
        } else {
            model.addAttribute("error", "사용자를 찾을 수 없습니다.");
            return "/user/mypage_edit";
        }
    }

    // 회원 삭제
    @PostMapping("list/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {
        try {
            userService.deleteById(id); // 사용자 삭제
        } catch (Exception e) {
            model.addAttribute("error", "사용자를 삭제하는 중 문제가 발생했습니다.");
            return "redirect:/admin/list?error=true"; // 에러 처리 후 리다이렉트
        }
        return "redirect:/admin/list"; // 성공 시 목록 페이지로 리다이렉트
    }

    // 회원 수정 페이지
    @GetMapping("/mypage/{id}/edit")
    public String updateUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "/user/mypage_edit";
        } else {
            model.addAttribute("error", "사용자를 찾을 수 없습니다.");
            return "/user/mypage_edit";
        }
    }

    // 회원 정보 수정
    @PostMapping("/mypage/{id}/edit")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user,
            @RequestParam(value = "image", required = false) MultipartFile image,
            Model model) throws IOException {
        User existingUser = userService.findById(id).orElse(null);
        if (existingUser != null) {
            // 기존 이미지 삭제
            if (existingUser.getProfileImg() != null && !existingUser.getProfileImg().isEmpty()) {
                profileImg.deleteFile(existingUser.getProfileImg());
            }

            // 새 이미지 업로드
            if (image != null && !image.isEmpty()) {
                String imagePath = profileImg.updateFile(image, existingUser.getProfileImg());
                existingUser.setProfileImage(imagePath);
            }

            // 사용자 정보 업데이트
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAddr(user.getAddr());
            existingUser.setNickname(user.getNickname());
            existingUser.setBirthDate(user.getBirthDate());
            existingUser.setSex(user.getSex());
            existingUser.setPhone(user.getPhone());

            userService.save(existingUser);
            model.addAttribute("user", existingUser);

            return "redirect:/admin/mypage/" + id;
        } else {
            model.addAttribute("error", "사용자를 찾을 수 없습니다.");
            return "/user/mypage_edit";
        }
    }
}
