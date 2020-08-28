import 'dart:async';

import 'package:flutter/services.dart';

class Payhere {
  static const pF = const MethodChannel('Payhere');
  String result = "FAILURE";

  Future<String> makeOneTimePayment(OneTimePayment oneTimePayment) async {
    await pF.invokeMethod("OTPPAY", oneTimePayment.toJson());
    await waitForResponse();
    return result;
  }

  Future<String> makeRecurringPayment(RecurringPayment recurringPayment) async {
    await pF.invokeMethod("RPPAY", recurringPayment.toJson());
    await waitForResponse();
    return result;
  }

  Future waitForResponse() {
    Completer completer = new Completer();
    pF.setMethodCallHandler((call) async {
      result = call.arguments.toString();
      completer.complete();
    });
    return completer.future;
  }
}

class OneTimePayment {
  String _merchantID;
  String _merchantSecret;
  String _currency;
  double _amount;
  String _orderID;
  String _itemDesc;
  String _cM1;
  String _cM2;
  String _fName;
  String _lName;
  String _email;
  String _phone;
  String _address;
  String _city;
  String _country;
  String _deliveryAddress;
  String _deliveryCity;
  String _deliveryCountry;
  int _quantity;
  String _mode = "SANDBOX";

  void setSandBoxMode() {
    this._mode = "SANDBOX";
  }

  void setProductionMode() {
    this._mode = "PRODUCTION";
  }


  OneTimePayment(
    String merchantID,
    String merchantSecret,
    String currency,
    double amount,
    String orderID,
    String itemDesc,
    String cM1,
    String cM2,
    String fName,
    String lName,
    String email,
    String phone,
    String address,
    String city,
    String country,
    String deliveryAddress,
    String deliveryCity,
    String deliveryCountry,
    int quantity,
  ) {
    this._merchantID = merchantID;
    this._merchantSecret = merchantSecret;
    this._currency = currency;
    this._amount = amount;
    this._orderID = orderID;
    this._itemDesc = itemDesc;
    this._cM1 = cM1;
    this._cM2 = cM2;
    this._fName = fName;
    this._lName = lName;
    this._email = email;
    this._phone = phone;
    this._address = address;
    this._city = city;
    this._country = country;
    this._deliveryAddress = deliveryAddress;
    this._deliveryCity = deliveryCity;
    this._deliveryCountry = deliveryCountry;
    this._quantity = quantity;
  }

  Map toJson() => {
        "MID": this._merchantID,
        "MSecret": this._merchantSecret,
        "Currency": this._currency,
        "Amount": this._amount,
        "OrderID": this._orderID,
        "ItemDesc": this._itemDesc,
        "CM1": this._cM1,
        "CM2": this._cM2,
        "FName": this._fName,
        "LName": this._lName,
        "EMail": this._email,
        "Phone": this._phone,
        "Address": this._address,
        "City": this._city,
        "Country": this._country,
        "DeliveryAddress": this._deliveryAddress,
        "DeliveryCity": this._deliveryCity,
        "DeliveryCountry": this._deliveryCountry,
        "Quantity": this._quantity,
        "MODE": this._mode
      };
}

class RecurringPayment {
  String _merchantID;
  String _merchantSecret;
  String _currency;
  double _amount;
  String _orderID;
  String _itemDesc;
  String _cM1;
  String _cM2;
  String _fName;
  String _lName;
  String _email;
  String _phone;
  String _address;
  String _city;
  String _country;

  String _recurrence;
  String _duration;
  double _startUpFee;

  String _mode = "SANDBOX";

  void setSandBoxMode() {
    this._mode = "SANDBOX";
  }

  void setProductionMode() {
    this._mode = "PRODUCTION";
  }

  RecurringPayment(
      String merchantID,
      String merchantSecret,
      String currency,
      double amount,
      String orderID,
      String itemDesc,
      String cM1,
      String cM2,
      String fName,
      String lName,
      String email,
      String phone,
      String address,
      String city,
      String country,
      String recurrence,
      String duration,
      double startUpFee) {
    this._merchantID = merchantID;
    this._merchantSecret = merchantSecret;
    this._currency = currency;
    this._amount = amount;
    this._orderID = orderID;
    this._itemDesc = itemDesc;
    this._cM1 = cM1;
    this._cM2 = cM2;
    this._fName = fName;
    this._lName = lName;
    this._email = email;
    this._phone = phone;
    this._address = address;
    this._city = city;
    this._country = country;
    this._recurrence = recurrence;
    this._duration = duration;
    this._startUpFee = startUpFee;
  }

  Map toJson() => {
        "MID": this._merchantID,
        "MSecret": this._merchantSecret,
        "Currency": this._currency,
        "Amount": this._amount,
        "OrderID": this._orderID,
        "ItemDesc": this._itemDesc,
        "CM1": this._cM1,
        "CM2": this._cM2,
        "FName": this._fName,
        "LName": this._lName,
        "EMail": this._email,
        "Phone": this._phone,
        "Address": this._address,
        "City": this._city,
        "Country": this._country,
        "Recurrence": this._recurrence,
        "Duration": this._duration,
        "StartUpFee": this._startUpFee,
        "MODE": this._mode
      };
}
