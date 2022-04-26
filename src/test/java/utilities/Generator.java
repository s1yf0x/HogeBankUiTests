package utilities;

import com.github.javafaker.Faker;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;


public class Generator {

    public String generateUsername(boolean space) {
        Faker faker = new Faker();
        if (space) {
            return faker.name().fullName();
        }
        return faker.name().firstName();
    }

    public String generatePassword(int len, boolean uppr, boolean lowr, boolean num) {
        CharacterRule upperCase = new CharacterRule(EnglishCharacterData.UpperCase);
        CharacterRule numbers = new CharacterRule(EnglishCharacterData.Digit);
        CharacterRule lowerCase = new CharacterRule(EnglishCharacterData.LowerCase);

        List<CharacterRule> rules = new ArrayList<>();

        if (uppr) {
            rules.add(upperCase);
        }
        if (lowr) {
            rules.add(lowerCase);
        }
        if (num) {
            rules.add(numbers);
        }

        PasswordGenerator passwordGenerator = new PasswordGenerator();

        return passwordGenerator.generatePassword(len, rules);
    }
}
