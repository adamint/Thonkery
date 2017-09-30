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
package tv.circuitrcay.thonkery.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.*;

public class BugReportCommand extends Command {

    public BugReportCommand() {
        this.name = "report";
        this.help = "report a bug in Thonkery";
        this.category = new Category("Support");
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        TextChannel channel = event.getGuild().getTextChannelById("339345868471664640");
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Bug Report :eyes:")
                .setColor(Color.RED)
                .addField("Username", event.getAuthor().getName()+"#"+event.getAuthor().getDiscriminator(), false)
                .addField("Suggestion", event.getArgs(), false);
        channel.sendMessage(eb.build()).queue();
    }
}
