import keyboard
from random import randint
from functions import Player, MiniMap
import pyautogui
from time import sleep
#TODO:
#detect player with minimap background blue and red
#move to a destination given by bthe master script

def main():
    # x: 0->253  y: 0->253
    xMin = 2592
    yMin = 1416
    xMax = 2845
    yMax = 1669
    minimap = MiniMap(xMin, yMin, xMax, yMax)
    player = Player(4)
    x = randint(
        10, xMax-xMin-10)
    y = randint(10, yMax-yMin-10)
    pyautogui.moveTo(x,y)
    while not keyboard.is_pressed('q'):
        player.detectPlayerColor(minimap)
        player.findTarget(minimap)
        while not keyboard.is_pressed('q') and player.x != None:
            print('1')
            player.followTarget(minimap,4, 50)
            if player.x != None:
                print('2')
                player.movePlayer(x, y)
            else:

                keyboard.release('a')
                sleep(0.2)
                keyboard.release('d')
                sleep(0.2)
                keyboard.release('s')
                sleep(0.2)
                keyboard.release('w')
                x = randint(
                    10, xMax-xMin-10)
                y = randint(10, yMax-yMin-10)

        minimap.renewScreenshot()


main()
