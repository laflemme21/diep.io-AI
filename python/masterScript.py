import threading
import subprocess

#TODO
#Implement communication between the two scripts
#create an upgrade script
#create new script for decidimg where to move player
#create a way (maybe txt file) to customize bot behaviors (agressiveness,upgrades done)
#create a way to rank different runs and save them along with customisation applied to bot

def run_script(script_name):
    subprocess.run(args=[
                   "c:/Users/carlk/Documents/GitHub/diep.io-AI/.venv/Scripts/python.exe", script_name])


aimingThread = threading.Thread(target=run_script, args=("aimingAI.py",))
movementThread = threading.Thread(target=run_script, args=("movementAI.py",))

aimingThread.start()
movementThread.start()

aimingThread.join()
movementThread.join()

print("All scripts have finished executing.")
