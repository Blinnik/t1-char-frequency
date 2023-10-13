package ru.t1.frequency.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CharFrequencyService {
    public Map<Character, Integer> getFrequency(@PathVariable String charSequence) {
        Map<Character, Integer> charsByFrequencies = new HashMap<>();

        for (int i = 0; i < charSequence.length(); i++) {
            char curChar = charSequence.charAt(i);
            Integer curValue = charsByFrequencies.getOrDefault(curChar, 0);

            charsByFrequencies.put(curChar, ++curValue);
        }

        return charsByFrequencies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
