package com.zerobase.weather.repository;

import com.zerobase.weather.domain.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class JdbcMemoRepositoryTest {

    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest() {

        // given
        Memo newMemo = new Memo(2, "this is second Memo!");

        // when
        jdbcMemoRepository.save(newMemo);

        // then
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "this is second Memo!");
    }

    @Test
    void findAllMemoTest() {

        // given
        List<Memo> memoList = jdbcMemoRepository.findAll();

        // when
        // then
        System.out.println("memoList = " + memoList);
        assertNotNull(memoList);
    }

}