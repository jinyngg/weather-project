package com.zerobase.weather.controller;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.service.DiaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    // @ApiOperation(value = "날씨 일기 생성", notes = "날씨 일기를 생성합니다.") => Swagger 설명 입력
    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장", notes = "")
    @PostMapping("/create/diary")
    public void createDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-mm-dd", example = "2023-05-26")
            LocalDate date
            , @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation(value = "선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    public List<Diary> readDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-mm-dd", example = "2023-05-26")
            LocalDate date) {
        return diaryService.readDiary(date);
    }

    @ApiOperation(value = "선택한 기간 중 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    public List<Diary> readDiaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 첫번째 날", example = "2023-05-26")
            LocalDate startDate
            , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 마지막 날", example = "2023-05-26")
            LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation(value = "선택한 날짜의 모든 일기 데이터를 수정합니다.")
    @PutMapping("/update/diary")
    public void updateDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-mm-dd", example = "2023-05-26")
            LocalDate date
            , @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @ApiOperation(value = "선택한 날짜의 모든 일기 데이터를 삭제합니다.")
    @DeleteMapping("/delete/diary")
    public void deleteDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-mm-dd", example = "2023-05-26")
            LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
