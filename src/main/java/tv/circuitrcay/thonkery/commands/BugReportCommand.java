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
