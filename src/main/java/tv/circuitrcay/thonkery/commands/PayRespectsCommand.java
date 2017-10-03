package tv.circuitrcay.thonkery.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import tv.circuitrcay.thonkery.execution.Command;

import java.util.List;

public class PayRespectsCommand extends Command {
    public PayRespectsCommand(String name, String help, Command.Category category, boolean usableInDM) {
        super(name, help, category, usableInDM);
    }

    @Override
    public void execute(List<String> arguments, MessageReceivedEvent event) {
        List<User> mentionedUsers = event.getMessage().getMentionedUsers();
        if (mentionedUsers.size() == 0) event.getChannel().sendMessage("You need to mention someone!").queue();
        else event.getChannel().sendMessage(new EmbedBuilder().setDescription(event.getAuthor().getAsMention() +
                " has paid their respects to" + mentionedUsers.get(0).getAsMention()).build()).queue();

    }
}
