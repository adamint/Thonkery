/*
 Copyright 2017 CircuitRCAY

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 **/
package tv.circuitrcay.thonkery.main;

import com.google.gson.Gson;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;
import tv.circuitrcay.thonkery.commands.*;
import tv.circuitrcay.thonkery.execution.Command;
import tv.circuitrcay.thonkery.execution.CommandFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Thonkery {
    public static final Gson gson = new Gson();
    public static JDA jda = null;
    public static CommandFactory factory = new CommandFactory("th/");

    public static void main(String[] args) throws Exception {
        Config config = new Config("C:\\Users\\Adam\\Desktop\\thonkery.txt", " :: ");
        factory.addCommand(new PayRespectsCommand("respects", "pay respects to someone", Command.Category.MISCELLANEOUS, false))
                .addCommand(new SayCommand("say", "copies you, duh!", Command.Category.MISCELLANEOUS, true))
                .addCommand(new CatCommand("cat", "get a random cat", Command.Category.MISCELLANEOUS, true))
                .addCommand(new EvalCommand("eval", "only for the owner! evals code", Command.Category.UTILIY, true))
                .addCommand(new UrbanDictionaryCommand("urban", "get urban dictionary definitions!", Command.Category.UTILIY, true))
                .addCommand(new SuggestionCommand("suggestion", "make a suggestion", Command.Category.UTILIY, true))
                .addCommand(new BugReportCommand("bugreport", "report a bug", Command.Category.UTILIY, true))
                .addCommand(new HelpCommand("help", "get help", Command.Category.UTILIY, true));
        jda = new JDABuilder(AccountType.BOT)
                .setEventManager(new AnnotatedEventManager())
                .setToken(config.get("token"))
                .setAutoReconnect(true)
                .setGame(Game.of("th/help - Thonkery Beta!"))
                .addEventListener(factory)
                .buildBlocking();
    }

    static class Config {
        private HashMap<String, String> keyPairs = new HashMap<>();

        Config(String path, String seperator) throws IOException {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> {
                String[] split = line.split(seperator);
                if (split.length == 2) keyPairs.put(split[0], split[1]);
            });
        }

        String get(String key) {
            return keyPairs.get(key);
        }
    }
}
