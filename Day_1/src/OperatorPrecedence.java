public class OperatorPrecedence {
    public static void main(String[] args) {
        // Highest to lowest (like math — BODMAS/PEMDAS)
        // 1. ()         parentheses
        // 2. ++ --      increment/decrement
        // 3. * / %      multiplication, division, modulo
        // 4. + -        addition, subtraction
        // 5. < > <= >=  comparison
        // 6. == !=      equality
        // 7. &&         logical AND
        // 8. ||         logical OR
        // 9. =          assignment

        int result = 2 + 3 * 4;    // 14, not 20 (multiplication first)
        int result2 = (2 + 3) * 4; // 20 (parentheses first)

        System.out.println(result);
        System.out.println(result2);
    }
}
