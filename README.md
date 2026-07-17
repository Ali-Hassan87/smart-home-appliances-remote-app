# Smart Home Appliances Remote App 🏠📱

A smart home automation application that allows users to remotely control home appliances such as lights and other connected devices through a mobile application.

## 📌 Project Overview

This project is a **Smart Home Appliances Remote Control App** that enables users to switch home appliances ON or OFF remotely. The application communicates with a local server created using **Python**, which acts as a bridge between the mobile app and the connected appliances.

By entering the IP address of the connected appliance/server, users can send commands from the mobile application to control devices instantly within the local network.

## 🚀 Features

- 🔌 Remote ON/OFF control of home appliances
- 💡 Control lights and other connected devices
- 🌐 IP-based device communication
- 🖥️ Python-based local server integration
- ⚡ Real-time command transmission over a local network
- 📱 Simple and user-friendly mobile interface

## 🛠️ Technologies Used

- **Android Development**
  - Kotlin / Java
  - Android Studio

- **Backend Server**
  - Python
  - Local Socket/Network Communication

- **Networking**
  - IP Address Based Communication
  - Local Wi-Fi Network

## ⚙️ How It Works

1. The Python local server runs on a system connected to the home network.
2. Each smart appliance/device is connected with an IP address.
3. The user enters the device IP address in the mobile application.
4. When the user presses ON/OFF buttons:
   - The app sends a command to the Python server.
   - The server processes the request.
   - The connected appliance changes its state accordingly.

## 📷 Application Workflow

```
Mobile App
     |
     |  ON/OFF Commands
     ↓
Python Local Server
     |
     |  Device Control Signals
     ↓
Smart Home Appliances
```

## 🎯 Purpose

The main goal of this project is to demonstrate a simple and efficient approach to home automation using mobile applications, Python-based local servers, and network communication.

## 🔮 Future Improvements

- Voice control integration
- IoT hardware support (ESP32/Arduino)
- Cloud-based remote access
- Multiple device management
- Real-time device status monitoring

## 👨‍💻 Developer

Developed as a Smart Home Automation project using Android and Python technologies.
