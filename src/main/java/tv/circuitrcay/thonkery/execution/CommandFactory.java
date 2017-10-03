package tv.circuitrcay.thonkery.execution;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CommandFactory {
    private final List<Command> commands = new ArrayList<>();
    private final Executor executor = Executors.newCachedThreadPool();
    private String prefix;
    private int receivedMessages = 0;
    private int receivedCommands = 0;

    public CommandFactory(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("Prefix cannot be null!");
        this.prefix = prefix;
    }

    public CommandFactory addCommand(Command cmd) {
        commands.add(cmd);
        return this;
    }

    @SubscribeEvent
    public void onMessage(MessageReceivedEvent event) {
        receivedMessages++;
        String content = event.getMessage().getRawContent();
        if (content.startsWith(prefix)) {
            String[] args = content.substring(prefix.length(), content.length()).split(" ");
            commands.forEach(command -> {
                if (args[0].equalsIgnoreCase(command.getName())) {
                    if (event.isFromType(ChannelType.TEXT) || command.isUsableInDM()) {
                        List<String> argsList = new ArrayList<>();
                        argsList.addAll(Arrays.asList(args).subList(1, args.length));
                        receivedCommands++;
                        executor.execute(() -> command.execute(argsList, event));
                    }
                    else event.getChannel().sendMessage("You can't use this command in PMs!").queue();
                }
            });
        }
    }

    public List<Command> getCommands() {
        return commands;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getReceivedCommands() {
        return receivedCommands;
    }

    public int getReceivedMessages() {
        return receivedMessages;
    }
}
