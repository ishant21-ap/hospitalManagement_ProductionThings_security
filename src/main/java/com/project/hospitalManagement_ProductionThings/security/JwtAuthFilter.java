package com.project.hospitalManagement_ProductionThings.security;

import com.project.hospitalManagement_ProductionThings.model.User;
import com.project.hospitalManagement_ProductionThings.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j     // This annoation is used for logging coming from  lombok
public class JwtAuthFilter extends OncePerRequestFilter {

    // here we are making filter this will be added before dispatcher servlet


    private final UserRepository userRepository;

    private final AuthUtil authUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("incoming request: ", request.getRequestURI());


        // from request header we are taking out jwt
        final String requestTokenHeader = request.getHeader("Authorization");   // this is convention  that header is passed in Auhtorization
        // value inside authorization is passed with the help of Bearer and then token

        //check
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);    // If check then aage badho using doFilter
            return;
        }

        // Here token will be like this
        // Bearer enfiowogfofaofafndsnd
        // so what we are doing we are converting it in array and then it will be like
        // ["Bearer", "faeifanfkgnefafae"]
        // from here we are taking out token using index [1]
        String token = requestTokenHeader.split("Bearer ")[1];

        // Now taking out username
        String username = authUtil.getUsernameFromToken(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findByUsername(username).orElseThrow();
        // now fiters are responsible to store user detials in SecurityContextHolder
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
