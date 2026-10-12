package service;

import dao.MemberDAO;
import model.Member;

public class MemberService {

    private final MemberDAO memberDAO = new MemberDAO();

    public boolean register(String name, String email, String password) {
        if (password == null || password.isBlank()) {
            System.out.println("Password cannot be empty.");
            return false;
        }

        try {
            String passwordHash = PasswordUtil.hashPassword(password);

            return memberDAO.addMember(name, email, passwordHash);

        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    public Member login(String email, String password) {
        if (email == null || email.isBlank()
                || password == null || password.isEmpty()) {
            System.out.println("Please enter your email and password.");
            return null;
        }

        return memberDAO.login(email, password);
    }
}