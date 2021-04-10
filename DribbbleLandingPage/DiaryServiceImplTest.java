package com.example.ediary.services;

import com.example.ediary.dtos.CreateEntryDTO;
import com.example.ediary.models.Diary;
import com.example.ediary.models.Entry;
import com.example.ediary.repositories.DiaryRepository;
import com.example.ediary.repositories.EntryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DiaryServiceImplTest {
    @Mock
    DiaryRepository diaryRepository;

    @Mock
    EntryRepository entryRepository;

    @InjectMocks
    DiaryServiceImpl diaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEntryCanBeSavedIntoDiary() throws Exception {
        Diary diary = new Diary();
        Entry entry = new Entry();
        diary.setId("id11");
        diary.setDiaryName("diary");

        entry.setTitle("title");
        entry.setBody("body");


        CreateEntryDTO createEntryDTO = new CreateEntryDTO();
        createEntryDTO.setBody("body");
        createEntryDTO.setTitle("title");

        when(diaryRepository.save(any())).thenReturn(diary);
        when(diaryRepository.findById("id11")).thenReturn(java.util.Optional.of(diary));
        when(entryRepository.save(any())).thenReturn(entry);

       Entry savedEntry =  diaryService.saveEntryInDiary(createEntryDTO, "id11");
        assertEquals(savedEntry, entry);
        System.out.println(diary);
        System.out.println(entry);
        assertNotNull(diary.getEntries().get(0).getDateTime());
    }
}