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
package tv.circuitrcay.thonkery;

import com.jagrosh.jdautilities.commandclient.CommandClientBuilder;
import com.jagrosh.jdautilities.commandclient.examples.PingCommand;
import com.jagrosh.jdautilities.commandclient.examples.ShutdownCommand;
import com.jagrosh.jdautilities.waiter.EventWaiter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.json.JSONObject;
import tv.circuitrcay.thonkery.commands.*;


import java.nio.file.Files;
import java.nio.file.Paths;

public class Bot {
    public static void main(String[] args) throws Exception {
        String config = new String(Files.readAllBytes(Paths.get("bot.json")));
        JSONObject object = new JSONObject(config);
        String token = object.getString("token");
        String ownerid = object.getString("ownerid");

        EventWaiter waiter = new EventWaiter();

        CommandClientBuilder client = new CommandClientBuilder();
        client.useDefaultGame();
        client.setOwnerId(ownerid);
        client.setPrefix("th/");
        client.addCommands(
                new PingCommand(),
                new EvalCommand(),
                new SayCommand(),
                new CatCommand(),
                new UrbanDictionaryCommand(),
                new RespectsCommand(),
                new SuggestionCommand(),
                new BugReportCommand(),
                new ShutdownCommand());

        new JDABuilder(AccountType.BOT)
                .setToken(token)
                .setAutoReconnect(true)
                .setGame(Game.of("Loading bot..."))
                .addEventListener(waiter)
                .addEventListener(client.build())
                .buildAsync();
    }
}
