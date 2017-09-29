package tv.circuitrcay.thonkery.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;


public class RespectsCommand extends Command {

    public RespectsCommand() {
        this.name = "f";
        this.help = "pay respects";
        this.aliases = new String[]{"respects"};
        this.guildOnly = false;
    }


    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder()
                .setDescription(event.getAuthor() + "has paid respects to" + event.getArgs());
        event.reply(eb.build());
    }
}
