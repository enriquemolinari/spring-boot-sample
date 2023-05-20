package unrn.springboot.sample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  
  @RequestMapping(method = RequestMethod.GET,  path = "/hello")
  public String hello() {
    return "hola mundo!";
  }
  
  @GetMapping(path = "/hello2")
  public String hello2() {
    return "hola mundo 2!";
  }
}
