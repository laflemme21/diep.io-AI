import pyautogui
import keyboard
from functions import *
#TODO
#implment target purple, ennemies, ennemies bullets
#implement reactions to those


def main():
    square = Target((247, 232, 126),97)
    triangle = Target((226, 126, 123),92)
    pentagon = Target((129, 142, 244),103)
    allTargets = (pentagon, triangle, square)
    screenshot = Screenshot(415, 160, 2500, 1520)
    mouse=Mouse()
    while not keyboard.is_pressed('q'):
        screenshot.renewScreenshot()
        for target in allTargets:
            target.findTarget(screenshot)
            while not keyboard.is_pressed('q') and target.x != None:
                mouse.moveTo((target.x,target.y),screenshot)
                target.followTarget(screenshot,15,100)

main()