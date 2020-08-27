# Payhere Flutter Plugin

A Plugin for Payhere SDK for Flutter.
![Build Version](https://img.shields.io/badge/V-0.5.1-brightgreen)
![Build](https://img.shields.io/badge/Status-Stable-brightgreen)
![Build SDK](https://img.shields.io/badge/FlutterChannel-Stable-blue)
![Payhere SDK Version](https://img.shields.io/badge/V-2.0.24-brightgreen)


![ScreenShot](https://raw.githubusercontent.com/SrilalS/Payhere-Flutter-Plugin/master/Docs/img/1.png?raw=true)
![ScreenShot](https://raw.githubusercontent.com/SrilalS/Payhere-Flutter-Plugin/master/Docs/img/1.png?raw=true)
![ScreenShot](https://raw.githubusercontent.com/SrilalS/Payhere-Flutter-Plugin/master/Docs/img/1.png?raw=true)


## Usage
### Installing
Under dependencies in your pubspec.yaml file:

    payhere: ^0.5.1
## Payment Status/Result

**Result will be a JSON in String format. Example  {STATUS : ERROR, CODE: -1}**
| Code| Reason|
|--|--|
| 1|  Success|
| -1|  Error|
| 0|  Canceled|
**Error (-1) and Canceled (0) are Same in Behavior.**

## Dev STATUS
| Platform| OneTime Payment| Recurring Payment | Pre-approval Payment
|--|--|--|--|
| Android |  ✔️| ❌ | ❌ | 
| iOS |  ❌| ❌ | ❌ |
| Web |  ❌| ❌ | ❌ |