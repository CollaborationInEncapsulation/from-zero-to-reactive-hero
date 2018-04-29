package com.example.part_11.controller;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ViewController {

	@GetMapping("/")
	public Mono<String> index() {
		return Mono.just("index");
	}
}
