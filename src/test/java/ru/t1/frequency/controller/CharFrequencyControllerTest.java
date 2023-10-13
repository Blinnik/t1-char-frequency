package ru.t1.frequency.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.t1.frequency.service.CharFrequencyService;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CharFrequencyController.class)
class CharFrequencyControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CharFrequencyService frequencyService;

    @Autowired
    private MockMvc mvc;

    private static final String URL = "/frequency";

    @Test
    void getFrequency_whenSequenceHasMoreThan500Chars_thenThrowBadRequestException() throws Exception {
        String sequenceWithMoreThan500Chars = new String(new char[501]).replace('\0', 'A');

        mvc.perform(get(URL + '/' + sequenceWithMoreThan500Chars))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorName", containsString("BadRequestException")))
                .andExpect(jsonPath("$.errorMessage",
                        containsString("The string must not contain more than 500 characters")));
    }

    @Test
    void getFrequency_whenSequenceCorrect_thenReturnMapOfValues() throws Exception {
        String sequence = "aaabbb cccddadc";

        when(frequencyService.getFrequency(sequence)).thenReturn(anyMap());

        mvc.perform(get(URL + '/' + sequence))
                .andExpect(status().isOk());
    }
}