package tournament.controller.authentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tournament.service.AuthenticationService;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request,
            HttpServletResponse response
    ) {
        var authResponse = authService.register(request);
        response.addCookie(authService.createCookie(authResponse.getToken()));
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) {
        var authResponse = authService.authenticate(request);
        response.addCookie(authService.createCookie(authResponse.getToken()));
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        var cookie = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(it -> authService.COOKIE_NAME.equals(it.getName()))
                .findFirst();
        cookie.ifPresent(it -> {
            it.setMaxAge(0);
            it.setSecure(false);
        });
        return ResponseEntity.noContent().build();
    }

}
