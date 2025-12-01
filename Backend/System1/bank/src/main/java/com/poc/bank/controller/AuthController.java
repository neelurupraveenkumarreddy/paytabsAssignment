package com.poc.bank.controller;

import com.poc.bank.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetails;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(
                    body.get("username"), body.get("password")
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }

        UserDetails user = userDetails.loadUserByUsername(body.get("username"));
        String token = jwtUtil.createToken(user);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "role", user.getAuthorities().iterator().next().getAuthority()
        ));
    }
}
