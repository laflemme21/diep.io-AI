import pyautogui
import keyboard


class Screenshot:
    def __init__(self, xMin, yMin, xMax, yMax):
        self.xMin = xMin
        self.yMin = yMin
        self.xMax = xMax
        self.yMax = yMax
        self.screenshot = pyautogui.screenshot(region=(xMin, yMin, xMax, yMax))

    def renewScreenshot(self):
        self.screenshot = pyautogui.screenshot(
            region=(self.xMin, self.yMin, self.xMax, self.yMax))

    def findTargetInScreenshot(self, step, target: tuple[int, int, int]):
        for x1 in range(self.xMin, self.xMax, step):
            if keyboard.is_pressed('q'):
                return None
            for y1 in range(self.yMin, self.yMax, step):

                # if keyboard.is_pressed('f'):
                #   pyautogui.moveTo(x1, y1)

                # we do -xMin -yMin bc the screenshot is smaller than the screen
                if self.screenshot.getpixel(xy=(x1-self.xMin, y1-self.yMin)) == target:
                    return [x1-self.xMin, y1-self.yMin]
        return None


class MiniMap(Screenshot):
    pass


class Target:
    def __init__(self, color, step):
        self.color = color
        self.x = 0
        self.y = 0
        self.step = step

    def findTarget(self, screenshot: Screenshot):
        position = screenshot.findTargetInScreenshot(self.step, self.color)
        if position != None:
            self.x = position[0]
            self.y = position[1]
        else:
            self.x = None
            self.y = None

    def followTarget(self, screenshotRef: Screenshot, step=24, perimeter=60):
        xMin = self.x-perimeter
        xMax = self.x+perimeter
        yMin = self.y-perimeter
        yMax = self.y+perimeter
        coordinates = Screenshot(xMin+screenshotRef.xMin, yMin+screenshotRef.yMin,
                                 xMax+screenshotRef.xMax, yMax+screenshotRef.yMax).findTargetInScreenshot(step, self.color)
        if coordinates != None:
            self.x = coordinates[0]+xMin
            self.y = coordinates[1]+yMin
        else:
            self.x = None
            self.y = None


class Player(Target):
    def __init__(self, step):
        self.color = (0, 0, 0)
        self.x = None
        self.y = None
        self.step = step

    def detectPlayerColor(self, minimap: MiniMap, step=13):
        out = 0
        inn = 0
        red =0
        blue=0
        for x in range(minimap.xMin, minimap.xMax, step):
            for y in range(minimap.yMin, minimap.yMax, step):
                if minimap.screenshot.getpixel(xy=(x-minimap.xMin, y-minimap.yMin)) == (199, 199, 199):
                    out += 1
                elif minimap.screenshot.getpixel(xy=(x-minimap.xMin, y-minimap.yMin)) == (205, 205, 205):
                    inn += 1
                elif minimap.screenshot.getpixel(xy=(x-minimap.xMin, y-minimap.yMin)) == (205, 201, 201):
                    red+=1
                elif minimap.screenshot.getpixel(xy=(x-minimap.xMin, y-minimap.yMin)) == (200, 204, 205):
                    blue+=1
        if out > inn:
            if red>blue:
                if out > red:
                    self.color = (55, 55, 55)
                else:
                    self.color=(61,57,57)
            else:
                if out>blue:
                    self.color = (55, 55, 55)
                else:
                    self.color=(56,60,61)
        else:
            if red > blue:
                if inn > red:
                    self.color = (61, 61, 61)
                else:
                    self.color = (61, 57, 57)
            else:
                if inn > blue:
                    self.color = (61, 61, 61)
                else:
                    self.color = (56, 60, 61)

    def movePlayer(self, xTo, yTo,):
        # pyautogui.moveTo(xTo+2592, yTo+1416)

        if self.x-xTo < -10:
            keyboard.release('a')
            keyboard.press('d')
        elif self.x-xTo > 10:
            keyboard.release('d')
            keyboard.press('a')
        else:
            print('bye')
            keyboard.release('a')
            keyboard.release('d')

        if self.y-yTo < -10:
            keyboard.release('w')
            keyboard.press('s')
        elif self.y-yTo > 10:
            keyboard.release('s')
            keyboard.press('w')
        else:
            print('bye')
            keyboard.release('s')
            keyboard.release('w')

class Mouse():
    def __init__(self):
        pass
    
    def moveTo(self,position,screenshot:Screenshot):
        pyautogui.moveTo(position[0]+screenshot.xMin,
                         position[1]+screenshot.yMin)
