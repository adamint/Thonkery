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
 */
package tv.circuitrcay.thonkery.commands;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import tv.circuitrcay.thonkery.execution.Command;
import tv.circuitrcay.thonkery.utils.WebRequester;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import static tv.circuitrcay.thonkery.main.Thonkery.gson;

public class UrbanDictionaryCommand extends Command {
    public UrbanDictionaryCommand(String name, String help, Category category, boolean usableInDM) {
        super(name, help, category, usableInDM);
    }

    @Override
    public void execute(List<String> arguments, MessageReceivedEvent event) {
        if (arguments.size() == 0) event.getChannel().sendMessage("You need to include a search term!").queue();
        else {
            try {
                JsonObject json = gson.fromJson(WebRequester.REQUESTER.get("http://api.urbandictionary.com/v0/define?term=" +
                        URLEncoder.encode(arguments.stream().collect(Collectors.joining(" ")), "UTF-8")), JsonElement.class).getAsJsonObject();
                JsonObject result = json.get("list").getAsJsonArray().get(0).getAsJsonObject();
                String word = result.get("word").getAsString();
                String permalink = result.get("permalink").getAsString();
                String definition = result.get("definition").getAsString();
                event.getChannel().sendMessage(new EmbedBuilder()
                        .setTitle(word)
                        .addField("Definition", definition, false)
                        .addField("Link", permalink, false).build()).queue();
            } catch (IOException e) {
                event.getChannel().sendMessage("Oh no, an error occured! I've logged this exception so the developers can fix it.").queue();
                e.printStackTrace();
            }
        }
    }
}