package ru.t1.frequency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.frequency.exception.BadRequestException;
import ru.t1.frequency.service.CharFrequencyService;

import java.util.Map;

@RestController
@RequestMapping("/frequency")
public class CharFrequencyController {
    private final CharFrequencyService frequencyService;

    public CharFrequencyController(CharFrequencyService frequencyService) {
        this.frequencyService = frequencyService;
    }

    @GetMapping("/{charSequence}")
    public Map<Character, Integer> getFrequency(@PathVariable String charSequence) {
        if (charSequence.length() > 500) {
            throw new BadRequestException("The string must not contain more than 500 characters");
        }

        String formattedSequence = charSequence.replaceAll(" ", "");

        return frequencyService.getFrequency(formattedSequence);
    }
}
