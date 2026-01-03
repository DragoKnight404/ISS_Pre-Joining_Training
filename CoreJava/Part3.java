/**
 Part 3: Loop Control and Decision Making
 Concepts Covered:
 1. Decision Making: if, else-if, switch
 2. Loops: for, for-each, while, do-while
 3. Jump Statements: break, continue
 */
public class Part3 {

    public static void main(String[] args) {

        // Raw Data: Marks for 6 subjects
        // Special codes: -1 (Absent), 999 (Malpractice/Error)
        int[] marks = {85, 92, -1, 45, 999, 78};

        System.out.println("--- 1. Loop Control (For Loop & Branching) ---");

        int totalScore = 0;
        int subjectsCounted = 0;

        // FOR LOOP: Standard iteration through the array
        for (int i = 0; i < marks.length; i++) {
            int score = marks[i];

            // LOGIC: Handle specific cases first
            if (score == -1) {
                System.out.println("Subject " + (i + 1) + ": Absent (Skipping calculation)");
                continue; // CONTINUE: Skips the rest of this iteration, goes to next i
            }

            if (score > 100) {
                System.out.println("Subject " + (i + 1) + ": INVALID DATA detected! Stopping processing.");
                break; // BREAK: Exits the loop completely immediately
            }

            // If we are here, the score is valid
            totalScore += score;
            subjectsCounted++;
            System.out.println("Subject " + (i + 1) + ": Added " + score);
        }


        System.out.println("\n--- 2. Decision Making (Switch Case) ---");

        if (subjectsCounted > 0) {
            int average = totalScore / subjectsCounted;
            String grade;

            // SWITCH STATEMENT: efficient for fixed values
            // We use the integer division trick (85/10 = 8) to create ranges
            switch (average / 10) {
                case 10:
                case 9:
                    grade = "A (Excellent)";
                    break; // Essential to stop falling through to next case
                case 8:
                    grade = "B (Very Good)";
                    break;
                case 7:
                    grade = "C (Good)";
                    break;
                case 6:
                    grade = "D (Pass)";
                    break;
                default:
                    grade = "F (Fail)";
            }
            System.out.println("Calculated Average: " + average);
            System.out.println("Final Grade: " + grade);

        } else {
            System.out.println("No valid marks to process.");
        }


        System.out.println("\n--- 3. While vs Do-While Loops ---");

        // WHILE LOOP: Checks condition FIRST. Might never run.
        int uploadRetries = 3;
        while (uploadRetries > 0) {
            System.out.println("Uploading results to server... (Attempt " + uploadRetries + ")");
            uploadRetries--;
        }

        // DO-WHILE LOOP: Runs code FIRST, then checks condition.
        // Guaranteed to run at least once. (Great for menus or initial prompts)
        int menuOption = 0;
        do {
            System.out.println("Generated Report Footer. (This always prints at least once)");
            menuOption++; // Simulating logic that changes the condition
        } while (menuOption < 1);
    }
}