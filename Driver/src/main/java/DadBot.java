import main.BotDriver;
import commands.DadMain;
import commands.ImDadResponse;
import commands.dadJokes.DadJokes;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class DadBot extends BotDriver {
    @Override
    protected void setup() {
        VERSION = "1.0";
        activity = Activity.watching("You grow up so fast");
        TOKEN = "DAD_BOT";
    }
    public DadBot() throws LoginException {
        super();
        shardManager.addEventListener(new DadMain(VERSION), new ImDadResponse(), new DadJokes());
    }
    public static void main(String[] args) {
        try {
            DadBot bot = new DadBot();
        } catch (LoginException e) {
            System.out.println("ERROR: provided bot token is invalid");
        }
    }
}
