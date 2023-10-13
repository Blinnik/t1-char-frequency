package ru.t1.frequency.service;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharFrequencyServiceTest {
    private CharFrequencyService frequencyService = new CharFrequencyService();

    @Test
    void getFrequency_whenSequenceEmpty_thenReturnEmptyMap() {
        String sequence = "";

        Map<Character, Integer> expectedResult = Map.of();
        Map<Character, Integer> actualResult = frequencyService.getFrequency(sequence);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getFrequency_whenSequenceCorrect_thenReturnOrderedByValuesMap() {
        String sequence = "BaAaB12343432111";

        Map<Character, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put('1', 4);
        expectedResult.put('3', 3);
        expectedResult.put('a', 2);
        expectedResult.put('B', 2);
        expectedResult.put('2', 2);
        expectedResult.put('4', 2);
        expectedResult.put('A', 1);

        Map<Character, Integer> actualResult = frequencyService.getFrequency(sequence);

        assertEquals(expectedResult, actualResult);
    }
}