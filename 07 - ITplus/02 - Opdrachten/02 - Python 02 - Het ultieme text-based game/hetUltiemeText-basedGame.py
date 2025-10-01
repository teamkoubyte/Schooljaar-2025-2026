import random
import colorama as kleur
import os

kleur.init(autoreset=True)

naam = ""
rol = ""
favorieteWapen = ""
dataPunten = 0
levens = 3
score = 0
aantalZetten = 0

kaart = [
    ["#", "#", "#", "#", "#", "#", "#", "#"],
    ["#", ".", "$", ".", "!", ".", "$", "#"],
    ["#", "@", ".", "#", "#", ".", ".", "#"],
    ["#", "$", ".", ".", ".", "!", "$", "#"],
    ["#", ".", "#", "$", ".", ".", ".", "#"],
    ["#", "!", ".", ".", "#", ".", "x", "#"],
    ["#", "#", "#", "#", "#", "#", "#", "#"]
]

print(kleur.Fore.GREEN + """   ___  _  _  ____  ____  ____  ___  ____  ___  __  __  ____  ____  ____  _  _    ___  ____  ____  __   
 / __)( \/ )(  _ \( ___)(  _ \/ __)( ___)/ __)(  )(  )(  _ \(_  _)(_  _)( \/ )  / __)(  _ \( ___)(  )  
( (__  \  /  ) _ < )__)  )   /\__ \ )__)( (__  )(__)(  )   / _)(_   )(   \  /   \__ \ )___/ )__)  )(__ 
 \___) (__) (____/(____)(_)\_)(___/(____)\___)(______)(_)\_)(____) (__)  (__)   (___/(__)  (____)(____)        
""")
print(kleur.Fore.YELLOW + "Je bent een hacker die een beveiligd netwerk moet infiltreren!")
print(kleur.Fore.YELLOW + "Verzamel alle data ($) en bereik de uitgang (x)")
print(kleur.Fore.RED + "Pas op voor vallen (!)")
print("")
print("Toetsenbord bediening:")
print("z = omhoog bewegen")
print("s = omlaag bewegen") 
print("q = links bewegen")
print("d = rechts bewegen")
print("x = spel stoppen")
print("")

print(kleur.Fore.CYAN + "Maak eerst je hacker profiel:")
print("")

naam = input("Wat is je hacker naam? ")
if naam == "":
    naam = "Anonymous"

print("")
print("Kies je specialiteit:")
print("1. Netwerk Hacker")
print("2. Malware Expert") 
print("3. Social Engineer")
rolKeuze = input("Kies 1, 2 of 3: ")

if rolKeuze == "1":
    rol = "Netwerk Hacker"
    favorieteWapen = "Nmap scanner"
elif rolKeuze == "2":
    rol = "Malware Expert"
    favorieteWapen = "Trojan virus"
elif rolKeuze == "3":
    rol = "Social Engineer"
    favorieteWapen = "Phishing email"
else:
    rol = "Beginner Hacker"
    favorieteWapen = "Keyboard"

print("")
print(kleur.Fore.MAGENTA + "Profiel aangemaakt!")
print("Naam: " + naam)
print("Rol: " + rol)
print("Favoriete wapen: " + favorieteWapen)

achtergrondLijst = [
    "Je hebt geleerd hacken op school.",
    "Je bent een ex-militair cyber specialist.", 
    "Je hebt jezelf hacken geleerd op YouTube.",
    "Je werkte vroeger voor een beveiligingsbedrijf.",
]
achtergrond = random.choice(achtergrondLijst)
print("Achtergrond: " + achtergrond)
print("")

input("Druk ENTER om het spel te starten...")

gameOver = False
gewonnen = False

