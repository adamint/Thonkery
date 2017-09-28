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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import tv.circuitrcay.thonkery.utils.HTTP;

import java.io.IOException;

public class UrbanDictionaryCommand extends Command {

    public UrbanDictionaryCommand() {
        this.name = "urban";
        this.help = "searches Urban Dictionary";
        this.guildOnly = false;
        this.category = new Category("Miscellaneous");
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
    }

    @Override
    protected void execute(CommandEvent event) {
        try {
            HTTP req = new HTTP();
            String res = req.get("http://api.urbandictionary.com/v0/define?term=" + event.getArgs().toLowerCase());
            Gson gson = new GsonBuilder().create();
            JsonObject json = gson.fromJson(res, JsonElement.class).getAsJsonObject();
            JsonObject result = json.get("list").getAsJsonArray().get(0).getAsJsonObject();
            String word = result.get("word").getAsString();
            String permalink = result.get("permalink").getAsString();
            String definition = result.get("definition").getAsString();
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle(word)
                    .addField("Definition", definition, false)
                    .addField("Link", permalink, false);
            event.reply(embed.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
