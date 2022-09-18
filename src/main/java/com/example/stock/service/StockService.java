package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    // @Transactional
    public synchronized void decrease(Long id, Long quantity) {
        // Transactional 어노테이션으로 인해 동시성 문제 해결 안됨
        // 프록시로 만든 transaction 클래스의 동작방식
        // 트랜잭션을 시작 -> decrease() 호출 -> 트랜잭션 종료
        // 트랜잭션 종료 이전에 접근해온 쓰레드는 commit이 안된 값을 들고 있음


        Stock stock = stockRepository.findById(id).orElseThrow();

        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }

}
