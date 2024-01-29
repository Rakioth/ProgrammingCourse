package com.raks.swiftly.application.controller;

import com.raks.swiftly.application.controller.request.UserRequest;
import com.raks.swiftly.domain.model.helper.ClientRequestDto;
import com.raks.swiftly.domain.model.helper.ProductRequestDto;
import com.raks.swiftly.domain.model.dto.*;
import com.raks.swiftly.domain.port.spi.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserJpaPort       _userService;
    private final ClientJpaPort     _clientService;
    private final ClientTypeJpaPort _clientTypeService;
    private final OrderJpaPort      _orderService;
    private final ProductJpaPort    _productService;
    private final CategoryJpaPort   _categoryService;
    private final SupplierJpaPort   _supplierService;
    private final CatalogJpaPort    _catalogService;
    private final WarningJpaPort    _warningService;
    private final PromotionJpaPort  _promotionService;

    @GetMapping("/login")
    public ModelAndView getLogin() {
        List<UserDto> response = _userService.getAdmins();

        return new ModelAndView("admin-login-page",
                                Map.of("listAdminUsers", response)
        );
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashboard() {
        return new ModelAndView("admin-page");
    }

    @GetMapping("/dashboard/users")
    public ModelAndView getUsers() {
        List<UserDto> response = _userService.getAll();

        return new ModelAndView("dashboard/users/users",
                                Map.of("usersResponse", response)
        );
    }

    @GetMapping("/dashboard/users/{id}")
    public ModelAndView getUser(@PathVariable("id") Long id) {
        UserDto response = _userService.read(id);

        return new ModelAndView("dashboard/users/user",
                                Map.of("userResponse", response)
        );
    }

    @PostMapping("/dashboard/users/{id}")
    public ModelAndView postUser(@PathVariable("id") Long id, @Valid UserRequest request, BindingResult bindingResult) {
        List<FieldError> errors   = bindingResult.getFieldErrors("releaseBlock");
        UserDto          response = _userService.read(id);

        if (errors.isEmpty()) {
            response.setReleaseBlock(request.getReleaseBlock());
            _userService.update(response);
        }

        return new ModelAndView("dashboard/users/user",
                                Map.of("userResponse", response,
                                       "userError", errors)
        );
    }

    @GetMapping("/dashboard/clients")
    public ModelAndView getClients() {
        List<ClientDto>     response        = _clientService.getAll();
        List<ClientTypeDto> listClientTypes = _clientTypeService.getAll();

        return new ModelAndView("dashboard/clients/clients",
                                Map.of("clientsRequest", new ClientRequestDto(),
                                       "clientsResponse", response,
                                       "listClientTypes", listClientTypes)
        );
    }

    @PostMapping("/dashboard/clients")
    public ModelAndView postClients(@Valid ClientRequestDto request, BindingResult bindingResult) {
        List<ClientDto>     response        = _clientService.getByParameters(request);
        List<ClientTypeDto> listClientTypes = _clientTypeService.getAll();

        return new ModelAndView("dashboard/clients/clients",
                                Map.of("clientsRequest", request,
                                       "clientsResponse", response,
                                       "listClientTypes", listClientTypes)

        );
    }

    @GetMapping("/dashboard/orders")
    public ModelAndView getOrders() {
        List<OrderDto> response = _orderService.getAll();

        return new ModelAndView("dashboard/orders/orders",
                                Map.of("ordersResponse", response)
        );
    }

    @GetMapping("/dashboard/products")
    public ModelAndView getProducts() {
        List<ProductDto> response = _productService.getAllAdmin();

        return new ModelAndView("dashboard/products/products",
                                Map.of("productsResponse", response)
        );
    }

    @GetMapping("/dashboard/products/{id}")
    public ModelAndView getProduct(@PathVariable("id") Long id) {
        ProductDto response = _productService.read(id);

        return new ModelAndView("dashboard/products/product",
                                Map.of("productResponse", response)
        );
    }

    @PostMapping("/dashboard/products/{id}")
    public ModelAndView postProduct(@PathVariable("id") Long id, @Valid ProductRequestDto request, BindingResult bindingResult) {
        ProductDto response = _productService.read(id);
        if (request.getBrand() != null) response.setBrand(request.getBrand());
        if (request.getModel() != null) response.setModel(request.getModel());
        if (request.getPrice() != null) response.setPrice(request.getPrice());
        if (request.getStock() != null) response.setStock(request.getStock());
        if (request.getSoldUnits() != null) response.setSoldUnits(request.getSoldUnits());
        if (request.getHiddenThreshold() != null) response.setHiddenThreshold(request.getHiddenThreshold());
        if (request.getRequestThreshold() != null) response.setRequestThreshold(request.getRequestThreshold());
        if (request.getRating() != null) response.setRating(request.getRating());
        if (request.getComments() != null) response.setComments(request.getComments());
        if (request.getOnSale() != null) response.setOnSale(request.getOnSale());
        if (request.getIsNew() != null) response.setIsNew(request.getIsNew());

        _productService.update(response);

        return new ModelAndView("dashboard/products/product",
                                Map.of("productResponse", response)
        );
    }

    @GetMapping("/dashboard/categories")
    public ModelAndView getCategories() {
        List<CategoryDto> response = _categoryService.getAll();

        return new ModelAndView("dashboard/categories/categories",
                                Map.of("categoriesResponse", response)
        );
    }

    @GetMapping("/dashboard/categories/{id}")
    public ModelAndView getCategory(@PathVariable("id") Long id) {
        CategoryDto response = _categoryService.read(id);

        return new ModelAndView("dashboard/categories/category",
                                Map.of("categoryResponse", response)
        );
    }

    @GetMapping("/dashboard/suppliers")
    public ModelAndView getSuppliers() {
        List<SupplierDto> response = _supplierService.getAll();

        return new ModelAndView("dashboard/suppliers/suppliers",
                                Map.of("suppliersResponse", response)
        );
    }

    @GetMapping("/dashboard/suppliers/{id}")
    public ModelAndView getCatalog(@PathVariable("id") Long id) {
        CatalogDto response = _catalogService.getBySupplierId(id);

        return new ModelAndView("dashboard/suppliers/catalog",
                                Map.of("catalogResponse", response)
        );
    }

    @GetMapping("/dashboard/promotions")
    public ModelAndView getPromotions() {
        List<PromotionDto> response = _promotionService.getAll();

        return new ModelAndView("dashboard/promotions/promotions",
                                Map.of("promotionsResponse", response)
        );
    }

    @GetMapping("/dashboard/warnings")
    public ModelAndView getWarnings() {
        List<WarningDto> response = _warningService.getUnprocessed();

        return new ModelAndView("dashboard/warnings/warnings",
                                Map.of("warningsResponse", response)
        );
    }

}