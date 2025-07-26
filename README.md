# VCore Infotainment System for Volvo P1

VCore is a custom infotainment system developed for the Volvo P1 platform (V50, S40, C30, C70). It replaces the factory LCD screen with a modern, touchscreen-based interface while maintaining full integration with the vehicle's existing CANbus and MOST networks.

## Features

- Full CANbus communication
  Integrates with ICM, CEM, PCM, UHF, and SWM modules. Supports steering wheel buttons, climate display, and diagnostic data.

- Factory-style installation
  Uses OEM-style connectors. No wire cutting or splicing required. Retains all original vehicle functions.

- High-resolution touchscreen
  Automotive-grade screen with good visibility in all lighting conditions.

- Audio system support
  Compatible with factory amplifier systems. Includes support for DSP tuning specifically for Volvo audio setups.

- Smartphone connectivity
  Wireless CarPlay and Android Auto support for navigation, calls, and media.

- Real-time vehicle data
  Displays engine parameters, climate settings, and system diagnostics via CANbus.

## Emulator

A system emulator is available to preview the interface and simulate CANbus communication.

Coming soon.

## Supported Vehicles

Volvo P1 platform:

- V50 (2004–2012)
- S40 (2004–2012)
- C30 (2006–2013)
- C70 (2006–2013)

## BedWars Plugin

This repository also contains a simple BedWars plugin for Spigot servers. It features:
- Team colors and scoreboard integration
- Map loading from schematic files
- Basic shop system using an in-game inventory

The plugin source resides in `bedwars/`. Build it using Maven:

```bash
cd bedwars
mvn package
```

The resulting JAR in `target/` can be placed into a Spigot `plugins/` folder.
