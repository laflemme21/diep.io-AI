from keyboard import is_pressed
from functions import *
#TODO
#implment target purple, ennemies, ennemies bullets
#implement reactions to those


def main():
    #square = Target((247, 232, 126),97)
    triangle = Target((226, 126, 123),90)
    pentagon = Target((129, 142, 244),100)
    allTargets = (pentagon, triangle)
    screenshot = Screenshot(415, 160, 2500, 1520)
    mouse=Mouse()
    while not is_pressed('q'):
        screenshot.renewScreenshot()
        for target in allTargets:
            target.findTarget(screenshot)
            while not is_pressed('q') and target.x != None:
                mouse.moveTo((target.x,target.y),screenshot)
                target.followTarget(screenshot,30,80)

main()