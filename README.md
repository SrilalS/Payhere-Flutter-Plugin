# Payhere Flutter Plugin

A Plugin for Payhere SDK for Flutter.


![Build Version](https://img.shields.io/badge/V-0.6.0-brightgreen)
![Build](https://img.shields.io/badge/Status-Stable-brightgreen)
![Build SDK](https://img.shields.io/badge/FlutterChannel-Stable-blue)
![Payheresdk](https://img.shields.io/badge/PayhereSDK-2.0.24-brightgreen)


<p>
    <img src="https://raw.githubusercontent.com/SrilalS/Payhere-Flutter-Plugin/master/Docs/img/1.png?raw=true" width="190px" height="auto"/>
    <img src="https://raw.githubusercontent.com/SrilalS/Payhere-Flutter-Plugin/master/Docs/img/2.png?raw=true" width="190px" height="auto"/>
    <img src="https://raw.githubusercontent.com/SrilalS/Payhere-Flutter-Plugin/master/Docs/img/3.png?raw=true" width="190px" height="auto"/>
</p>


## Usage
[Get it From Pub.dev](https://pub.dev/packages/payhere)
### Installing
Under dependencies in your pubspec.yaml file:

    payhere: ^0.6.0
## Payment Status/Result

**Result will be a JSON in String format. Example  {STATUS : ERROR, CODE: -1}**
**if Payment is a Success, Result will Contain Few Aditional Data**
**{STATUS : SUCCESS , CODE : 2 , SIGN : PAYMENT_SIGN , PAYMENT_NO : PAYMENT_NO}**


| Code| Reason|
|--|--|
| 2|  Success|
| -1|  Error|
| 0|  Canceled|

**Error (-1) and Canceled (0) are Same in Behavior.**

## Dev STATUS
| Platform| OneTime Payment| Recurring Payment | Pre-approval Payment
|--|--|--|--|
| Android |  ✔️| 💻 | ❌ | 
| iOS |  ❌| ❌ | ❌ |
| Web |  ❌| ❌ | ❌ |
