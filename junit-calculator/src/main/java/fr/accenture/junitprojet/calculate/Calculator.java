package fr.accenture.junitprojet.calculate;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Calculator {
    public int integerDivision(int i, int j) {
        return i/j;
    }

    public int integerSubtraction(int minuend, int subtrahend) {
        return minuend - subtrahend;
    }
}
