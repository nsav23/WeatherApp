import serial       #pyserial library to communicate with the Arduino board

SERIAL_PORT = "/dev/ttyACM0"    #The serial port the arduino uses(Could be different for diffrent people!!!)
BAUD_RATE = 9600    #The baud rate the Arduino uses to communicate with the PC
LOG_FILE = "dht_readings_log.txt"   #The .txt file where the output from the Arduino IDE Serial monitor will be written

def main():
    try:
#Open serial connection to Arduino and wait 1s before receiving data, recieve data with 1 second interval
        ser = serial.Serial(SERIAL_PORT, BAUD_RATE, timeout=1)  

        with open(LOG_FILE, "a", buffering = 1) as f:
            while True:
                line = ser.readline().decode("utf-8").strip()
                f.write(line + "\n")
                f.flush()
 #Add keyboard combination for interrupting the porcess manually
    except KeyboardInterrupt:
        print("Interrupted manually by user!")
        with open(LOG_FILE, "a") as f:  #write "END" before stopping work with the file(important for the sevlet logic)
            f.write("END")
#Add keyboard combination for interrupting the porcess manually
if __name__ == "__main__":
    main()
