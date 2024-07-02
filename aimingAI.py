import pyautogui
import keyboard


def followTarget(x2Min, x2Max, y2Min, y2Max, target):
    screenshot = pyautogui.screenshot(
        region=(x2Min, y2Min, x2Max, y2Max))
    for x2 in range(x2Min, x2Max, 24):
        for y2 in range(y2Min, y2Max, 24):
            if screenshot.getpixel(xy=(x2-x2Min, y2-y2Min)) == target:
                pyautogui.moveTo(x2, y2)
                x2Min = x2-60
                x2Max = x2+60
                y2Min = y2-60
                y2Max = y2+60
                return followTarget(x2Min, x2Max, y2Min, y2Max, target)
            
def findTarget(screenshot: pyautogui,xMin,yMin,xMax,yMax,step,target:tuple[int,int,int]):
    for x1 in range(xMin, xMax, step):  
        if keyboard.is_pressed('q'):
            break
        else:
            for y1 in range(yMin, yMax, step):  

                if keyboard.is_pressed('f'):
                    pyautogui.moveTo(x1, y1)

                # we do -xMin -yMin bc the screenshot is smaller than the screen
                if screenshot.getpixel(xy=(x1-xMin, y1-yMin)) == target:
                    return [x1,y1]
    return None



def main():
    square = (247, 232, 126)
    triangle = (226, 126, 123)
    pentagon = (129, 142, 244)
    allTargets = (pentagon, triangle, square)
    while not keyboard.is_pressed('q'):
        screenshot = pyautogui.screenshot(region=(415, 160, 2500, 1520))
        for target in allTargets:
            targetLocation = findTarget(
                screenshot, 415, 160, 2500, 1520, 97, target)
            if targetLocation != None:
                pyautogui.moveTo(
                    targetLocation[0], targetLocation[1])  # 2085//97=21 1190//97=12
                # total pixels = 2.481.150 ,total pixel checked = 12*21= 252, area of square: 10.000
                x2Min = targetLocation[0]-60
                x2Max = targetLocation[0]+60
                y2Min = targetLocation[1]-60
                y2Max = targetLocation[1]+60
                followTarget(x2Min, x2Max, y2Min, y2Max, target)



main()
