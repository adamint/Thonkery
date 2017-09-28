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

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;

import org.json.JSONObject;
import tv.circuitrcay.thonkery.utils.HTTP;

import java.io.IOException;

public class CatCommand extends Command {

    public CatCommand() {
        this.name = "cat";
        this.help = "random cat";
        this.guildOnly = false;
        this.category = new Category("Miscellaneous");
    }

    @Override
    protected void execute(CommandEvent event) {
        try {
            HTTP req = new HTTP();
            String res = req.get("https://random.cat/meow");
            JSONObject obj = new JSONObject(res);
            String cat = obj.getString("file");
            event.reply(cat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
