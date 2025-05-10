package org.practice.SpringStarter.config;

import java.util.*;
import org.practice.SpringStarter.models.Account;
import org.practice.SpringStarter.models.Authority;
import org.practice.SpringStarter.models.Post;
import org.practice.SpringStarter.services.AccountService;
import org.practice.SpringStarter.services.AuthorityService;
import org.practice.SpringStarter.services.PostService;
import org.practice.SpringStarter.util.constants.Privillages;
import org.practice.SpringStarter.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

        for (Privillages auth : Privillages.values()) {
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);
        }

        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();
        Account account05 = new Account();

        account01.setEmail("account01demo@gmail.com");
        account01.setPassword("password01");
        account01.setFirstname("user01");
        account01.setLastname("LastName01");

        account02.setEmail("account02demo@gmail.com");
        account02.setPassword("password02");
        account02.setFirstname("user02");
        account02.setLastname("LastName02");

        account03.setEmail("editor@editor.com");
        account03.setPassword("pass987");
        account03.setFirstname("Editor");
        account03.setLastname("lastname");
        account03.setRole(Roles.EDITOR.getRole());

        account04.setEmail("super_editor@editor.com");
        account04.setPassword("pass987");
        account04.setFirstname("Editor");
        account04.setLastname("lastname");
        account04.setRole(Roles.EDITOR.getRole());

        account05.setEmail("admin@admin.com");
        account05.setPassword("pass987");
        account05.setFirstname("Admin");
        account05.setLastname("lastname");
        account05.setRole(Roles.ADMIN.getRole());
        Set<Authority> authorities = new HashSet<>();
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        account04.setAuthorities(authorities);

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);
        accountService.save(account05);

        List<Post> posts = postService.getAll();
        if (posts.size() == 0) {
            Post post01 = new Post();
            post01.setTitle("Post 01");
            post01.setBody(
                    """

                            <img src="https://swarajya.gumlet.io/swarajya/2023-07/effd691a-7af9-4221-9d8a-10b60f2b08c0/Arjun_MK1A_field_trials__1_.jpg?w=610&q=50&compress=true&format=auto" alt="M1 Abrams Tank" style="width:100%;max-width:600px;"/>
                                               Arjun Tank Project Could Suffer Another Devastating Delay, Thanks To Army's Decades-Long Reluctance To Back It
                                   Ujjwal Shrotryia
                                   Indian Army's indigenous Arjun Mk-1A tank.
                                   Indian Army's indigenous Arjun Mk-1A tank.
                                   The Indian Army's reluctance to fully commit to the DRDO-designed Arjun tank has once again thrown a spanner in the works for India's aspirations of having a homegrown main battle tank (MBT).

                                   This hesitation to place substantial orders for the Arjun tank has led to supply chain issues with the production line of the German-made 1400 HP MTU MB 838 Ka-501 V10 diesel-engine, powering the Arjun Mk-1 tank, shutting down.

                                   Its engine manufacturer, MTU, has indicated that it will take another four years to get the production line back up. This means more delays for the already delayed Arjun tank programme — a situation where the blame rests squarely on the shoulders of the army.

                                   Moreover, the stoppage of production of the MTU engine will adversely affect the availability of spare parts for the existing engines in use with the Arjun Mk-1 tanks, exacerbating issues that have plagued the fleet of 124 Arjun tanks for quite some time.

                                   In 2015, it was reported that almost 75 per cent of the fleet was grounded due to a lack of spare parts for the engine, transmission, and issues in the thermal and targeting systems of the tank — all stemming from challenges in importing foreign components.

                                   This starkly highlights the complications arising from ordering such small quantities.

                                   However, the effect of the German engine going out of production will be limited as Heavy Vehicles Factory (HVF) Avadi just two days ago (13 February) demonstrated the ability to maintain and repair the existing 1400 HP engine, entirely in India without any foreign help.

                                   The army has repeatedly given step-motherly treatment to the DRDO-designed tank, favouring the Russian T-90 tank, which the Arjun tank outperformed, fair and square, in comparative trials conducted in Rajasthan in 2010.

                                   Arjun bested the Russian T-90 MBT in every parameter — from gunnery to mobility in cross-country terrain. And, this is setting aside the relaxation given to T-90 in multiple parameters.

                                   Despite Arjun's superior performance, the army continued to obstruct its procurement, pointing out one deficiency after another.

                                   The tank's hefty weight of about 62.5 tonnes was cited as the official reason for not purchasing it in large quantities, arguing that its weight would hinder its ability to navigate roads and bridges near the Pakistan border, on top of asking for 83 tweaks to make it battle-ready, including 15 major ones.

                                   Ironically, the requested modifications led to an increase in weight to more than 68 tonnes, 6 tonnes more than before.

                                   Even after DRDO unveiled the latest model in 2018, the Arjun Mk-1A, with the suggested modifications, the army dragged its feet for another three years before placing an order, citing concerns over high import content, spare parts scarcity, and the lack of suitable ammunition.

                                   It was only in 2021, when the army, under immense pressure from the Ministry of Defence (MoD), relented and ordered 118 Arjun Mk-1A tanks worth Rs 7,523 crore, with deliveries set to begin in 2024.

                                   Now, with the engine production ceasing, more delays are anticipated.

                                   But it's not all doom and gloom.

                                   The DRDO is testing its own in-house designed 1500 HP DATRAN diesel engine. Its first prototype entered testing in 2023, and is anticipated to be lighter and more powerful than the German 1400 HP engine used by the Arjun Mk-1.

                                   Kicked off in 2010, a more powerful and uprated 1800 HP variant of the engine will also power the future ready combat vehicle — the next generation tank weighing around 50 tonnes with induction scheduled in the next decade.
                                                       """);
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Post 02");
            post02.setBody(
                    """
                            <img src="https://images02.military.com/sites/default/files/media/equipment/military-vehicles/m1a2-abrams-main-battle-tank/2014/02/m1a2-abrams-battle-tank-05.jpg" alt="M1 Abrams Tank" style="width:100%;max-width:600px;"/>


                            <h1>Topic: Abrams Tank</h1>
                               Blog Brand: The Buzz
                               Tags: Abrams Tank, M1A1 Abrams Tank, Military, NATO, Russia-Ukraine War, and U.S. Army
                               Ukraine’s M1 Abrams Tanks Could Smash Through Russia’s Defenses
                               October 1, 2023
                               By: Alex Hollings
                               Share
                               Share this link on Facebook
                               Share this page on X (Twitter)
                               Share this link on LinkedIn
                               Email a link to this page
                               An undisclosed number of American M1 Abrams main battle tanks have now arrived in Ukraine, giving the embattled nation a small boost in armored capability amid its ongoing counter-offensive.

                               Ukrainian President Volodymyr Zelensky took to X (previously known as Twitter) to announce their arrival on Monday morning.

                               “Good news from Defense Minister Umerov. Abrams are already in Ukraine and are preparing to reinforce our brigades. I am grateful to our allies for fulfilling the agreements! We are looking for new contracts and expanding the geography of supply,” Zelensky wrote.

                               The United States has promised Ukraine a total of 31 Abrams tanks, amounting to less than 10% of at least 321 total Western tanks headed for – or already in – the European nation. Exactly how many Abrams have arrived is unclear, but Politico reported in June that the first delivery of American tanks would include 10 M1A1 tanks, with the subsequent 21 coming later in the fall. According to the New York Times, anonymous sources within the Pentagon said the tanks arrived in-country on Saturday. The decision to provide Ukraine with refurbished M1A1s, rather than more modern M1A2s, was based on trying to expedite their arrival.

                               However, despite the M1 Abrams’ now-legendary prowess on the battlefield, this small number of tanks is unlikely to play a massive role in Ukraine’s counteroffensive. Instead, the Biden administration’s decision to provide Ukraine with these tanks could arguably be considered a diplomatic move, as it was America’s commitment to send tanks that many credit with Germany’s ultimate decision to allow countries to provide Ukraine with other heavy weapons and platforms, like Leopard 2 main battle tanks.


                               Leopard 2s quickly became the first Western tanks to see action on Ukraine’s behalf in June of this year, with videos of their participation in the counteroffensive surfacing shortly thereafter. In August, it was reported that Ukraine had received at least 71 Leopard IIs already, and had lost only five in combat up to that date.

                               While older than America’s top-of-the-line Abrams, the M1A1s Ukraine is receiving will still present a serious threat to the variety of Russian tanks, some of which are extremely dated. In particular, the Abrams optics will enable better night-fighting capabilities, while the tank’s armor and defensive systems offer a greater degree of survivability. The Abrams, for instance, stows its 120mm main gun ammunition in a sealed compartment on the back of the turret. As a result, a direct hit that manages to penetrate its armor will blow the shells out the back, rather than killing the tank’s occupants. Many Russian tanks like the T-72 and T-80, however, store their ammunition in the turret itself. When hit, this results in a catastrophic failure many have taken to calling the “jack in the box” effect, blowing the turret high into the sky and killing the tank’s crew.

                               “As President Zelensky said earlier today, the first batch of the 31 Abrams tanks have arrived in Ukraine. The mere presence of Abrams tanks serves as a potent deterrent. By having these tanks in their arsenal, the Ukrainian Army can more effectively discourage aggressive actions. Providing the Abrams tanks signifies a tangible commitment to Ukraine’s defense and stability, underscoring U.S. support for its partners facing external pressures. We will continue to focus on what we can do to help Ukraine succeed on the battlefield and protect its people,” a Pentagon statement sent to the Warzone said.

                               However, these Abrams may not see the battlefield any time soon. The initial batch will likely be kept in hiding to avoid being taken out by Russian missiles until the rest arrive and they can be used in larger numbers. The last thing Ukraine wants to happen is to lose these tanks before they even enter the fight.


                                           """);
            post02.setAccount(account02);
            postService.save(post02);
        }
    }
}
