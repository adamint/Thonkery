package tv.circuitrcay.thonkery.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.*;


public class SuggestionCommand extends Command {

    public SuggestionCommand() {
        this.name = "suggest";
        this.help = "suggest commands for Thonkery";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        TextChannel channel = event.getGuild().getTextChannelById("339926027151081472");
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Suggestion")
                .setColor(Color.BLUE)
                .addField("Username", event.getAuthor().getName()+"#"+event.getAuthor().getDiscriminator(), false)
                .addField("Suggestion", event.getArgs(), false);
        channel.sendMessage(eb.build()).queue();
    }
}
