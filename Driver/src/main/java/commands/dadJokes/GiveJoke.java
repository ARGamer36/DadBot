package commands.dadJokes;

import commands.abstracts.PrefixCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class GiveJoke extends PrefixCommand {
    public GiveJoke() {
        name = "dadJoke";
        description = "Give pop a chance to tell you a joke";
    }

    @Override
    public void action(MessageReceivedEvent event) {
        DadJokes dadJokes = new DadJokes();
        dadJokes.sendDadJoke(event.getGuild(), event.getChannel());
    }
}
