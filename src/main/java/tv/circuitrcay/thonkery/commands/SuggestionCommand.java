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


import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import tv.circuitrcay.thonkery.execution.Command;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static tv.circuitrcay.thonkery.main.Thonkery.jda;

public class SuggestionCommand extends Command {
    public SuggestionCommand(String name, String help, Category category, boolean usableInDM) {
        super(name, help, category, usableInDM);
    }

    @Override
    public void execute(List<String> arguments, MessageReceivedEvent event) {
        if (arguments.size() == 0) event.getChannel().sendMessage("You need to include a suggestion!").queue();
        else {
        TextChannel channel = jda.getTextChannelById("339926027151081472");
        if (channel == null) System.out.println("The bug report channel wasn't found!");
        else {
            channel.sendMessage(new EmbedBuilder()
                    .setTitle("Suggestion").setColor(Color.BLUE)
                    .addField("Username", event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator(), false)
                    .addField("Suggestion", arguments.stream().collect(Collectors.joining(" ")), false).build()).queue();
        }
    }
}}
