package tn.esprit.pidev4sae2back.helpers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;


@Component
@Slf4j
public class BadWordFilter {

    private final  ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource("classpath:badwords.txt");

    public boolean checkBadWord(String content) throws IOException {

        File file = resource.getFile();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String badWord = line.toLowerCase();
            content = content.toLowerCase();
            if (content.contains(badWord) || content.equals(badWord)) {
                return true;
            }
        }
        return false;
    }
}
