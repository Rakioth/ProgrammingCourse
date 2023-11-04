package com.raks.swiftly.application.api;

import com.raks.swiftly.application.api.request.*;
import com.raks.swiftly.application.api.response.ClientFormResponse;
import com.raks.swiftly.application.api.response.RefreshResponse;
import com.raks.swiftly.application.util.CookieManager;
import com.raks.swiftly.application.api.response.LoginFormResponse;
import com.raks.swiftly.application.api.response.RegisterFormResponse;
import com.raks.swiftly.application.util.SessionManager;
import com.raks.swiftly.domain.model.dto.*;
import com.raks.swiftly.domain.model.enums.Gender;
import com.raks.swiftly.domain.model.enums.Role;
import com.raks.swiftly.domain.port.spi.AuthJpaPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthJpaPort _authService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest request) {
        RegisterFormResponse currentStep = RegisterFormResponse.builder()
                                                               .step("VerifyView")
                                                               .data(RegisterFormResponse.DataObject.builder()
                                                                                                    .email(request.getEmail())
                                                                                                    .build())
                                                               .build();

        SessionManager.update("register-form", currentStep);

        _authService.register(
                ConfirmationTokenDto.builder()
                                    .email(request.getEmail())
                                    .token(ConfirmationTokenDto.generateToken())
                                    .expiredDate(ConfirmationTokenDto.generateExpiredDate(10))
                                    .verified(false)
                                    .build()
        );
    }

    @PostMapping("/register/verify")
    public void verify(@Valid @RequestBody VerifyRequest request) {
        RegisterFormResponse currentStep = RegisterFormResponse.builder()
                                                               .step("AuthView")
                                                               .build();

        SessionManager.update("register-form", currentStep);

        _authService.verify(
                ConfirmationTokenDto.builder()
                                    .email(request.getEmail())
                                    .token(request.getCode())
                                    .build()
        );
    }

    @PostMapping("/register/auth")
    public void auth(@CookieValue(name = "_luser", defaultValue = "empty") String loggedUsers, @Valid @RequestBody AuthRequest request) {
        SessionManager.delete("register-form");

        TokenDto token = _authService.auth(
                UserDto.builder()
                       .username(request.getUsername())
                       .email(request.getEmail())
                       .password(request.getPassword())
                       .role(Role.USER)
                       .successAuth(0)
                       .failedAuth(0)
                       .build()
        );

        CookieManager.logUsername(loggedUsers, request.getUsername());
        CookieManager.create("access_token", token.getAccessToken());
    }

    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequest request) {
        LoginFormResponse currentStep = LoginFormResponse.builder()
                                                         .step("LogauthView")
                                                         .data(LoginFormResponse.DataObject.builder()
                                                                                           .username(request.getUsername())
                                                                                           .build())
                                                         .build();

        SessionManager.update("login-form", currentStep);
    }

    @PostMapping("/login/auth")
    public void logauth(@CookieValue(name = "_luser", defaultValue = "empty") String loggedUsers, @Valid @RequestBody LogauthRequest request) {
        SessionManager.delete("login-form");

        TokenDto token = _authService.login(
                UserDto.builder()
                       .username(request.getUsername())
                       .password(request.getPassword())
                       .build()
        );

        CookieManager.logUsername(loggedUsers, request.getUsername());
        CookieManager.create("access_token", token.getAccessToken());
    }

    @PostMapping("/login/forgot")
    public void forgot(@Valid @RequestBody ForgotRequest request) {
        SessionManager.create("email", request.getEmail());

        _authService.forgot(request.getEmail());
    }

    @PostMapping("/reset/{token}")
    public void reset(
            @PathVariable("token") String token,
            @Valid @RequestBody ResetRequest request
    ) {
        SessionManager.delete("email");

        _authService.reset(
                RecoveryTokenDto.builder()
                                .token(token)
                                .user(UserDto.builder().password(request.getPassword()).build())
                                .build()
        );
    }

    @PostMapping("/refresh")
    public RefreshResponse reset(@CookieValue("refresh_token") String refreshToken) {
        return RefreshResponse.builder()
                              .accessToken(_authService.refresh(refreshToken))
                              .build();
    }

    @PostMapping("/client/info")
    public void clientInfo(@Valid @RequestBody ClientInfoRequest request) {
        ClientFormResponse currentStep = ClientFormResponse.builder()
                                                           .stepNumber(2)
                                                           .step("FormDetailsView")
                                                           .build();

        ClientFormResponse.DataObject currentData = ClientFormResponse.DataObject.builder()
                                                                                 .name(request.getName())
                                                                                 .surnames(request.getSurnames())
                                                                                 .genderCode(request.getGenderCode())
                                                                                 .birthdate(request.getBirthdate())
                                                                                 .build();

        SessionManager.update("client-form", currentStep);
        SessionManager.update("client-data", currentData);
    }

    @PostMapping("/client/details")
    public void clientDetails(@Valid @RequestBody ClientDetailsRequest request) {
        ClientFormResponse currentStep = ClientFormResponse.builder()
                                                           .stepNumber(3)
                                                           .step("FormOtherView")
                                                           .build();

        ClientFormResponse.DataObject currentData = ClientFormResponse.DataObject.builder()
                                                                                 .documentTypeCode(request.getDocumentTypeCode())
                                                                                 .document(request.getDocument())
                                                                                 .countryTypeCode(request.getCountryTypeCode())
                                                                                 .phoneNumber(request.getPhoneNumber())
                                                                                 .addrViaCode(request.getAddrViaCode())
                                                                                 .addrName(request.getAddrName())
                                                                                 .addrNumber(request.getAddrNumber())
                                                                                 .addrPortal(request.getAddrPortal())
                                                                                 .addrFloor(request.getAddrFloor())
                                                                                 .addrLocality(request.getAddrLocality())
                                                                                 .addrRegion(request.getAddrRegion())
                                                                                 .addrPostalCode(request.getAddrPostalCode())
                                                                                 .build();

        SessionManager.update("client-form", currentStep);
        SessionManager.update("client-data", currentData);
    }

    @PostMapping("/client/other")
    public void clientOther(@Valid @RequestBody ClientOtherRequest request, @CookieValue("access_token") String accessToken) {
        ClientFormResponse.DataObject data = (ClientFormResponse.DataObject) SessionManager.read("client-data");

        SessionManager.delete("client-form");
        SessionManager.delete("client-data");

        _authService.createClient(
                ClientDto.builder()
                         .gender(Gender.valueOf(data.getGenderCode()))
                         .birthdate(data.getBirthdate())
                         .countryCode(CountryTypeDto.builder().code(data.getCountryTypeCode()).build())
                         .documentType(ClientDocumentDto.builder().code(data.getDocumentTypeCode()).build())
                         .document(data.getDocument())
                         .phoneNumber(data.getPhoneNumber())
                         .name(data.getName())
                         .surnames(data.getSurnames())
                         .address(AddressDto.builder()
                                            .via(ViaTypeDto.builder().code(data.getAddrViaCode()).build())
                                            .name(data.getAddrName())
                                            .number(data.getAddrNumber())
                                            .portal(data.getAddrPortal())
                                            .floor(data.getAddrFloor())
                                            .locality(data.getAddrLocality())
                                            .region(data.getAddrRegion())
                                            .postalCode(data.getAddrPostalCode())
                                            .build())
                         .clientCreditCards(List.of(CreditCardDto.builder()
                                                                 .type(CardTypeDto.builder().code(request.getCardTypeCode()).build())
                                                                 .number(request.getCardNumber())
                                                                 .ccv(request.getCardCcv())
                                                                 .expiredDate(request.getCardExpiredDate())
                                                                 .build()))
                         .accumulatedExpenses(BigDecimal.ZERO)
                         .type(ClientTypeDto.builder().code("bronze").build())
                         .comments(request.getComments())
                         .license(request.getLicense())
                         .build(),
                accessToken
        );
    }

    @GetMapping("/email/confirmation")
    public void resendConfirmation() {
        RegisterFormResponse registerForm = (RegisterFormResponse) SessionManager.read("register-form");

        _authService.resendConfirmation(registerForm.getData().getEmail());
    }

    @GetMapping("/email/recovery")
    public void resendRecovery() {
        String email = (String) SessionManager.read("email");

        _authService.resendRecovery(email);
    }

    @GetMapping("/form/register")
    public Object registerForm() {
        Object currentStep = SessionManager.read("register-form");

        if (currentStep == null) {
            currentStep = RegisterFormResponse.builder()
                                              .step("RegisterView")
                                              .build();
            SessionManager.create("register-form", currentStep);
        }

        return currentStep;
    }

    @GetMapping("/form/login")
    public Object loginForm() {
        Object currentStep = SessionManager.read("login-form");

        if (currentStep == null) {
            currentStep = LoginFormResponse.builder()
                                           .step("LoginView")
                                           .build();
            SessionManager.create("login-form", currentStep);
        }

        return currentStep;
    }

    @GetMapping("/form/client")
    public Object clientForm() {
        ClientFormResponse            currentStep = (ClientFormResponse) SessionManager.read("client-form");
        ClientFormResponse.DataObject currentData = (ClientFormResponse.DataObject) SessionManager.read("client-data");

        if (currentStep == null) {
            currentStep = ClientFormResponse.builder()
                                            .stepNumber(1)
                                            .step("FormInfoView")
                                            .build();
            SessionManager.create("client-form", currentStep);
        }

        if (currentData == null) {
            currentData = ClientFormResponse.DataObject.builder().build();
            SessionManager.create("client-data", currentData);
        }

        return ClientFormResponse.builder()
                                 .stepNumber(currentStep.getStepNumber())
                                 .step(currentStep.getStep())
                                 .data(currentData)
                                 .build();
    }

}