while gameOver == False:
    os.system('cls' if os.name == 'nt' else 'clear')
    
    print(kleur.Fore.CYAN + "Hacker: " + naam + " (" + rol + ")")
    print("Data verzameld: " + str(dataPunten) + "/5")
    print("Levens: " + str(levens))
    print("Score: " + str(score))
    print("Zetten: " + str(aantalZetten))
    print("")
    
    rijTeller = 0
    while rijTeller < len(kaart):
        huidigeRij = kaart[rijTeller]
        gekleurdeRij = []
        
        kolomTeller = 0
        while kolomTeller < len(huidigeRij):
            huidigTeken = huidigeRij[kolomTeller]
            
            if huidigTeken == '#':
                gekleurdeRij.append(kleur.Fore.WHITE + '#')
            elif huidigTeken == '$':
                gekleurdeRij.append(kleur.Fore.GREEN + '$')
            elif huidigTeken == 'x':
                gekleurdeRij.append(kleur.Fore.YELLOW + 'x')
            elif huidigTeken == '@':
                gekleurdeRij.append(kleur.Fore.CYAN + '@')
            elif huidigTeken == '!':
                gekleurdeRij.append(kleur.Fore.RED + '!')
            else:
                gekleurdeRij.append('.')
            
            kolomTeller = kolomTeller + 1
        
        print("".join(gekleurdeRij))
        rijTeller = rijTeller + 1
    
    print("")
    
    gebeurtenisKans = random.randint(1, 5)
    if gebeurtenisKans == 1:
        gebeurtenisLijst = [
            "Je ontdekt een zwakke firewall!",
            "Een beveiligingscamera heeft je bijna gespot!",
            "Je " + favorieteWapen + " werkt perfect!",
            "Het netwerk wordt langzamer...",
            "Je hoort voetstappen in de gang!"
        ]
        randomGebeurtenis = random.choice(gebeurtenisLijst)
        print(kleur.Fore.YELLOW + randomGebeurtenis)
        print("")
    
    beweging = input(kleur.Fore.CYAN + "Wat wil je doen? (z/q/s/d/x): ").lower()
    
    if beweging == 'x':
        print("")
        print(kleur.Fore.RED + "Je hebt het spel verlaten!")
        print("Totale score: " + str(score))
        gameOver = True
        break
    
    spelX = 0
    spelY = 0
    
    zoekY = 0
    while zoekY < len(kaart):
        zoekX = 0
        while zoekX < len(kaart[zoekY]):
            if kaart[zoekY][zoekX] == '@':
                spelX = zoekX
                spelY = zoekY
            zoekX = zoekX + 1
        zoekY = zoekY + 1
    
    nieuweX = spelX
    nieuweY = spelY
    
    if beweging == 'z':
        nieuweY = nieuweY - 1
    elif beweging == 's':
        nieuweY = nieuweY + 1
    elif beweging == 'q':
        nieuweX = nieuweX - 1
    elif beweging == 'd':
        nieuweX = nieuweX + 1
    else:
        print(kleur.Fore.RED + "Ongeldige keuze!")
        input("Druk ENTER om verder te gaan...")
        continue
    
    aantalZetten = aantalZetten + 1
    
    if kaart[nieuweY][nieuweX] == '#':
        print(kleur.Fore.RED + "OEPS! Je botst tegen een firewall!")
        print("Probeer een andere richting.")
        input("Druk ENTER om verder te gaan...")
    else:
        doelTeken = kaart[nieuweY][nieuweX]
        
        if doelTeken == '$':
            dataPunten = dataPunten + 1
            score = score + 100
            print(kleur.Fore.GREEN + "Je hebt een datapakket gehackt!")
            print("+" + str(100) + " punten!")
            
        elif doelTeken == '!':
            levens = levens - 1
            score = score - 50
            print(kleur.Fore.RED + "Je bent in een val gelopen!")
            print("Je verliest een leven!")
            print("-" + str(50) + " punten!")
            
            if levens <= 0:
                print(kleur.Fore.RED + "Je hebt geen levens meer!")
                gameOver = True
                
        elif doelTeken == 'x':
            if dataPunten >= 5:
                score = score + 500
                print(kleur.Fore.MAGENTA + "Je bent ontsnapt!")
                print("Je hebt alle data verzameld en bent veilig ontsnapt!")
                print("+" + str(500) + " bonus punten!")
                gewonnen = True
                gameOver = True
            else:
                print(kleur.Fore.RED + "Je hebt nog niet alle data!")
                print("Je hebt nog " + str(5 - dataPunten) + " datapakketten nodig!")
        
        if gameOver == False:
            kaart[spelY][spelX] = '.'
            kaart[nieuweY][nieuweX] = '@'
        
        input("Druk ENTER om verder te gaan...")

print("")
print(kleur.Fore.CYAN + "========================================")
print(kleur.Fore.CYAN + "           SPEL STATISTIEKEN")
print(kleur.Fore.CYAN + "========================================")
print("Speler: " + naam)
print("Rol: " + rol) 
print("Data verzameld: " + str(dataPunten) + "/5")
print("Totaal aantal zetten: " + str(aantalZetten))
print("Eindresultaat: " + str(score) + " punten")

if gewonnen == True:
    print(kleur.Fore.GREEN + "STATUS: MISSIE GESLAAGD!")
else:
    print(kleur.Fore.RED + "STATUS: MISSIE GEFAALD!")

print("")
print("Bedankt voor het spelen!")