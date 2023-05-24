package com.zerobase.weather.repository;

import com.zerobase.weather.domain.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class JpaMemoRepositoryTest {

    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest() {

        // given
        Memo memo = new Memo(10, "this is JPA MEMO!");

        // when
        jpaMemoRepository.save(memo);

        // then
        List<Memo> memoList = jpaMemoRepository.findAll();
        assertTrue(memoList.size() > 0);

    }

    @Test
    void findByIdTest() {

        // given
        Memo memo = new Memo(11, "JPA!");

        // when
        Memo save = jpaMemoRepository.save(memo);

        // then
        Optional<Memo> result = jpaMemoRepository.findById(save.getId());
        assertEquals(result.get().getText(), "JPA!");
    }

}