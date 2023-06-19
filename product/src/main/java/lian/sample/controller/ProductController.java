package lian.sample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Member;

@RestController
@RequiredArgsConstructor
public class ProductController {

    @PostMapping("/product")
    public String test() {

        return "hi";
    }
}
