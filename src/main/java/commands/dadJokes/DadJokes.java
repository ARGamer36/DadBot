package commands.dadJokes;

import Information.FileAccessor;
import Information.ServerStorage;
import Main.MainCommands;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileNotFoundException;
import java.util.*;

public class DadJokes extends ListenerAdapter {
    protected static final int SEC = 1000;
    protected static final int MIN = SEC * 60;
    protected static final int HOUR = MIN * 60;
    private String jokeChannel;
    private List<String> dadJokesList;
    private boolean jokesRemain;
    private boolean jokesOn;
    private Timer timer;
    public DadJokes() {
        jokeChannel = null;
        resetDadJokesList();
        jokesRemain = true;
        jokesOn = true;
        timer = new Timer();
    }
    public String getJokeChannelId(Guild guild) {
        String filepath = ServerStorage.getInfoFilePath(guild, "jokeChannel.txt");
        try {
            jokeChannel = FileAccessor.getFileLine(filepath);
        } catch (FileNotFoundException e) {
            jokeChannel = guild.getSystemChannel().getId();
            try {
                FileAccessor.rewriteFile(filepath, jokeChannel);
            } catch (FileNotFoundException e2) {}
        }
        return jokeChannel;
    }
    public void setJokeChannel(Guild guild, String channelId) {
        String filepath = ServerStorage.getInfoFilePath(guild, "jokeChannel.txt");
        jokeChannel = channelId;
        try {
            FileAccessor.rewriteFile(filepath, jokeChannel);
        } catch (FileNotFoundException e2) {}
    }
    public void sendDadJoke(Guild guild) {
        String channelId = getJokeChannelId(guild);
        TextChannel channel = guild.getTextChannelById(channelId);
        sendDadJoke(guild, channel);
    }
    public void sendDadJoke(GenericGuildEvent event, TextChannel channel) {
        if (dadJokesList.size() != 0) {
            Random random = new Random();
            int jokeNum = random.nextInt(dadJokesList.size());
            String joke = dadJokesList.get(jokeNum);
            channel.sendMessage(joke).queue();
        } else if (jokesRemain) {
            MainCommands.sendServerMessage(event.getGuild(), "Dad is out of jokes");
            jokesRemain = false;
        }
    }
    public void sendDadJoke(Guild guild, Channel channel) {
        Random random = new Random();
        int jokeNum = random.nextInt(dadJokesList.size());
        String joke = dadJokesList.get(jokeNum);
        guild.getTextChannelById(channel.getId()).sendMessage(joke).queue();
    }
    public void start(GenericGuildEvent event) {
        start(event, 24*HOUR);
    }
    public void start(GenericGuildEvent event, int delay) {
        stop();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (jokesOn) {
                    sendDadJoke(event.getGuild());
                }
            }
        }, 0, delay);
    }
    public void start(Guild guild, int delay) {
        stop();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (jokesOn) {
                    sendDadJoke(guild);
                }
            }
        }, 0, delay);
    }
    public void stop() {
        timer.cancel();
    }
    protected void resetDadJokesList() {
        dadJokesList = new ArrayList<>();
        dadJokesList.add("I'm afraid for the calendar. Its days are numbered.");
        dadJokesList.add("My wife said I should do lunges to stay in shape. That would be a big step forward.");
        dadJokesList.add("Why do fathers take an extra pair of socks when they go golfing ||In case they get a hole in one!||");
        dadJokesList.add("Singing in the shower is fun until you get soap in your mouth. Then it's a soap opera.");
        dadJokesList.add("What do a tick and the Eiffel Tower have in common? ||They're both Paris sites.||");
        dadJokesList.add("What do you call a fish wearing a bowtie? ||So*fish*ticated.||");
        dadJokesList.add("How do you follow Will Smith in the snow? ||You follow the fresh prints.||");
        dadJokesList.add("If April showers bring May flowers, what do May flowers bring? ||Pilgrims.||");
        dadJokesList.add("I thought the dryer was shrinking my clothes. Turns out it was the refrigerator all along.");
        dadJokesList.add("What do you call a factory that makes okay products? ||A satisfactory.||");
        dadJokesList.add("Dear Math, grow up and solve your own problems.");
        dadJokesList.add("What did the janitor say when he jumped out of the closet? ||Supplies!||");
        dadJokesList.add("Have you heard about the chocolate record player? ||It sounds pretty sweet.||");
        dadJokesList.add("What did the ocean say to the beach? ||Nothing, it just waved.||");
        dadJokesList.add("Why do seagulls fly over the ocean? ||Because if they flew over the bay, we'd call them bagels.||");
        dadJokesList.add("I only know 25 letters of the alphabet. I don't know y.");
        dadJokesList.add("How does the moon cut his hair? ||Eclipse it.||");
        dadJokesList.add("What did one wall say to the other? ||I'll meet you at the corner.||");
        dadJokesList.add("What did the zero say to the eight? ||That belt looks good on you.||");
        dadJokesList.add("A skeleton walks into a bar and says, 'Hey, bartender. I'll have one beer and a mop.'");
        dadJokesList.add("Where do fruits go on vacation? ||Pear-is!||");
        dadJokesList.add("I asked my dog what's two minus two. He said nothing.");
        dadJokesList.add("What did Baby Corn say to Mama Corn? ||Where's Pop Corn?||");
        dadJokesList.add("What's the best thing about Switzerland? ||I don't know, but the flag is a big plus.||");
        dadJokesList.add("What does a sprinter eat before a race? ||Nothing, they fast!||");
        dadJokesList.add("Where do you learn to make a banana split? ||Sundae school.||");
        dadJokesList.add("What has more letters than the alphabet? ||The post office!||");
        dadJokesList.add("Dad, did you get a haircut? ||No, I got them all cut!||");
        dadJokesList.add("What do you call a poor Santa Claus? ||St. Nickel-less.||");
        dadJokesList.add("I got carded at a liquor store, and my Blockbuster card accidentally fell out. The cashier said never mind.");
        dadJokesList.add("Where do boats go when they're sick? ||To the boat doc.||");
        dadJokesList.add("I don't trust those trees. They seem kind of shady.");
        dadJokesList.add("My wife is really mad at the fact that I have no sense of direction. So I packed up my stuff and right!");
        dadJokesList.add("How do you get a squirrel to like you? ||Act like a nut.||");
        dadJokesList.add("Why don't eggs tell jokes? ||They'd crack each other up.||");
        dadJokesList.add("I don't trust stairs. They're always up to something.");
        dadJokesList.add("What do you call someone with no body and no nose? ||Nobody knows.||");
        dadJokesList.add("Did you hear the rumor about butter? ||Well, I'm not going to spread it!||");
        dadJokesList.add("Why couldn't the bicycle stand up by itself? ||It was two tired.||");
        dadJokesList.add("What did one hat say to the other? ||Stay here! I'm going on ahead.||");
        dadJokesList.add("Why did Billy get fired from the banana factory? ||He kept throwing away the bent ones.||");
        dadJokesList.add("Dad, can you put my shoes on? ||No, I don't think they'll fit me.||");
        dadJokesList.add("Why can't a nose be 12 inches long? ||Because then it would be a foot.||");
        dadJokesList.add("What does a lemon say when it answers the phone? ||Yellow!||");
        dadJokesList.add("This graveyard looks overcrowded. People must be dying to get in.");
        dadJokesList.add("What kind of car does an egg drive? ||A yolkswagen.||");
        dadJokesList.add("Dad, can you put the cat out? ||I didn't know it was on fire.||");
        dadJokesList.add("How do you make 7 even? ||Take away the s.||");
        dadJokesList.add("How does a taco say grace? ||Lettuce pray.||");
        dadJokesList.add("What time did the man go to the dentist? ||Tooth hurt-y.||");
        dadJokesList.add("Why didn't the skeleton climb the mountain? ||It didn't have the guts.||");
        dadJokesList.add("What do you call it when a snowman throws a tantrum? ||A meltdown.||");
        dadJokesList.add("How many tickles does it take to make an octopus laugh? ||Ten tickles.||");
        dadJokesList.add("I have a joke about chemistry, but I don't think it will get a reaction.");
        dadJokesList.add("What concert costs just 45 cents? ||50 Cent featuring Nickelback!||");
        dadJokesList.add("What does a bee use to brush its hair? ||A honeycomb!||");
        dadJokesList.add("How do you make a tissue dance? ||You put a little boogie in it.||");
        dadJokesList.add("Why did the math book look so sad? ||Because of all of its problems!||");
        dadJokesList.add("What do you call cheese that isn't yours? ||Nacho cheese.||");
        dadJokesList.add("My dad told me a joke about boxing. I guess I missed the punch line.");
        dadJokesList.add("What kind of shoes do ninjas wear? ||Sneakers!||");
        dadJokesList.add("How does a penguin build its house? ||Igloos it together.||");
        dadJokesList.add("How did Harry Potter get down the hill? ||Walking. JK! Rowling.||");
        dadJokesList.add("I used to be addicted to soap, but I'm clean now.");
        dadJokesList.add("A guy walks into a bar...and he was disqualified from the limbo contest.");
        dadJokesList.add("You think swimming with sharks is expensive? Swimming with sharks cost me an arm and a leg.");
        dadJokesList.add("When two vegans get in an argument, is it still called a beef?");
        dadJokesList.add("I ordered a chicken and an egg from Amazon. I'll let you know...");
        dadJokesList.add("Do you wanna box for your leftovers? ||No, but I'll wrestle you for them.||");
        dadJokesList.add("That car looks nice but the muffler seems exhausted.");
        dadJokesList.add("Shout out to my fingers. I can count on all of them.");
        dadJokesList.add("If a child refuses to nap, are they guilty of resisting a rest?");
        dadJokesList.add("What country's capital is growing the fastest? ||Ireland. Every day it's Dublin.");
        dadJokesList.add("I once had a dream I was floating in an ocean of orange soda. It was more of a fanta sea.");
        dadJokesList.add("Did you know corduroy pillows are in style? They're making headlines.");
        dadJokesList.add("Did you hear about the kidnapping at school? It's okay, he woke up.");
        dadJokesList.add("A cheeseburger walks into a bar. The bartender says, 'Sorry, we don't serve food here.'");
        dadJokesList.add("I once got fired from a canned juice company. Apparently I couldn't concentrate.");
        dadJokesList.add("I used to play piano by ear. Now I use my hands.");
        dadJokesList.add("Have you ever tried to catch a fog? I tried yesterday but I mist.");
        dadJokesList.add("I'm on a seafood diet. I see food and I eat it.");
        dadJokesList.add("Why did the scarecrow win an award? Because he was outstanding in his field.");
        dadJokesList.add("I made a pencil with two erasers. It was pointless.");
        dadJokesList.add("How do you make a Kleenex dance? ||Put a little boogie in it!||");
        dadJokesList.add("I'm reading a book about anti-gravity. It's impossible to put down!");
        dadJokesList.add("Did you hear about the guy who invented the knock-knock joke? He won the 'no-bell' prize.");
        dadJokesList.add("I've got a great joke about construction, but I'm still working on it.");
        dadJokesList.add("I used to hate facial hair...but then it grew on me.");
        dadJokesList.add("I decided to sell my vacuum cleaner—it was just gathering dust!");
        dadJokesList.add("I had a neck brace fitted years ago and I've never looked back since.");
        dadJokesList.add("You know, people say they pick their nose, but I feel like I was just born with mine.");
        dadJokesList.add("What's brown and sticky? ||A stick.||");
        dadJokesList.add("Why can't you hear a psychiatrist using the bathroom? Because the 'P' is silent.");
        dadJokesList.add("What do you call an elephant that doesn't matter? An irrelephant.");
        dadJokesList.add("What do you get from a pampered cow? Spoiled milk.");
        dadJokesList.add("I like telling Dad jokes. Sometimes he laughs!");
        dadJokesList.add("What's the best smelling insect? ||A deodor-ant.||");
        dadJokesList.add("I used to be a personal trainer. Then I gave my too weak notice.");
        dadJokesList.add("Did I tell you the time I fell in love during a backflip? I was heels over head!");
        dadJokesList.add("If a child refuses to sleep during nap time, are they guilty of resisting a rest?");
        dadJokesList.add("I ordered a chicken and an egg online. I’ll let you know.");
        dadJokesList.add("It takes guts to be an organ donor.");
        dadJokesList.add("If you see a crime at an Apple Store, does that make you an iWitness?");
        dadJokesList.add("I'm so good at sleeping, I can do it with my eyes closed!");
        dadJokesList.add("I was going to tell a time-traveling joke, but you guys didn't like it.");
        // puns
        dadJokesList.add("What do you call a fake noodle? ||An impasta.||");
        dadJokesList.add("What do you call a belt made of watches? ||A waist of time.||");
        dadJokesList.add("What happens when a strawberry gets run over crossing the street? ||Traffic jam.||");
        dadJokesList.add("What do you call two monkeys that share an Amazon account? ||Prime mates.||");
        dadJokesList.add("What do you call a pony with a sore throat? ||A little hoarse.||");
        dadJokesList.add("Where do math teachers go on vacation? ||Times Square.||");
        dadJokesList.add("Whenever I try to eat healthy, a chocolate bar looks at me and Snickers.");
        dadJokesList.add("What does garlic do when it gets hot? ||It takes its cloves off.||");
        dadJokesList.add("What's a robot's favorite snack? ||Computer chips.||");
        dadJokesList.add("How much does it cost Santa to park his sleigh? ||Nothing, it's on the house.");
        dadJokesList.add("Mountains aren't just funny. They're hill areas.");
        dadJokesList.add("What do clouds wear? ||Thunderwear.||");
        dadJokesList.add("Why are piggy banks so wise? ||They're filled with common cents.||");
        dadJokesList.add("Why is Peter Pan always flying? ||He neverlands.||");
        dadJokesList.add("How do you get a good price on a sled? ||You have toboggan.||");
        dadJokesList.add("How can you tell if a tree is a dogwood tree? ||By its bark.||");
        dadJokesList.add("I used to hate facial hair, but then it grew on me.");
        dadJokesList.add("It's inappropriate to make a 'dad joke' if you're not a dad. It's a faux pa.");
        dadJokesList.add("What do you call a hot dog on wheels? ||Fast food!||");
        dadJokesList.add("Where do young trees go to learn? ||Elementree school.||");
        dadJokesList.add("Did you hear about the circus fire? ||It was in tents.||");
        dadJokesList.add("Can February March? No, but April May!");
        dadJokesList.add("How do lawyers say goodbye? We'll be suing ya!");
        dadJokesList.add("Wanna hear a joke about paper? Never mind—it's tearable.");
        dadJokesList.add("What's the best way to watch a fly fishing tournament? Live stream.");
        dadJokesList.add("Spring is here! I got so excited I wet my plants.");
        dadJokesList.add("I could tell a joke about pizza, but it's a little cheesy.");
        dadJokesList.add("Don't trust atoms. They make up everything!");
        dadJokesList.add("When does a joke become a dad joke? ||When it becomes apparent.||");
        dadJokesList.add("I wouldn't buy anything with velcro. It's a total rip-off.");
        dadJokesList.add("What’s an astronaut’s favorite part of a computer? ||The space bar.||");
        dadJokesList.add("I don't play soccer because I enjoy the sport. I'm just doing it for kicks!");
        dadJokesList.add("Why are elevator jokes so classic and good? ||They work on many levels.||");
        dadJokesList.add("Why do bees have sticky hair? ||Because they use a honeycomb.||");
        dadJokesList.add("What do you call a fake noodle? ||An impasta.||");
        dadJokesList.add("Which state has the most streets? ||Rhode Island.||");
        dadJokesList.add("What did the coffee report to the police? ||A mugging.||");
        dadJokesList.add("What did the fish say when he hit the wall? ||Dam.||");
        dadJokesList.add("Is this pool safe for diving? ||It deep ends.||");
        dadJokesList.add("If you see a crime happen at the Apple store, what does it make you? ||An iWitness.||");
        // https://www.countryliving.com/life/a27452412/best-dad-jokes/
        // Stopped at Best One-Liner Dad Jokes
    }
}
