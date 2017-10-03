package tv.circuitrcay.thonkery.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import tv.circuitrcay.thonkery.execution.Command;

import java.awt.*;
import java.util.List;

import static tv.circuitrcay.thonkery.main.Thonkery.factory;

public class HelpCommand extends Command {
    public HelpCommand(String name, String help, Category category, boolean usableInDM) {
        super(name, help, category, usableInDM);
    }

    @Override
    public void execute(List<String> arguments, MessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder()
                .setAuthor("Thonkery Command List", null, event.getAuthor().getEffectiveAvatarUrl())
                .setColor(Color.BLUE);
        factory.getCommands().forEach(command -> builder.appendDescription("+ **" +
                factory.getPrefix() + command.getName() + "**: *" + command.getHelp() + "*\n"));
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
