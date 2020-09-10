package io.hungref.dangdol.service;

import io.hungref.dangdol.domain.cook.CookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CookService {
    private final CookRepository cookRepository;

    public void add(){

    }
}