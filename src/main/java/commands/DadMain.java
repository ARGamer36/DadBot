package commands;

import Main.MainCommands;
import commands.dadJokes.DadJokes;
import commands.dadJokes.GiveJoke;
import commands.dadJokes.StartJokes;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;

public class DadMain extends MainCommands {
    public static DadJokes dadJokes;
    public DadMain(String version) {
        super(version);
        prefixCommands.add(new GiveJoke());
        slashCommands.add(new StartJokes());
        dadJokes = new DadJokes();
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        super.onGuildReady(event);
        dadJokes.start(event);
    }
}
