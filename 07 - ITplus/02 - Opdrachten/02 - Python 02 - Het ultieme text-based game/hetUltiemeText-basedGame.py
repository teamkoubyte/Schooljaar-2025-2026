import colorama as color
import random

color.init(autoreset=True)

# Game variabelen
spelkarakter = "voetballer"
level = 1
max_level = 10
doelpunten = 0


def toon_intro():
    print("Je bent een " + spelkarakter + " die zoveel mogelijk wil scoren.")
    print("Je moet in dit spel zo veel mogelijk scoren. Je begint bij level 1 en eindigt in level 10.")
    print("Om één level hoger te geraken moet je een doelpunt maken bij de tegenstander.")


def vraag_speler_info():
    naam = input("Dag voetballer. Wat is je naam? ")
    leeftijd = input("Wat is je leeftijd? ")
    lengte = input("Wat is je lengte? ")
    gewicht = input("Wat is je gewicht? ")
    rugnummer = input("Wat is je rugnummer? ")
    favorieteBeen = input("Wat is je favorite been? ")
    team = input("Wat is je team? ")

    return {
        'naam': naam,
        'leeftijd': leeftijd,
        'lengte': lengte,
        'gewicht': gewicht,
        'rugnummer': rugnummer,
        'favorieteBeen': favorieteBeen,
        'team': team
    }


def toon_speler_info(speler):
    print(f"\nHallo voetballer {speler['naam']}!")
    print(f"Je bent een {speler['leeftijd']} jarige voetballer.")
    print(f"Je speelt tegen de beste teams van de wereld, je begint met het spelen tegen Frankrijk.")
    print("Je mag deze match absoluut niet verliezen, anders begin je opnieuw.\n")


def toon_stats(level, doelpunten):
    print(f"\n{color.Fore.CYAN}--- STATS ---")
    print(f"Level: {level}/{max_level}")
    print(f"Doelpunten: {doelpunten}")
    print("-------------\n")


def speel_match():
    teams = ["Frankrijk", "Brazilië", "Argentinië", "Duitsland", "Spanje",
             "Nederland", "Italië", "Portugal", "Engeland", "België"]

    tegenstander = teams[level - 1] if level <= len(teams) else "Wereldkampioen"

    print(f"{color.Fore.YELLOW}--- MATCH TEGEN {tegenstander.upper()} ---")
    print(f"Level {level}: Je speelt tegen {tegenstander}!")

    print("\nKies je actie:")
    print("1. Probeer te scoren")
    print("2. Speel defensief")
    print("3. Maak een pass")

    keuze = input("Wat doe je? (1/2/3): ")

    if keuze == "1":
        if random.random() > 0.4:
            print(f"{color.Fore.GREEN}DOELPUNT! Je scoort tegen {tegenstander}!")
            return True
        else:
            print(f"{color.Fore.RED}Gemist! De keeper van {tegenstander} redt je schot.")
            return False
    elif keuze == "2":
        if random.random() > 0.6:
            print(f"{color.Fore.GREEN}Goede verdediging! Je krijgt een kans om te scoren!")
            return True
        else:
            print(f"{color.Fore.RED}Je speelt te defensief en krijgt geen kansen.")
            return False
    elif keuze == "3":
        if random.random() > 0.5:
            print(f"{color.Fore.GREEN}Perfecte pass! Je teamgenoot scoort!")
            return True
        else:
            print(f"{color.Fore.RED}De pass wordt onderschept door {tegenstander}.")
            return False
    else:
        print(f"{color.Fore.RED}Ongeldige keuze! Je verliest de bal.")
        return False


def game_loop(speler):
    global level, doelpunten

    while level <= max_level:
        toon_stats(level, doelpunten)

        if speel_match():
            doelpunten += 1
            print(f"{color.Fore.GREEN}Je gaat naar level {level + 1}!")
            level += 1

            if level > max_level:
                print(f"{color.Fore.MAGENTA}🎉 GEFELICITEERD {speler['naam']}! Je hebt alle levels gehaald!")
                print(f"Totaal doelpunten: {doelpunten}")
                break
        else:
            print(f"{color.Fore.RED}Je hebt verloren tegen level {level}!")
            print("Probeer het opnieuw!")

        doorgaan = input(f"{color.Fore.CYAN}Wil je doorgaan? (j/n): ").lower()
        if doorgaan != 'j' and doorgaan != 'ja':
            print("Bedankt voor het spelen!")
            break


def main():
    toon_intro()
    speler = vraag_speler_info()
    toon_speler_info(speler)

    # Start de game loop
    game_loop(speler)

if __name__ == "__main__":
    main()