package logic;
import static java.awt.event.KeyEvent.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Keyboard {

    private Robot robot;
    String characters;
    int i;
    Character character;

    public static void main(String... args) throws Exception {
        Keyboard keyboard = new Keyboard();
        keyboard.type("Hello there, how are you?");
    }

    public Keyboard() throws AWTException {
        this.robot = new Robot();
    }

    public Keyboard(Robot robot) {
        this.robot = robot;
    }

    private String substituteCmd(String input) {
        return input.replace("\\\\cmd\\\\", "\1").replace("\\\\cmdend\\\\","\1");
    }

    public void type(String input) {
        characters = substituteCmd(input);
        int length = characters.length();
        for (i = 0; i < length; i++) {
            character = characters.charAt(i);
            type();
        }
    }

    private void cmdDoType() {
        ArrayList<Integer> allkeys = new ArrayList<Integer>();
        allkeys.add(VK_META);
        i++;
        while(characters.charAt(i)!= '\1'){
            java.util.List keys = getIntFromChar(characters.charAt(i));
            allkeys.addAll(keys);
            i++;
            character = characters.charAt(i);
        }
        doType(toIntArray(allkeys));
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doType(int... keyCodes) {
        doType(keyCodes, 0, keyCodes.length);
    }

    int[] toIntArray(ArrayList<Integer> list)  {
        int[] ret = new int[list.size()];
        int i = 0;
        for (Integer e : list)
            ret[i++] = e.intValue();
        return ret;
    }

    private void doType(int[] keyCodes, int offset, int length) {
        if (length == 0) {
            return;
        }
        robot.keyPress(keyCodes[offset]);
        doType(keyCodes, offset + 1, length - 1);
        robot.keyRelease(keyCodes[offset]);
    }


    public void type() {
        switch (character) {
            case 'a':
                doType(VK_A);
                break;
            case 'b':
                doType(VK_B);
                break;
            case 'c':
                doType(VK_C);
                break;
            case 'd':
                doType(VK_D);
                break;
            case 'e':
                doType(VK_E);
                break;
            case 'f':
                doType(VK_F);
                break;
            case 'g':
                doType(VK_G);
                break;
            case 'h':
                doType(VK_H);
                break;
            case 'i':
                doType(VK_I);
                break;
            case 'j':
                doType(VK_J);
                break;
            case 'k':
                doType(VK_K);
                break;
            case 'l':
                doType(VK_L);
                break;
            case 'm':
                doType(VK_M);
                break;
            case 'n':
                doType(VK_N);
                break;
            case 'o':
                doType(VK_O);
                break;
            case 'p':
                doType(VK_P);
                break;
            case 'q':
                doType(VK_Q);
                break;
            case 'r':
                doType(VK_R);
                break;
            case 's':
                doType(VK_S);
                break;
            case 't':
                doType(VK_T);
                break;
            case 'u':
                doType(VK_U);
                break;
            case 'v':
                doType(VK_V);
                break;
            case 'w':
                doType(VK_W);
                break;
            case 'x':
                doType(VK_X);
                break;
            case 'y':
                doType(VK_Y);
                break;
            case 'z':
                doType(VK_Z);
                break;
            case 'A':
                doType(VK_SHIFT, VK_A);
                break;
            case 'B':
                doType(VK_SHIFT, VK_B);
                break;
            case 'C':
                doType(VK_SHIFT, VK_C);
                break;
            case 'D':
                doType(VK_SHIFT, VK_D);
                break;
            case 'E':
                doType(VK_SHIFT, VK_E);
                break;
            case 'F':
                doType(VK_SHIFT, VK_F);
                break;
            case 'G':
                doType(VK_SHIFT, VK_G);
                break;
            case 'H':
                doType(VK_SHIFT, VK_H);
                break;
            case 'I':
                doType(VK_SHIFT, VK_I);
                break;
            case 'J':
                doType(VK_SHIFT, VK_J);
                break;
            case 'K':
                doType(VK_SHIFT, VK_K);
                break;
            case 'L':
                doType(VK_SHIFT, VK_L);
                break;
            case 'M':
                doType(VK_SHIFT, VK_M);
                break;
            case 'N':
                doType(VK_SHIFT, VK_N);
                break;
            case 'O':
                doType(VK_SHIFT, VK_O);
                break;
            case 'P':
                doType(VK_SHIFT, VK_P);
                break;
            case 'Q':
                doType(VK_SHIFT, VK_Q);
                break;
            case 'R':
                doType(VK_SHIFT, VK_R);
                break;
            case 'S':
                doType(VK_SHIFT, VK_S);
                break;
            case 'T':
                doType(VK_SHIFT, VK_T);
                break;
            case 'U':
                doType(VK_SHIFT, VK_U);
                break;
            case 'V':
                doType(VK_SHIFT, VK_V);
                break;
            case 'W':
                doType(VK_SHIFT, VK_W);
                break;
            case 'X':
                doType(VK_SHIFT, VK_X);
                break;
            case 'Y':
                doType(VK_SHIFT, VK_Y);
                break;
            case 'Z':
                doType(VK_SHIFT, VK_Z);
                break;
            case '`':
                doType(VK_BACK_QUOTE);
                break;
            case '0':
                doType(VK_0);
                break;
            case '1':
                doType(VK_1);
                break;
            case '2':
                doType(VK_2);
                break;
            case '3':
                doType(VK_3);
                break;
            case '4':
                doType(VK_4);
                break;
            case '5':
                doType(VK_5);
                break;
            case '6':
                doType(VK_6);
                break;
            case '7':
                doType(VK_7);
                break;
            case '8':
                doType(VK_8);
                break;
            case '9':
                doType(VK_9);
                break;
            case '-':
                doType(VK_MINUS);
                break;
            case '=':
                doType(VK_EQUALS);
                break;
            case '~':
                doType(VK_SHIFT, VK_BACK_QUOTE);
                break;
            case '!':
                doType(VK_EXCLAMATION_MARK);
                break;
            case '@':
                doType(VK_AT);
                break;
            case '#':
                doType(VK_NUMBER_SIGN);
                break;
            case '$':
                doType(VK_DOLLAR);
                break;
            case '%':
                doType(VK_SHIFT, VK_5);
                break;
            case '^':
                doType(VK_CIRCUMFLEX);
                break;
            case '&':
                doType(VK_AMPERSAND);
                break;
            case '*':
                doType(VK_ASTERISK);
                break;
            case '(':
                doType(VK_LEFT_PARENTHESIS);
                break;
            case ')':
                doType(VK_RIGHT_PARENTHESIS);
                break;
            case '_':
                doType(VK_UNDERSCORE);
                break;
            case '+':
                doType(VK_PLUS);
                break;
            case '\t':
                doType(VK_TAB);
                break;
            case '\n':
                doType(VK_ENTER);
                break;
            case '[':
                doType(VK_OPEN_BRACKET);
                break;
            case ']':
                doType(VK_CLOSE_BRACKET);
                break;
            case '\\':
                doType(VK_BACK_SLASH);
                break;
            case '{':
                doType(VK_SHIFT, VK_OPEN_BRACKET);
                break;
            case '}':
                doType(VK_SHIFT, VK_CLOSE_BRACKET);
                break;
            case '|':
                doType(VK_SHIFT, VK_BACK_SLASH);
                break;
            case ';':
                doType(VK_SEMICOLON);
                break;
            case ':':
                doType(VK_COLON);
                break;
            case '\'':
                doType(VK_QUOTE);
                break;
            case '"':
                doType(VK_QUOTEDBL);
                break;
            case ',':
                doType(VK_COMMA);
                break;
            case '<':
                doType(VK_SHIFT, VK_COMMA);
                break;
            case '.':
                doType(VK_PERIOD);
                break;
            case '>':
                doType(VK_SHIFT, VK_PERIOD);
                break;
            case '/':
                doType(VK_SLASH);
                break;
            case '?':
                doType(VK_SHIFT, VK_SLASH);
                break;
            case ' ':
                doType(VK_SPACE);
                break;
            case '\1':
                cmdDoType();
                break;
            default:
                throw new IllegalArgumentException("Cannot type character " + character);
        }
    }



    public java.util.List getIntFromChar(char character) {
        switch (character) {
            case 'a':
                return Arrays.asList(Arrays.asList(VK_A));
            case 'b':
                return Arrays.asList(VK_B);

            case 'c':
                return Arrays.asList(VK_C);

            case 'd':
                return Arrays.asList(VK_D);

            case 'e':
                return Arrays.asList(VK_E);

            case 'f':
                return Arrays.asList(VK_F);

            case 'g':
                return Arrays.asList(VK_G);

            case 'h':
                return Arrays.asList(VK_H);

            case 'i':
                return Arrays.asList(VK_I);

            case 'j':
                return Arrays.asList(VK_J);

            case 'k':
                return Arrays.asList(VK_K);

            case 'l':
                return Arrays.asList(VK_L);

            case 'm':
                return Arrays.asList(VK_M);

            case 'n':
                return Arrays.asList(VK_N);

            case 'o':
                return Arrays.asList(VK_O);

            case 'p':
                return Arrays.asList(VK_P);

            case 'q':
                return Arrays.asList(VK_Q);

            case 'r':
                return Arrays.asList(VK_R);

            case 's':
                return Arrays.asList(VK_S);

            case 't':
                return Arrays.asList(VK_T);

            case 'u':
                return Arrays.asList(VK_U);

            case 'v':
                return Arrays.asList(VK_V);

            case 'w':
                return Arrays.asList(VK_W);

            case 'x':
                return Arrays.asList(VK_X);

            case 'y':
                return Arrays.asList(VK_Y);

            case 'z':
                return Arrays.asList(VK_Z);

            case 'A':
                return Arrays.asList(VK_SHIFT, VK_A);

            case 'B':
                return Arrays.asList(VK_SHIFT, VK_B);

            case 'C':
                return Arrays.asList(VK_SHIFT, VK_C);

            case 'D':
                return Arrays.asList(VK_SHIFT, VK_D);

            case 'E':
                return Arrays.asList(VK_SHIFT, VK_E);

            case 'F':
                return Arrays.asList(VK_SHIFT, VK_F);

            case 'G':
                return Arrays.asList(VK_SHIFT, VK_G);

            case 'H':
                return Arrays.asList(VK_SHIFT, VK_H);

            case 'I':
                return Arrays.asList(VK_SHIFT, VK_I);

            case 'J':
                return Arrays.asList(VK_SHIFT, VK_J);

            case 'K':
                return Arrays.asList(VK_SHIFT, VK_K);

            case 'L':
                return Arrays.asList(VK_SHIFT, VK_L);

            case 'M':
                return Arrays.asList(VK_SHIFT, VK_M);

            case 'N':
                return Arrays.asList(VK_SHIFT, VK_N);

            case 'O':
                return Arrays.asList(VK_SHIFT, VK_O);

            case 'P':
                return Arrays.asList(VK_SHIFT, VK_P);

            case 'Q':
                return Arrays.asList(VK_SHIFT, VK_Q);

            case 'R':
                return Arrays.asList(VK_SHIFT, VK_R);

            case 'S':
                return Arrays.asList(VK_SHIFT, VK_S);

            case 'T':
                return Arrays.asList(VK_SHIFT, VK_T);

            case 'U':
                return Arrays.asList(VK_SHIFT, VK_U);

            case 'V':
                return Arrays.asList(VK_SHIFT, VK_V);

            case 'W':
                return Arrays.asList(VK_SHIFT, VK_W);

            case 'X':
                return Arrays.asList(VK_SHIFT, VK_X);

            case 'Y':
                return Arrays.asList(VK_SHIFT, VK_Y);

            case 'Z':
                return Arrays.asList(VK_SHIFT, VK_Z);

            case '`':
                return Arrays.asList(VK_BACK_QUOTE);

            case '0':
                return Arrays.asList(VK_0);

            case '1':
                return Arrays.asList(VK_1);

            case '2':
                return Arrays.asList(VK_2);

            case '3':
                return Arrays.asList(VK_3);

            case '4':
                return Arrays.asList(VK_4);

            case '5':
                return Arrays.asList(VK_5);

            case '6':
                return Arrays.asList(VK_6);

            case '7':
                return Arrays.asList(VK_7);

            case '8':
                return Arrays.asList(VK_8);

            case '9':
                return Arrays.asList(VK_9);

            case '-':
                return Arrays.asList(VK_MINUS);

            case '=':
                return Arrays.asList(VK_EQUALS);

            case '~':
                return Arrays.asList(VK_SHIFT, VK_BACK_QUOTE);

            case '!':
                return Arrays.asList(VK_EXCLAMATION_MARK);

            case '@':
                return Arrays.asList(VK_AT);

            case '#':
                return Arrays.asList(VK_NUMBER_SIGN);

            case '$':
                return Arrays.asList(VK_DOLLAR);

            case '%':
                return Arrays.asList(VK_SHIFT, VK_5);

            case '^':
                return Arrays.asList(VK_CIRCUMFLEX);

            case '&':
                return Arrays.asList(VK_AMPERSAND);

            case '*':
                return Arrays.asList(VK_ASTERISK);

            case '(':
                return Arrays.asList(VK_LEFT_PARENTHESIS);

            case ')':
                return Arrays.asList(VK_RIGHT_PARENTHESIS);

            case '_':
                return Arrays.asList(VK_UNDERSCORE);

            case '+':
                return Arrays.asList(VK_PLUS);

            case '\t':
                return Arrays.asList(VK_TAB);

            case '\n':
                return Arrays.asList(VK_ENTER);

            case '[':
                return Arrays.asList(VK_OPEN_BRACKET);

            case ']':
                return Arrays.asList(VK_CLOSE_BRACKET);

            case '\\':
                return Arrays.asList(VK_BACK_SLASH);

            case '{':
                return Arrays.asList(VK_SHIFT, VK_OPEN_BRACKET);

            case '}':
                return Arrays.asList(VK_SHIFT, VK_CLOSE_BRACKET);

            case '|':
                return Arrays.asList(VK_SHIFT, VK_BACK_SLASH);

            case ';':
                return Arrays.asList(VK_SEMICOLON);

            case ':':
                return Arrays.asList(VK_COLON);

            case '\'':
                return Arrays.asList(VK_QUOTE);

            case '"':
                return Arrays.asList(VK_QUOTEDBL);

            case ',':
                return Arrays.asList(VK_COMMA);

            case '<':
                return Arrays.asList(VK_SHIFT, VK_COMMA);

            case '.':
                return Arrays.asList(VK_PERIOD);

            case '>':
                return Arrays.asList(VK_SHIFT, VK_PERIOD);

            case '/':
                return Arrays.asList(VK_SLASH);

            case '?':
                return Arrays.asList(VK_SHIFT, VK_SLASH);

            case ' ':
                return Arrays.asList(VK_SPACE);

            default:
                throw new IllegalArgumentException("Cannot type character " + character);
        }
    }
}

