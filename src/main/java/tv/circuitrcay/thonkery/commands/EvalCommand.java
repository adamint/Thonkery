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

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import tv.circuitrcay.thonkery.execution.Command;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.List;
import java.util.stream.Collectors;

public class EvalCommand extends Command {
    public EvalCommand(String name, String help, Category category, boolean usableInDM) {
        super(name, help, category, usableInDM);
    }

    @Override
    public void execute(List<String> arguments, MessageReceivedEvent event) {
        ScriptEngine se = new ScriptEngineManager().getEngineByName("Nashorn");
        se.put("jda", event.getJDA());
        se.put("guild", event.getGuild());
        se.put("channel", event.getChannel());
        se.put("event", event);
        try {
            event.getChannel().sendMessage("Success:\n```java\n" +
                    se.eval(arguments.stream().collect(Collectors.joining("  "))) + "```").queue();
        } catch (Exception e) {
            event.getChannel().sendMessage("Error:\n```\n" + e + "```").queue();
        }
    }
}
