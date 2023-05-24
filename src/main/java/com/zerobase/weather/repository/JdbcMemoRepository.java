package com.zerobase.weather.repository;

import com.zerobase.weather.domain.Memo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemoRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Memo save(Memo memo) {
        String sql = "insert into memo value(?, ?)";
        jdbcTemplate.update(sql, memo.getId(), memo.getText());
        return memo;
    }

    public List<Memo> findAll() {
        String sql = "select * from memo";
        return jdbcTemplate.query(sql, memoRowMapper());
    }

    public Optional<Memo> findById(int id) {
        String sql = "select * from memo where id = ?";
        return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst();
    }

    // RowMapper => 데이터베이스의 반환 결과인 ResultSet를 객체로 변환해주는 클래스
    private RowMapper<Memo> memoRowMapper() {
        /*
        ResultSet
        { id = 1, text = 'this is memo~' }
         */
        return (rs, rowNum) -> new Memo(
                rs.getInt("id")
                , rs.getString("text")
        );
    }

}
