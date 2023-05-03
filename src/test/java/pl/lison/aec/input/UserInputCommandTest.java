package pl.lison.aec.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserInputCommandTest {

    @Test
    void shouldBuildCorrectUserInputCommand() {
        //given
        String input = "mage, add, MageName";

        //when
        UserInputCommand userInputCommand = new UserInputCommand(input);
        //then
        assertEquals("mage", userInputCommand.getCommand());
        assertEquals("add", userInputCommand.getAction());
        assertLinesMatch(List.of("MageName"), userInputCommand.getParam());
    }

    @Test
    void shouldBuildCorrectUserInputCommandWithMultipleParams() {
        //given
        String input = "command, action, param 1, param 2, param 3";

        //when
        UserInputCommand userInputCommand = new UserInputCommand(input);
        //then
        assertEquals("command", userInputCommand.getCommand());
        assertEquals("action", userInputCommand.getAction());
        assertLinesMatch(List.of("param 1", "param 2", "param 3"), userInputCommand.getParam());
    }

    @Test
    void shouldBuildCorrectUserInputCommandWithoutParams() {
        //given
        String input = "command, action";

        //when
        UserInputCommand userInputCommand = new UserInputCommand(input);
        //then
        assertEquals("command", userInputCommand.getCommand());
        assertEquals("action", userInputCommand.getAction());
        assertTrue(userInputCommand.getParam().isEmpty());
    }


}