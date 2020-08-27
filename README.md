# Payhere Flutter Plugin

A Plugin for Payhere SDK for Flutter.

![ScreenShot](https://raw.githubusercontent.com/SrilalS/tree/Payhere-Flutter-Plugin/blob/master/Docs/img/1.png?raw=true)

![ScreenShot](https://raw.githubusercontent.com/SrilalS/tree/Payhere-Flutter-Plugin/blob/master/Docs/img/1.png?raw=true)

![ScreenShot](https://raw.githubusercontent.com/SrilalS/tree/Payhere-Flutter-Plugin/blob/master/Docs/img/1.png?raw=true)

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