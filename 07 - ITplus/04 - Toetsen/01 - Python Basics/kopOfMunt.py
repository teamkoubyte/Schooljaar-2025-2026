import random

keuze = ["kop", "munt"]
randomKeuze = random.choice(keuze)
aantalPuntenSpeler = 0
aantalPuntenComputer = 0
ronde = 1

print ("--- RONDE " + str(ronde) + " ---")

jouwKeuze = input("Denk je dat de computer kop of munt zal gooien?")

print ("De computer gooide " + randomKeuze)

if jouwKeuze == randomKeuze:
  aantalPuntenSpeler += 1
else:
  aantalPuntenComputer += 1

print ("Jij hebt " + str(aantalPuntenSpeler) + " punten.")
print ("De computer heeft " + str(aantalPuntenComputer) + " punten.")

if aantalPuntenSpeler ^ aantalPuntenSpeler == 5 :
  print ("----------------")
  print ("EINDE SPEL")

    

  
    

