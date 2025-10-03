import random

aantalPuntenSpeler = 0
aantalPuntenComputer = 0
aantalRondes = 1

while aantalPuntenSpeler < 5 and aantalPuntenComputer < 5:
    print("--- RONDE " + str(aantalRondes) + " ---")
    
    jouwKeuze = input("Denk je dat de computer kop of munt zal gooien? ")
    
    computerKeuze = random.choice(["kop", "munt"])
    
    print("De computer gooide " + computerKeuze + ".")
    
    if jouwKeuze == computerKeuze:
        aantalPuntenSpeler += 1
    else:
        aantalPuntenComputer += 1
    
    print("Jij hebt " + str(aantalPuntenSpeler) + " punten.")
    print("De computer heeft " + str(aantalPuntenComputer) + " punten.")
    print("")
    
    aantalRondes += 1

print("----------------")
print("EINDE SPEL")

uitslag = input("Wil je weten wie gewonnen is? ")

if uitslag == "ja":
    if aantalPuntenSpeler == 5:
        print("Jij bent gewonnen :)")
    else:
        print ("")
        print("De computer is gewonnen :(")

    

  
    

