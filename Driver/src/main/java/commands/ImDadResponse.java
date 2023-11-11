package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class ImDadResponse extends ListenerAdapter {
    private final String[] STARTERS = new String[]{"i'm", "im", "i am"};
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getMember().getUser().isBot()) {
            String message = event.getMessage().getContentRaw();
            String lcMes = message.toLowerCase();
            int index = 0;
            String childName = "kid";
            boolean locatedStarter = false;
            for (String starter : STARTERS) {
                starter += " ";
                if (lcMes.startsWith(starter)) {
                    index = starter.length();
                    locatedStarter = true;
                    break;
                }
                starter = " " + starter;
                if (lcMes.contains(starter)) {
                    index = lcMes.indexOf(starter) + starter.length();
                    locatedStarter = true;
                    break;
                }
            }
            if (locatedStarter) {
                childName = message.substring(index);
                sendImDadMessage(event, childName);
            }
        }
    }
    private void sendImDadMessage(MessageReceivedEvent event, String childName) {
        Random random = new Random();
        int chance = random.nextInt(10);
        if (chance >= 5) {
            event.getMessage().reply("Hi " + childName + ", I'm Dad!").queue();
        }
    }
}
